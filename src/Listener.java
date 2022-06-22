import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Listener implements ActionListener, ItemListener {
    private Game game;


    public Listener(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.reset(!e.getActionCommand().equals("play again"));
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        System.out.println(e.toString());
    }
}
