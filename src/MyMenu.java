import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MyMenu extends JMenu {
    private JMenuBar menuBar;
    Listener listener;
    public MyMenu(String s)
    {
        super(s);
        listener=new Listener();
        setMnemonic(KeyEvent.VK_F);
        getAccessibleContext().setAccessibleDescription(
                "File");
        JMenuItem menuItem=new JMenuItem("new game",KeyEvent.VK_F2);
        menuItem.addActionListener(listener);
        add(menuItem);
        JMenuItem menuItem1=new JMenuItem("play again",KeyEvent.VK_F3);
        menuItem1.addActionListener(listener);
        add(menuItem1);
        menuBar=new JMenuBar();
        menuBar.add(this);

    }

    public JMenuBar getJMenuBar() {
        return menuBar;
    }
}
