import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {
    JLabel background = new JLabel(new ImageIcon("background.png"));
    JLabel bacteria = new JLabel(new ImageIcon("character.png"));
    int bacteriaSize = 50;
    int AreaWidth = 800;
    int AreaHeight = 420;
    int leftBoundaryToMove = 0;
    int topBoundaryToMove = 0;
    int rightBoundaryToMove = AreaWidth - bacteriaSize - 15;
    int bottomBoundaryToMove = AreaHeight - bacteriaSize;

    public GameArea(Display display) {
        super();
        this.setLayout(null);
        this.setBounds(0, 0, AreaWidth, AreaHeight);
        bacteria.setBounds((AreaWidth - 50) / 2, (AreaHeight - 50) / 2, bacteriaSize, bacteriaSize);
        this.add(bacteria);

        background.setBounds(0, 0, AreaWidth, AreaHeight);
        this.add(background);

    }

    // moves a character and returns true if the move is possible
    public boolean CharacterMoveTo(JLabel character, int x, int y) {
        if (x <= rightBoundaryToMove && y <= bottomBoundaryToMove && x >= leftBoundaryToMove && y >= topBoundaryToMove) {
            character.setBounds(x, y, character.getWidth(), character.getHeight());
            return true;
        }
        return false;
    }

}
