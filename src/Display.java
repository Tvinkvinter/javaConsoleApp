import java.awt.*;
import javax.swing.*;

public class Display extends JFrame {
    GameArea gameArea = new GameArea(this);
    Console console = new Console(this, gameArea);
    public static void main(String[] args){
        Display display = new Display();
    }

    public Display() {
        super("Lab 2");
        this.setLayout(null);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.add(gameArea);
        container.add(console);
    }
}
