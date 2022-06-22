import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MouseInputs implements MouseListener {
    private GamePanel gamePanel;
    private GameMap gameMap;
    private JButton button;
    private static Icon FLAG ;
    private static Icon QUESTION;
    private  static Icon BOMB;
    private static int BUTTON_HEIGHT;
    private static int BUTTON_WIDTH;
    private int c;
    private int rotation;
    public MouseInputs(GamePanel gamePanel, GameMap gameMap,JButton button)
    {
        this.gamePanel = gamePanel;
        this.gameMap = gameMap;
        this.button=button;
        BUTTON_WIDTH=gamePanel.getWidth()/gameMap.getWidth();
        BUTTON_HEIGHT=gamePanel.getHeight()/gameMap.getHeight();
        c=0;
        if(BOMB==null) BOMB=new ImageIcon(new ImageIcon("res/bomb.png").getImage().getScaledInstance(BUTTON_WIDTH,BUTTON_HEIGHT,java.awt.Image.SCALE_SMOOTH));
        if(FLAG==null)FLAG=new ImageIcon(new ImageIcon("res/flag.jpg").getImage().getScaledInstance(BUTTON_WIDTH,BUTTON_HEIGHT,java.awt.Image.SCALE_SMOOTH));
        if(QUESTION==null)QUESTION=new ImageIcon(new ImageIcon("res/question.jpg").getImage().getScaledInstance(BUTTON_WIDTH,BUTTON_HEIGHT,java.awt.Image.SCALE_SMOOTH));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            int index=gamePanel.getButtons().indexOf(button);
            int x=index %gameMap.getWidth();
            int y=index/gameMap.getWidth();
            int content=gameMap.getGameCoordinates()[y][x];
            String[] options=new String[2];
            options[0]="play again";
            options[1]="quit";
            JFrame frame=gamePanel.getGameWindow();
            if(content==-1)
            {
                if(rotation==0) {
                    rotation=1;
                    button.setIcon(BOMB);
                    button.setBackground(Color.red);
                    int response = JOptionPane.showOptionDialog(frame, "You lose",
                            "sorry", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                    if (response == 1)
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    else {
                        gameMap.reset();
                        gamePanel.resetButtons();
                    }
                }
                else
                {
                    button.setIcon(QUESTION);
                    rotation--;
                }
            }

            else{
                int w=gameMap.getWidth(),h=gameMap.getHeight();
                if (content!=0) {
                    button.setText(String.valueOf(content));
                    button.setBackground(Color.white);
                }
                else
                {
                    JButton button;
                    int v;
                    ArrayList<Integer> nonBombs=gameMap.OpenUp(x,y);
                    for(int n:nonBombs) {
                        button=gamePanel.getButtons().get(n);
                        button.setBackground(Color.white);
                        v=gamePanel.getGameMap().getGameCoordinates()[n / w][n % w];
                        if(v!=0)
                        {
                            button.setText(String.valueOf(v));
                        }
                    }
                }
                for(int i=0;i< w*h;i++)
                {
                    if(!((gameMap.isBomb(i/w,i%w)||(gamePanel.getButtons().get(i).getBackground().equals(Color.white)))))
                        break;
                    if(i==w*h-1)
                        JOptionPane.showMessageDialog(frame,"Win haha");
                }
            }
            //show content of the pressed label
        } else if ((e.getButton()==MouseEvent.BUTTON3)&&(!(button.getBackground().equals(Color.white))))
        {
        if(c==0) {
            button.setIcon(FLAG);
            rotation=1;
        }
            else if (c==1)
                button.setIcon(QUESTION);
            else
                button.setIcon(null);
            c=++c%3;
       }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
