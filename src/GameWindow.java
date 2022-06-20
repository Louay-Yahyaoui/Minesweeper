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


    public void newGame() {
        panel.resetButtons();
        panel.getGameMap().reset();
    }

    public void restart() {
        panel.resetButtons();
    }
}
