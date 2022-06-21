import java.util.ArrayList;
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
        showBombsandMap();
    }
    private void initClasses()
    {
        bombs=generateBombs(bombNb);
        gameCoordinates=generateMap(height, width, bombNb);
    }
    private int[][] generateMap(int height,int width,int bombNb)
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
    private TreeSet<Integer> generateBombs( int bombNb)
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
        ArrayList<Integer> OpenSpace=new ArrayList<Integer>(height*width);
        OpenSpace.add(index);
        if(x>0)
        {
               OpenSpace.addAll(checkNearbyBombs(x-1,y,false,false,false,true));
        }
        if (x<width-1) {
               OpenSpace.addAll(checkNearbyBombs(x+1,y,false,false,true,false));
        }
        if(y+1<height)
        {
            OpenSpace.addAll(checkNearbyBombs(x,y+1,true,false,false,false));
            if(x>0)
            {
                OpenSpace.addAll(checkNearbyBombs(x-1,y+1,false,false,false,true));
            }
            if(x<width-1)
            {
                OpenSpace.addAll(checkNearbyBombs(x+1,y+1,true,false,true,false));
            }
        }
        if(y>0)
        {
            OpenSpace.addAll(checkNearbyBombs(x,y-1,false,true,false,false));
            if(x>0)
            {
                OpenSpace.addAll(checkNearbyBombs(x-1,y-1,false,true,false,true));
            }
            if(x<width-1)
            {
                OpenSpace.addAll(checkNearbyBombs(x+1,y-1,false,true,true,false));
            }
        }
        return OpenSpace;

    }

    public ArrayList<Integer> checkNearbyBombs(int x, int y,boolean up,boolean down,boolean right,boolean left)
    {
        ArrayList<Integer> nearbyEmpties=new ArrayList<Integer>();
        boolean fz1=up||right,fz2=up||left,fz3=down||right,fz4=down||left;
        nearbyEmpties.add(x+y*width);
        //TODO:BY HAAAAAAAAANNNNNNNNNNNNDDDDDDDDDDDDDDD FFS
//        if(x>0)
//        {
//            if(hasEmptyNeighbour(x-1,y)&&left)
//                nearbyEmpties.addAll(checkNearbyBombs(x-1,y,false,false,false,true));
//            if((y>0)&&hasEmptyNeighbour(x-1,y-1)&&fz4)
//                nearbyEmpties.addAll(checkNearbyBombs(x-1,y-1,false,true,false,true));
//            if((y<height-1)&&hasEmptyNeighbour(x-1,y+1)&&fz2)
//                nearbyEmpties.addAll(checkNearbyBombs(x-1,y+1,true,false,false,true));
//
//        }
//        if((y>0)&&hasEmptyNeighbour(x,y-1)&&down)
//            nearbyEmpties.addAll(checkNearbyBombs(x,y-1,false,true,false,false));
//        if((y<height-1)&&hasEmptyNeighbour(x,y+1)&&up)
//            nearbyEmpties.addAll(checkNearbyBombs(x,y+1,true,false,false,false));
//        if(x<width-1)
//        {
//            if(hasEmptyNeighbour(x+1,y)&&right)
//                nearbyEmpties.addAll(checkNearbyBombs(x+1,y,false,false,true,false));
//            if((y>0)&&hasEmptyNeighbour(x+1,y-1)&&fz3)
//                nearbyEmpties.addAll(checkNearbyBombs(x+1,y-1,false,true,false,true));
//            if((y<height-1)&&hasEmptyNeighbour(x+1,y+1)&&fz1)
//                nearbyEmpties.addAll(checkNearbyBombs(x+1,y+1,true,false,true,true));
//        }
        return nearbyEmpties;
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