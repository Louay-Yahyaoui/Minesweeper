import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;

public class MouseInputs implements MouseListener {
    private GamePanel gamePanel;
    private GameMap gameMap;
    private JButton button;
    private static Icon FLAG ;
    private static Icon QUESTION;
    private  static Icon BOMB;
    private static int BUTTON_HEIGHT;
    private static int BUTTON_WIDTH;
    private int x;
    public MouseInputs(GamePanel gamePanel, GameMap gameMap,JButton button)
    {
        this.gamePanel = gamePanel;
        this.gameMap = gameMap;
        this.button=button;
        BUTTON_WIDTH=gamePanel.getWidth()/gameMap.getWidth();
        BUTTON_HEIGHT=gamePanel.getHeight()/gameMap.getHeight();
        x=0;
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
            int index=gamePanel.getIndexAt(e.getX(),e.getY());
            int x=index/ gameMap.getWidth();
            int y=index% gameMap.getWidth();
            int content=gameMap.getGameCoordinates()[x][y];
            String[] options=new String[2];
            options[0]="play again";
            options[1]="quit";
            JFrame frame=gamePanel.getGameWindow();
            if(content==-1)
            {
                button.setIcon(BOMB);
                button.setBackground(Color.red);
                int response=JOptionPane.showOptionDialog(frame,"You lose",
                        "sorry",JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null,options,options[0]);
                if(response==1)
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                else
                {
                    gameMap.reset();
                }
            }

            else if (content!=0) {
                button.setText(String.valueOf(content));
            }
            else
            {
                button.setBackground(Color.white);
            }
            //show content of the pressed label
        } else if (e.getButton()==MouseEvent.BUTTON3) {
            if(x==0)
            button.setIcon(FLAG);
            else if (x==1)
                button.setIcon(QUESTION);
            else
                button.setIcon(null);
            x=++x%3;
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
