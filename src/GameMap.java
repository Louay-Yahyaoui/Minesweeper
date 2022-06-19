import java.util.TreeSet;

import static java.lang.Math.*;

public class GameMap
{
    private int height;
    private int width;
    private int bombNb;
    private TreeSet<Integer> bombs;
    private int[][] gameCoordinates;

    public GameMap(int height, int width, int bombNb)
    {
        this.height = height;
        this.width = width;
        this.bombNb = bombNb;
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
                if (!isBomb(map[i][j])) {
                    map[i][j] = searchNearbyBombs(i, j);
                }
            }
        System.out.println(bombs);
        for (int i=0;i<height;i++)
        {
            for (int j=0;j<width;j++) {
                System.out.format("%02d", map[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
        return map;
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

    private boolean isEdge(int i, int j)
    {
        if(((i==0) ||(i==height-1))||((j==0)||(j==width-1)))
            return true;
        return false;
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
    public boolean isBomb(int c)
    {
        return c==-1;
    }
}
