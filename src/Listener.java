import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Listener implements ActionListener, ItemListener {
    private int height;
    private int width;
    private int bombNb;


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Settings")
        {
            //change height and width and bombnb
        }
        
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        System.out.println(e.toString());
    }
}
