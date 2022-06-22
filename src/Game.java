import java.util.*;

public class Game extends Thread{
    private static GameWindow gameWindow;
    private Thread thread;

    public Game(int height,int width,int bombNb)
    {
        GameMap gameMap=new GameMap(height,width,bombNb);
        gameWindow=new GameWindow(gameMap);
        //this.start();
    }

    public static void reset(boolean b)
    {
        if(b)
        {
            gameWindow.newGame();
        }
        else
            gameWindow.restart();

    }
    @Override
    public void run()
    {

    }

}
