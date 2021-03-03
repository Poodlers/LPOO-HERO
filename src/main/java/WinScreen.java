import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class WinScreen extends MyScreen{
    private String word;
    WinScreen(int h,int w,String word){
        super(h,w);
        this.word = word;
    }

    @Override
    public void processKey(KeyStroke key) throws IOException {
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'r'){
            this.setGoToNext(true);
        }else{
            System.exit(0);
        }
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.getWidth(), this.getHeight()), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.getWidth() / 2 - 5,this.getHeight() / 2), this.word);
        graphics.putString(new TerminalPosition(this.getWidth() / 2 - 5,this.getHeight() / 2 + 1), "PRESS 'R' to Restart!");
        graphics.putString(new TerminalPosition(this.getWidth() / 2 - 5,this.getHeight() / 2 + 3), "PRESS any other key to exit!");
    }
}
