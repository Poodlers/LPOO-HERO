import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int height;
    private int width;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    private Hero hero = new Hero(10,10);
    Arena(int h, int w){
        height = h;
        width = w;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }
    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return monsters;
    }
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }
    private void retrieveCoins(Position pos){
        for(Coin coin: coins){
            if(coin.getPos().equals(pos)){
                coins.remove(coin);
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
                System.out.println("Monster Killed Hero! RIP");
                System.exit(0);
            }
        }
    }

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
        }
        checkMonsterCollision(hero.getPos());
        moveMonsters();
        checkMonsterCollision(hero.getPos());
    }
    private boolean canHeroMove(Position pos){
        for(Wall wall: walls){
            if(wall.getPos().equals(pos)){
                return false;
            }
        }
        return true;
    }
    private void moveHero(Position position) {
        if(canHeroMove(position)){
            hero.setPosition(position);
            retrieveCoins(position);
        }
    }
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
        for(Monster monster: monsters){
            monster.draw(graphics);
        }
    }
}
