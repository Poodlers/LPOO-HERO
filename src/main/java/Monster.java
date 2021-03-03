
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

abstract public class Monster extends Element{
    private String character;
    Monster(int x, int y, String character){
        super(x,y);
        this.character = character;
    }

    abstract public Position move();
    public Position moveUp(int amount){
        return new Position(getPos().getX(),getPos().getY() - amount);
    }
    public Position moveComposite(int x, int y){
        return new Position(getPos().getX() + x,getPos().getY() + y);
    }
    public Position moveDown(int amount){
        return new Position(getPos().getX(),getPos().getY() + amount);
    }
    public Position moveRight(int amount){
        return new Position(getPos().getX() + amount,getPos().getY());
    }
    public Position moveLeft(int amount){
        return new Position(getPos().getX() - amount,getPos().getY());
    }
    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#222222"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPos().getX(), getPos().getY()), this.character);
    }
}
