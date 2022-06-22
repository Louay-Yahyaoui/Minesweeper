import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Vector;
import javax.swing.*;
import static java.lang.Math.*;

public class GameMap
{
    private int height;
    private int width;
    private int bombNb;
    private TreeSet<Integer> bombs;
    private Vector<Integer> checks;

    public int[][] getGameCoordinates() {
        return gameCoordinates;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private int[][] gameCoordinates;

    public GameMap(int height, int width, int bombNb)
    {
        this.height = height;
        this.width = width;
        this.bombNb = bombNb;
        initClasses();
        showBombsandMap();
    }
    private void initClasses()
    {
        bombs=generateBombs();
        gameCoordinates=generateMap();
        checks=new Vector<Integer>(height*width);
    }
    private int[][] generateMap()
    {
        int[][]map=new int[height][width];
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
                map[i][j]=0;
        for (int x:bombs)
        {
            map[x/width][x%width]=-1;
        }
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++) {
                if (!isBomb(i,j)) {
                    map[i][j] = searchNearbyBombs(i, j);
                }
            }
        return map;
    }
    private void showBombsandMap()
    {
        System.out.println(bombs);
        for (int i=0;i<height;i++)
        {
            for (int j=0;j<width;j++) {
                System.out.format("%02d", gameCoordinates[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    private int searchNearbyBombs(int i, int j) {
        int bombCount = 0;

        if(bombs.contains((i+1)*width+j))
            bombCount++;

        if(bombs.contains((i-1)*width+j))
            bombCount++;
        if (j + 1 !=height)
        {
            if (bombs.contains(i* width + j + 1))
                bombCount++;
            if (bombs.contains((i+ 1) * width + j + 1))
                bombCount++;
            if (bombs.contains((i - 1) * width + j + 1))
                bombCount++;
        }
        if(j!=0)
        {
            if(bombs.contains(i*width+j-1))
                bombCount++;
            if(bombs.contains((i+1)*width+j-1))
                bombCount++;
            if(bombs.contains((i-1)*width+j-1))
                bombCount++;
        }

        return bombCount;
    }
    private TreeSet<Integer> generateBombs()
    {
        int randX,randY;
        TreeSet<Integer> bombs=new TreeSet<Integer>();
        while(bombs.size()<bombNb)
        {
            randY= (int) floor(random()*height);
            randX= (int) floor(random()*width);
            bombs.add(randY * (width) + randX);
        }
        return bombs;
    }
    public boolean isBomb(int y,int x)
    {
        return bombs.contains(y*width+x);
    }

    public void reset()
    {
        initClasses();
    }

    public ArrayList<Integer> OpenUp(int x,int y)
    {
        int index=x+y*width;
        ArrayList<Integer> OpenSpace=new ArrayList<Integer>();
        if(!isChecked(x,y))
        {
            if(hasEmptyNeighbour(x,y))
            {
                OpenSpace.add(index);
                checks.add(index);
                if(isEmpty(x,y))
                {
                    if(x>0)
                    {
                        if(!isChecked(x-1,y))
                            OpenSpace.addAll(OpenUp(x-1,y));
                    }
                    if (x<width-1) {
                        if(!isChecked(x+1,y))
                            OpenSpace.addAll(OpenUp(x+1,y));
                    }
                    if(y+1<height)
                    {
                        if(!isChecked(x,y+1))
                            OpenSpace.addAll(OpenUp(x,y+1));
                        if((x>0)&&(!isChecked(x-1,y+1)))
                        {
                            OpenSpace.addAll(OpenUp(x-1,y+1));
                        }
                        if((x<width-1)&&(!isChecked(x+1,y+1)))
                        {
                            OpenSpace.addAll(OpenUp(x+1,y+1));
                        }
                    }
                    if(y>0)
                    {
                        if (!isChecked(x,y-1))
                            OpenSpace.addAll(OpenUp(x,y-1));
                        if((x>0)&&(!isChecked(x-1,y-1)))
                        {
                            OpenSpace.addAll(OpenUp(x-1,y-1));
                        }
                        if((x<width-1)&&(!isChecked(x+1,y-1)))
                        {
                            OpenSpace.addAll(OpenUp(x+1,y-1));
                        }
                    }
                }
            }
        }
        return OpenSpace;

    }

    private boolean isChecked(int x, int y) {
        return checks.contains(x+y*width);
    }

    private boolean isEmpty(int x, int y) {
        return (gameCoordinates[y][x]==0);
    }

    private boolean hasEmptyNeighbour(int x, int y) {
        if(isEmpty(x,y))
            return true;
        if (x > 0) {
            if (isEmpty(x - 1, y))
                return true;
            if ((y > 0) && (isEmpty(x - 1, y - 1)))
                return true;
            if ((y < height - 1) && (isEmpty(x - 1, y + 1)))
                return true;
        }

        if (x < width - 1) {
            if (isEmpty(x + 1, y))
                return true;
            if ((y > 0) && (isEmpty(x + 1, y - 1)))
                return true;
            if ((y < height - 1) && (isEmpty(x + 1, y + 1)))
                return true;
        }
        if ((y > 0) && isEmpty(x, y - 1))
            return true;
        return (y<height-1)&&isEmpty(x,y+1);
    }

}