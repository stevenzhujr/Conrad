import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Frame; 

public class Conrad extends Frame{
    public Conrad() 
    { 
        setVisible(true); 
        setSize(1000, 1000); 
        addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) 
            { 
                System.exit(0); 
            } 
        }); 
    } 
    public void paint(Graphics g, int i, int j) 
    { 
        g.drawRect(i, j, 100, 50); 
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int height = 10;
        int width = 10;
        int[][] grid = new int[height][width];
        grid[5][5]=1;
        grid[4][5]=1;
        grid[5][4]=1;
        grid[3][2]=1;
        grid[2][3]=1;
        grid[3][3]=1;
        grid[9][9]=1;
        for (int i = 0; i<height; i++){
            for (int j = 0; j<width; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println(" ");
        }
        Conrad c = new Conrad();
        c.paint(c.getGraphics(),100,100);
        c.paint(c.getGraphics(), 100, 100);
        int i = 0;
        int j = 0;
        while (true){
            c.paint(c.getGraphics(),i,j);
            i++;
            j++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e){
                System.out.print("fuck");
            }
            
        }
    }
    public static int[][] next(int grid[][], int height, int width){
        int[][] next = new int[height][width];
        next = grid;
        for (int i = 0; i<height; i++){
            for (int j = 0; j<width; j++){
                int alive = 0;
                for (int x = -1; x < 2; x++){
                    for (int y = -1; y < 2; y++){
                        if (i+x>=0 && i+x<height && j+y>=0 && j+y<width){
                            if (grid[i+x][j+y]==1 && (x!=0 || y!=0)){
                                alive++;
                            }
                        }
                    }
                }
                if (next[i][j] == 1 && alive<2){
                    next[i][j]=0;
                } else if (next[i][j] == 1 && alive>3){
                    next[i][j]=0;
                } else if (next[i][j] == 0 && alive==3){
                    next[i][j]=1;
                }
            }
        }
        return next;
    }
 }