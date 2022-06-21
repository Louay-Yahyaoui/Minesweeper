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
        ArrayList<Integer> OpenSpace=new ArrayList<Integer>();
        OpenSpace.add(index);
        if(x>0)
        {

                OpenSpace.add(x-1,y);
        } else if (x<width-1) {
                OpenSpace.add(x+1,y);
        }

        if(y+1<height)
        {
                OpenSpace.add(x,y+1);
            if(x>0)
            {
                    OpenSpace.add(x-1,y+1);
            }
            if(x<width-1)
            {
                    OpenSpace.add(x+1,y+1);
            }
        }
        if(y>0)
        {
                OpenSpace.add(x,y-1);
            if(x>0)
            {
                OpenSpace.add(x-1,y-1);
            }
            if(x<width-1)
            {
                OpenSpace.add(x+1,y-1);
            }
        }
        return OpenSpace;

    }

    public ArrayList<Integer> checkNearbyBombs(int x, int y,boolean up,boolean down,boolean right,boolean left)
    {
        ArrayList<Integer> nearbyEmpties=new ArrayList<Integer>();
        boolean fz1=up||right,fz2=up||left,fz3=down||right,fz4=down||left;
        //todo:debug this I'm tired,later fix the top of the panel
        nearbyEmpties.add(x+y*width);

//        if ((y<height-1)&&(isEmpty(x,y+1))&&up)
//            nearbyEmpties=doAccordingly(x,y+1,nearbyEmpties,true,false,false,false);
//
//        if((y>0)&&(isEmpty(x,y-1))&&down)
//            nearbyEmpties=doAccordingly(x,y-1,nearbyEmpties,false,true,false,false);
//      if(x<width-1)
//       {
//        if ((y<height-1)&&(isEmpty(x+1,y+1))&&fz1)
//          nearbyEmpties=doAccordingly(x+1,y+1,nearbyEmpties,true,false,true,false);
//
//        if ((y>0)&&(isEmpty(x+1,y-1))&&fz3)
//           nearbyEmpties=doAccordingly(x+1,y-1,nearbyEmpties,false,true,true,false);
//
//        if (isEmpty(x+1,y)&&right)
//            nearbyEmpties=doAccordingly(x+1,y,nearbyEmpties,false,false,true,false);
//        }
//        if(x>0)
//        {
//            if((y>0)&&(isEmpty(x-1,y-1))&&fz4)
//                nearbyEmpties=doAccordingly(x-1,y-1,nearbyEmpties,false,true,false,true);
//            if(isEmpty(x-1,y)&&left)
//                nearbyEmpties=doAccordingly(x-1,y,nearbyEmpties,false,false,false,true);
//            if ((y<height-1)&&(isEmpty(x-1,y+1))&&fz2)
//                nearbyEmpties=doAccordingly(x-1,y+1,nearbyEmpties,true,false,false,true);
//        }
        return nearbyEmpties;
    }

    private boolean isEmpty(int x, int y) {
        return (gameCoordinates[y][x]==0);
    }

    public ArrayList<Integer> doAccordingly(int x, int y,ArrayList<Integer>l,boolean up,boolean down,boolean right,boolean left)
    {
        if((!(l.contains(x+y*width)))&&(InBounds(x,y)))
        {
            ArrayList<Integer> safeSquares=checkNearbyBombs(x,y,up,down,right,left);
            l.addAll(safeSquares);
        }
        return l;
    }

    private boolean InBounds(int x, int y) {
        if((x<0)||(x>=width))
            return false;
        else
        {
            return ((y>=0)&&(y<height));
        }
    }
}
