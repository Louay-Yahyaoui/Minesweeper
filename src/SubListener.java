import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SubListener implements ActionListener {
    private Game game;

    public SubListener(GamePanel panel) {
        this.game = panel.getGameWindow().getGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand())
        {
            case "easy":
            game.setHeight(10);
            game.setWidth(10);
            game.setBombNb(GameMap.EASY);
            game.reset(true);
            break;
            case "medium":
                game.setHeight(16);
                game.setWidth(16);
                game.setBombNb(GameMap.MEDIUM);
                game.reset(true);
                break;
            case "hard":
            game.setHeight(16);
            game.setWidth(16);
            game.setBombNb(GameMap.HARD);
            game.reset(true);
            break;
            case "custom":
                System.out.println("nope");
                break;
        }
    }
}
