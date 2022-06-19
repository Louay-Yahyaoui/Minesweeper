import java.util.*;

public class Game extends Thread{
    private GameWindow gameWindow;
    private GameMap gameMap;
    private Thread thread;

    public Game(int width,int height,int bombNb)
    {
        //gameWindow=new GameWindow();
        gameMap=new GameMap(height,width,bombNb);
        //this.start();
    }

    public static void reset(boolean b)
    {
        //animation

    }

    @Override
    public void run()
    {

    }

}
