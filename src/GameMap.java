import java.util.TreeSet;

import static java.lang.Math.*;

public class GameMap
{
    private int height;
    private int width;
    private int bombNb;
    private TreeSet<Integer> bombs;

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
    }
    private void initClasses()
    {
        bombs=generateBombs(height,width,bombNb);
        gameCoordinates=generateMap(height, width, bombNb);
    }
    private int[][] generateMap(int height,int width,int bombNb)
    {
        int[][]map=new int[height][width];
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
                map[i][j]=0;
        bombs=generateBombs(height,width,bombNb);
        for (int x:bombs)
        {
            map[x/(width)][x%(width)]=-1;
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
        if (j + 1 != width)
        {
            if (bombs.contains(i * width + j + 1))
                bombCount++;
        if (bombs.contains((i + 1) * width + j + 1))
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
    private TreeSet<Integer> generateBombs(int height, int width, int bombNb)
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
    public boolean isBomb(int x,int y)
    {
        return bombs.contains(x*height+y);
    }

    public void reset()
    {
        initClasses();
    }
}
