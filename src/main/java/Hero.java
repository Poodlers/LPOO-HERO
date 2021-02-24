import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position pos;
    Hero(int x, int y){
       pos = new Position(x,y);
    }
    public void setPosition(Position pos){
        this.pos = pos;
    }
    public Position getPos(){
        return this.pos;
    }
    public Position moveUp(){
        return new Position(pos.getX(),pos.getY() -1);
    }
    public Position moveDown(){
        return new Position(pos.getX(),pos.getY() + 1);
    }
    public Position moveRight(){
        return new Position(pos.getX() + 1,pos.getY());
    }
    public Position moveLeft(){
        return new Position(pos.getX() - 1,pos.getY());
    }
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "X");
    }
}
