import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Listener implements ActionListener, ItemListener {

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getActionCommand().equals("new game"))
//            Game.playAgain(true);
//        else if (e.getActionCommand().equals("play again"))
//            Game.playAgain(false);
        Game.reset((e.getActionCommand().equals("new game"))?true:((e.getActionCommand().equals("play again"))?false:null));
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        System.out.println(e.toString());
    }
}
