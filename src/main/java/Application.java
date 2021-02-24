import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.Screen.*;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.TerminalSize;
import java.io.IOException;


public class Application {
    public static void main(String[] args) {

            Game game = new Game();
            game.run();
    }
}
