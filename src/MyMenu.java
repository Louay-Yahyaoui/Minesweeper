import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MyMenu extends JMenu {
    private JMenuBar menuBar;
    Listener listener;
    SubListener subListener;
    GamePanel panel;
    public MyMenu(String s,GamePanel panel)
    {
        super(s);
        this.panel=panel;
        listener=new Listener(panel.getGameWindow().getGame());
        subListener=new SubListener(panel);
        JMenu submenu=new JMenu();
        submenu.getAccessibleContext().setAccessibleDescription("difficulty");
        submenu.setText("Difficulty");
        JMenuItem i1=new JMenuItem("easy"),i2=new JMenuItem("medium"),i3=new JMenuItem("hard");
        submenu.add(i1);
        submenu.add(i2);
        submenu.add(i3);
        i1.addActionListener(subListener);
        i2.addActionListener(subListener);
        i3.addActionListener(subListener);
        setMnemonic(KeyEvent.VK_F);
        getAccessibleContext().setAccessibleDescription(
                "File");
        JMenuItem menuItem=new JMenuItem("new game",KeyEvent.VK_F2);
        menuItem.addActionListener(listener);
        add(menuItem);
        JMenuItem menuItem1=new JMenuItem("play again",KeyEvent.VK_F3);
        menuItem1.addActionListener(listener);
        add(menuItem1);
        add(submenu);
        menuBar=new JMenuBar();
        menuBar.add(this);

    }

    public JMenuBar getJMenuBar() {
        return menuBar;
    }
}
