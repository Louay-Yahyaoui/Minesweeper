import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePanel extends JPanel
{
    private GameMap gameMap;
    private GameWindow gameWindow;
    private final int BUTTON_HEIGHT;
    private final int BUTTON_WIDTH;
    public GamePanel(GameMap gameMap,GameWindow gameWindow)
    {
        super(new GridLayout(gameMap.getHeight(), gameMap.getWidth()));;
        this.gameWindow=gameWindow;
        this.gameMap=gameMap;
        setFocusable(true);
        //MyMenu menu=new MyMenu("file");
        //add(menu.getJMenuBar());
        setSize(1280,720);
        BUTTON_HEIGHT=getHeight()/gameMap.getHeight();
        BUTTON_WIDTH=getWidth()/gameMap.getWidth();
        drawGameMap();
    }

    private void drawGameMap()
    {
        JButton button;
        for(int i=0;i<gameMap.getHeight();i++)
            for (int j=0;j<gameMap.getWidth();j++)
            {
                button=new JButton("");
                add(button);
                button.addMouseListener(new MouseInputs(this,gameMap,button));
            }
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(new Color(100,100,100));
        g.fillRect(0,0,1280,720);
    }
    public int getIndexAt(int x, int y)
    {
        return (x/BUTTON_HEIGHT)*gameMap.getWidth()+(y/BUTTON_WIDTH);
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }
}
