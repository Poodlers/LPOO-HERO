import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena extends MyScreen {
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    private Door door;
    private Hero hero;
    Arena(String filename){
        List<Wall> walls = new ArrayList<>();
        List<Monster> monsters = new ArrayList<>();
        List<Coin> coins = new ArrayList<>();
        File file = new File(filename);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int line_number = 1;
            while ((line = br.readLine()) != null) {
                this.setWidth(line.length() + 1);
                for(int i = 0; i < line.length();i++){
                    switch (line.charAt(i)){
                        case 'X':
                            walls.add(new Wall(i+1,line_number));
                            break;
                        case 'M':
                            monsters.add(new Monster_Circle(i + 1, line_number, "M"));
                            break;
                        case 'S':
                            monsters.add(new Monster_Slider(i+1, line_number, "S"));
                            break;
                        case 'T':
                            monsters.add(new Monster_Teleporter(i +1, line_number, "T"));
                            break;
                        case 'H':
                            hero = new Hero(i + 1, line_number);
                            break;
                        case 'O':
                            coins.add(new Coin(i + 1, line_number));
                            break;

                    }
                }
                line_number++;
            }
            this.setHeight(line_number);
            this.walls = walls;
            this.monsters = monsters;
            this.coins = coins;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    Arena(int h, int w){
        super(h,w);
        hero = new Hero(10,10);
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
        this.door = createDoor();
    }
    private Door createDoor(){
        Random random = new Random();
        Position position;
        boolean valid_pos = false;
        int x = 0, y = 0;
        while(!valid_pos){
            valid_pos = true;
            x = random.nextInt(getWidth() - 2);
            y = random.nextInt(getHeight()- 2);
            position = new Position(x,y);
            for(Monster monster: monsters){
                if(monster.getPos().equals(position)){
                    valid_pos = false;
                    break;
                }
            }
        }
        return new Door(x,y);
    }
    private List<Monster> createMonsters() {
        Random random = new Random();
        int monster_select;
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            monster_select = random.nextInt(3);
            switch (monster_select){
                case 0:
                    monsters.add(new Monster_Circle(random.nextInt(getWidth() - 2) + 1, random.nextInt(getHeight() - 2) + 1, "M"));
                    break;
                case 1:
                    monsters.add(new Monster_Teleporter(random.nextInt(getWidth() - 2) + 1, random.nextInt(getHeight() - 2) + 1, "T"));
                    break;
                case 2:
                    monsters.add(new Monster_Slider(random.nextInt(getWidth() - 2) + 1, random.nextInt(getHeight() - 2) + 1, "S"));
                    break;
            }

        }

        return monsters;
    }
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(getWidth() - 3) + 1, random.nextInt(getHeight() - 3) + 1));
        return coins;
    }
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < getWidth(); c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, getHeight() - 2));
        }

        for (int r = 1; r < getHeight() - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(getWidth() - 2, r));
        }

        return walls;
    }
    private void retrieveCoins(Position pos){
        for(Coin coin: coins){
            if(coin.getPos().equals(pos)){
                coins.remove(coin);
                hero.collectCoin();
                return;
            }
        }
    }
    public void moveMonsters(){
        for(Monster monster: monsters){
            Position new_mons_pos = monster.move();
            if(canHeroMove(new_mons_pos)){
                monster.setPosition(new_mons_pos);
            }
        }
    }
    private void checkMonsterCollision(Position position){
        for(Monster monster: monsters){
            if(monster.getPos().equals(position)){
                hero.takeDamage();
                if(hero.getLifeEnergy() == 0){
                    this.setLostGame(true);
                }
            }
        }
    }
    @Override
    public void processKey(KeyStroke key) throws IOException {
        switch(key.getKeyType()){
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            case Character:
                if(key.getCharacter() == 'q'){
                    System.exit(0);
                }
                break;
        }
        checkMonsterCollision(hero.getPos());
        moveMonsters();
        checkMonsterCollision(hero.getPos());
        if(this.coins.isEmpty())  checkDoorCollision();
    }
    private void checkDoorCollision(){
        if(door.getPos().equals(hero.getPos())){
            setGoToNext(true);
        }
    }

    private boolean canHeroMove(Position pos){
        if(pos.getY() > getHeight() - 3 || pos.getY() < 1 || pos.getX() < 1 || pos.getX() > getWidth() - 3){
            return false;
        }
        return true;
    }
    private void moveHero(Position position) {
        if(canHeroMove(position)){
            hero.setPosition(position);
            retrieveCoins(position);
        }
    }
    @Override
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(getWidth(), getHeight()), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
        for(Monster monster: monsters){
            monster.draw(graphics);
        }
        hero.draw_HP(graphics,getWidth(),getHeight());
        hero.draw_Score(graphics,getWidth(),getHeight());
        if(this.coins.size() == 0){
            door.draw(graphics);
        }
    }
}
