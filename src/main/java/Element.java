import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

abstract class Element {
    private Position position;
    Element(int x, int y){
        position = new Position(x,y);
    }
    public void setPosition(Position pos){
        this.position = pos;
    }
    public Position getPos(){
        return this.position;
    }
    abstract public void draw(TextGraphics graphics);
}
