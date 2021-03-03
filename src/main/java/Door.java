import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Door extends Element{
    Door(int x, int y){
        super(x,y);
    }
    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPos().getX(), getPos().getY()), "D");
    }
}
