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
        if(game.getBombNb()==GameMap.EASY)
        {
            game = new Game(game.getHeight(), game.getWidth(), game.getBombNb());
        }
        panel.resetButtons();
        panel.getGameMap().reset();
    }

    public void restart() {
        panel.resetButtons();
    }
}
