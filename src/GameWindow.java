import javax.swing.*;

public class GameWindow extends JFrame
{
    private GamePanel panel;
    private MyMenu menu;

    public GamePanel getPanel() {
        return panel;
    }

    public GameWindow(GameMap gameMap)
    {
        panel=new GamePanel(gameMap,this);
        add(panel);
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void playAgain(boolean again)
    {
        System.out.println("new game loading... | again:"+again);
    }
}
