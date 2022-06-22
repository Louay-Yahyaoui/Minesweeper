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
    private ArrayList<JButton> buttons;
    private static JButton smiley;

    public static JButton getSmiley()
    {
        return smiley;
    }

    public GamePanel(GameMap gameMap, GameWindow gameWindow)
    {
        super(new GridLayout(gameMap.getHeight()+1, gameMap.getWidth()));
        MyMenu menu=new MyMenu("file");
        JMenuBar jbar=menu.getJMenuBar();
        jbar.setSize(new Dimension(getHeight()/gameMap.getHeight()+1,getWidth()));
        add(jbar);
        setSize(1280,720);
        setPreferredSize(new Dimension(720/16*17,720));
        BUTTON_HEIGHT=getHeight()/gameMap.getHeight();
        BUTTON_WIDTH=getWidth()/gameMap.getWidth();
        JLabel label;
        smiley=new JButton();
        smiley.setIcon(new ImageIcon(new ImageIcon("res/smiley.jpg").getImage().getScaledInstance(BUTTON_WIDTH,BUTTON_HEIGHT,java.awt.Image.SCALE_SMOOTH)));
        for (int i=0;i<gameMap.getWidth()-1;i++) {
            if(i==gameMap.getWidth()/2)
            {
                add(smiley);
                continue;
            }
            label=new JLabel();
            add(label);
        }
        this.gameWindow=gameWindow;
        this.gameMap=gameMap;
        setFocusable(true);
        buttons=new ArrayList<JButton>();


        drawGameMap();
    }

    public static void setSmiley(JButton smiley) {
        GamePanel.smiley=smiley;
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
                buttons.add(button);
            }
    }
    public void resetButtons()
    {
        smiley.setIcon(MouseInputs.SMILEY);
        for (JButton button :buttons)
        {
            button.setText("");
            button.setIcon(null);
            button.setBackground(getBackground());
        }
    }
    public void paintComponent(Graphics g)
    {
        g.setColor(new Color(200,200,200));
        g.fillRect(0,0,1280,720);
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
