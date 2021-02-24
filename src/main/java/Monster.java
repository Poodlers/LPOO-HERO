import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    Monster(int x, int y){
        super(x,y);
    }

    public Position move(){
        Random random = new Random();
        int rand_int = random.nextInt(4);
        switch (rand_int){
            case 0:
                return moveUp();
            case 1:
                return moveDown();
            case 2:
                return moveLeft();
            case 3:
                return moveRight();
        }
        return new Position(getPos().getX(),getPos().getY());

    }

    public Position moveUp(){
        return new Position(getPos().getX(),getPos().getY() -1);
    }
    public Position moveDown(){
        return new Position(getPos().getX(),getPos().getY() + 1);
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
        graphics.putString(new TerminalPosition(getPos().getX(), getPos().getY()), "M");
    }
}
