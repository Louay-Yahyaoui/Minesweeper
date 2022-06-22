import java.util.*;

public class Game extends Thread{
    private static GameWindow gameWindow;
    private Thread thread;
    private int height;
    private int width;
    private static int bombNb;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getBombNb() {
        return bombNb;
    }

    public void setBombNb(int bombNb) {
        this.bombNb = bombNb;
    }

    public Game(int height, int width, int bombNb)
    {
        this.height=height;
        this.width=width;
        this.bombNb=bombNb;
        GameMap gameMap=new GameMap(this);
        gameWindow=new GameWindow(gameMap,this);
        //this.start();
    }

    public void reset(boolean b)
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
