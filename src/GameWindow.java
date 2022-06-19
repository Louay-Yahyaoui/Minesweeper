import javax.swing.*;

public class GameWindow extends JFrame
{
    private GamePanel panel;
    private MyMenu menu;
    public GameWindow()
    {
        menu=new MyMenu("file");
        panel=new GamePanel();
        panel.add(menu.getJMenuBar());
        add(panel);
        setVisible(true);
    }
    public static void playAgain(boolean again)
    {
        System.out.println("new game loading... | again:"+again);
    }
}
