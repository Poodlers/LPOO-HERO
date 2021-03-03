import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

abstract public class MyScreen {
    private int height;
    private int width;
    private boolean goToNext = false;
    private boolean LostGame = false;

    MyScreen() {
    }
    ;

    MyScreen(int h, int w) {
        height = h;
        width = w;
    }
    public int getHeight(){
        return this.height;
    }
    public int getWidth(){
        return this.width;
    }



    abstract public void processKey(KeyStroke key) throws IOException;

    public boolean isArenaComplete() {
        return this.goToNext;
    }
    public boolean gameIsLost() {
        return LostGame;
    }

    public void setGoToNext(boolean bool) {
        this.goToNext = bool;
    }
    public void setLostGame(boolean bool) {
        this.LostGame = bool;
    }

    abstract public void draw(TextGraphics graphics);

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}