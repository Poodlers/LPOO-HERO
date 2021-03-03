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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Screen screen;
    //private Arena arena = new Arena("C:\\Users\\radio\\OneDrive\\Ambiente de Trabalho\\PwoGwammingUwU\\Java Projects\\hero\\arena.txt");
    private List<MyScreen> screenList;
    private int currentArena;
    Game(){
        screenList = createScreen();
        currentArena = 1;
        try{
            TerminalSize terminalSize = new TerminalSize(100, 50);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private List<MyScreen> createScreen() {
        Random random = new Random();
        ArrayList<MyScreen> arenas = new ArrayList<>();
        arenas.add(new WinScreen(50,100,"You Lost!!!"));
        for (int i = 0; i < 3; i++){
            arenas.add(new Arena(50,100));
        }
        arenas.add(new WinScreen(50,100,"You Won!!!!"));
        return arenas;
    }

    private void processKey(KeyStroke key) throws IOException {
        screenList.get(currentArena).processKey(key);
    }
    private void restart(){
        this.screenList = createScreen();
        currentArena = 1;

    }
    public void run(){
        try{
            while(true){
                if(screenList.get(currentArena).isArenaComplete()){
                    if(currentArena == this.screenList.size() - 1){
                        restart();
                    }else{
                        currentArena++;
                    }

                }
                if(screenList.get(currentArena).gameIsLost()){
                    currentArena = 0;
                }
                screen.clear();
                screenList.get(currentArena).draw(screen.newTextGraphics());
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
