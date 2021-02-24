import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena = new Arena(50,100);

    Game(){
        try{
            TerminalSize terminalSize = new TerminalSize(100, 100);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private void processKey(KeyStroke key) throws IOException {
        arena.processKey(key);
    }

    public void run(){
        try{
            while(true){
                screen.clear();
                arena.draw(screen.newTextGraphics());
                screen.refresh();
                KeyStroke key = screen.readInput();
                if(key.getKeyType() == KeyType.EOF){
                    break;
                }
                processKey(key);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
