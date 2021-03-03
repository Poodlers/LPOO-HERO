import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero extends Element {
    private int LifeEnergy = 3;
    private int score = 0;
    Hero(int x, int y){
       super(x,y);
    }

    public Position moveUp(){
        return new Position(getPos().getX(),getPos().getY() -1);
    }
    public Position moveDown(){
        return new Position(getPos().getX(),getPos().getY() + 1);
    }
    public int getLifeEnergy(){
        return LifeEnergy;
    }
    public void takeDamage(){
        this.LifeEnergy--;

    }
    public void collectCoin(){
        this.score++;
    }
    public Position moveRight(){
        return new Position(getPos().getX() + 1,getPos().getY());
    }
    public Position moveLeft(){
        return new Position(getPos().getX() - 1,getPos().getY());
    }


    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPos().getX(), getPos().getY()), "X");
    }

    public void draw_HP(TextGraphics graphics, int width,int height){
        graphics.setForegroundColor(TextColor.Factory.fromString("#999999"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(width - 15,height - 1), "HP: ");
        graphics.putString(new TerminalPosition(width - 6,height - 1), String.valueOf(LifeEnergy));

    }

    public void draw_Score(TextGraphics graphics, int width,int height){
        graphics.setForegroundColor(TextColor.Factory.fromString("#999999"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(width - 30,height - 1), "Score: ");
        graphics.putString(new TerminalPosition(width - 24,height - 1), String.valueOf(this.score));

    }

}
