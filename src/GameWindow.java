import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

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
        try {
            setIconImage( ImageIO.read(new File("res/icon.jpg")));
        }
        catch (Exception e)
        {}

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
    }

    public void restart() {
        panel.resetButtons();
        panel.getGameMap().resetChecks();
    }
}
