import javax.swing.*;

public class GameWindow extends JFrame
{
    private GamePanel panel;
    private MyMenu menu;
    private Game game;

    public Game getGame() {
        return game;
    }

    public GamePanel getPanel() {
        return panel;
    }

    public GameWindow(GameMap gameMap, Game game)
    {
        this.game=game;
        panel=new GamePanel(gameMap,this);
        add(panel);
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


    public void newGame() {
        panel.resetButtons();
        panel.getGameMap().reset();
        System.out.println(panel.getGameMap().getBombNb());
    }

    public void restart() {
        panel.resetButtons();
        panel.getGameMap().resetChecks();
    }
}
