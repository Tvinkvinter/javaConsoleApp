import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.*;

public class Console extends JPanel {
    private static GameArea gameArea;
    private static Display display;
    Color textColor = new Color(223, 223, 203);
    JLabel preCommand = new JLabel(">>>");
    public static JTextField input = new JTextField("", 100);
    public static JLabel output = new JLabel();
    ArrayList<String> buffer = new ArrayList<String>();
    int currentBufferIndex = 0;

    String moveToRegex = "move_to\\(\\d+, \\d+\\)";  // regex for command "move_to(x, y)"
    Pattern patternCommandMove = Pattern.compile(moveToRegex);

    public Console(Display display, GameArea gameArea) {
        super();
        Console.gameArea = gameArea;
        Console.display = display;
        this.setBackground(new Color(43, 43, 43));
        this.setBounds(0, 420, 800, 150);
        this.setLayout(null);

        output.setBounds(10, 0, 740, 110);
        output.setForeground(textColor);
        output.setText("<html></html>");
        output.setVerticalAlignment(SwingConstants.BOTTOM);
        this.add(output);

        preCommand.setBounds(10, 110, 40, 20);
        preCommand.setForeground(textColor);
        this.add(preCommand);

        input.setBounds(40, 110, 740, 20);
        input.setBackground(new Color(43, 43, 43));
        input.setCaretColor(textColor);
        input.setForeground(textColor);
        input.setBorder(null);
        input.addKeyListener(new CommandManager());
        this.add(input);

        buffer.add("");
    }

    public static void updateOutput(String addLine) {
        String consoleOutput = Console.output.getText().substring(0, Console.output.getText().length() - 7);
        consoleOutput += addLine + "<br></html>";
        Console.output.setText(consoleOutput);
    }

    class CommandManager implements KeyListener {
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (patternCommandMove.matcher(Console.input.getText()).matches()) {
                    int xIndex = Console.input.getText().indexOf('(') + 1;
                    int yIndex = Console.input.getText().indexOf(',') + 2;
                    int x = Integer.parseInt(Console.input.getText().substring(xIndex, Console.input.getText().indexOf(',')));
                    int y = Integer.parseInt(Console.input.getText().substring(yIndex, Console.input.getText().lastIndexOf(')')));
                    boolean isMoveSucceed = Console.gameArea.CharacterMoveTo(gameArea.bacteria, x, y);
                    if (isMoveSucceed) Console.updateOutput(">>> " + Console.input.getText());
                    else Console.updateOutput("<font color=gray> >>> " + Console.input.getText() +
                            "</font><br><font color=yellow>The character is going to be out of the area!<br>" + "Possible values: "
                            + "x = (" + gameArea.leftBoundaryToMove + ", " + gameArea.rightBoundaryToMove + "), "
                            + "y = (" + gameArea.topBoundaryToMove + ", " + gameArea.bottomBoundaryToMove + ")</font>");
                } else if (Console.input.getText().equals("exit()")) {
                    display.dispose();
                } else {
                    Console.updateOutput("<font color=red>Command \"" + Console.input.getText() + "\" is not recognized!</font>");
                }
                buffer.add(buffer.size() - 1, Console.input.getText());
                currentBufferIndex = buffer.size() - 1;
                Console.input.setText("");
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (currentBufferIndex > 0) currentBufferIndex--;
                Console.input.setText(buffer.get(currentBufferIndex));
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (currentBufferIndex < buffer.size() - 1) currentBufferIndex++;
                Console.input.setText(buffer.get(currentBufferIndex));
            }
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }
    }
}
