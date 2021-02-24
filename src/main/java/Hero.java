import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero extends Element {
    Hero(int x, int y){
       super(x,y);
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
        graphics.putString(new TerminalPosition(getPos().getX(), getPos().getY()), "X");
    }
}
