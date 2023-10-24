import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Frame; 
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*; 

public class Conrad extends Frame{
    static boolean cont = true;
    public Conrad() 
    { 
        setVisible(true); 
        setSize(1000, 1000);
        setLayout(null);
        addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) 
            { 
                System.exit(0); 
            } 
        }); 
    } 
    public void paint(Graphics g, int[][] grid) 
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1000,1000);
        g.setColor(Color.BLACK);
        for (int i = 60; i<260; i+=20){
            for (int j = 60; j<260; j+=20){
                g.drawRect(i, j, 20, 20);
                if (grid[i/20-3][j/20-3]==1){
                    g.setColor(Color.BLACK);
                    g.fillRect(i,j,20,20);
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int height = 10;
        int width = 10;
        int[][] grid = new int[height][width];
        grid[0][0]=1;
        grid[5][5]=1;
        grid[4][5]=1;
        grid[5][4]=1;
        grid[3][2]=1;
        grid[2][3]=1;
        grid[3][3]=1;
        grid[9][9]=1;
        Conrad c = new Conrad();
        boolean first = true;
        Button b = new Button("Pause");
        b.setBounds(60,300,100,50);
        b.addActionListener(new ActionListener() {    
            public void actionPerformed (ActionEvent e) {    
                    if (cont){
                        cont = false;
                    } else {
                        cont = true;
                    }
                    System.out.print(cont);
                }    
            });
        c.add(b);
        while (true){
            if (cont){
                c.paint(c.getGraphics(),grid);
                if (!first){
                    grid = next(grid, 10, 10);
                } else {
                    first = false;
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e){
                System.out.print("fuck");
            }
        }
    }
    public static int[][] next(int grid[][], int height, int width){
        int[][] next = new int[10][10];
        for (int i = 0; i<height; i++){
            for (int j = 0; j<width; j++){
                int alive = 0;
                for (int x = -1; x < 2; x++){
                    for (int y = -1; y < 2; y++){
                        if (i==4 && j==3){
                        }
                        if (i+x >= 0 && i+x<height && j+y >= 0 && j+y<width){
                            if (grid[i+x][j+y]==1 && !(x==0 && y==0)){
                                alive++;
                            }
                        }
                    }
                }
                if (grid[i][j] == 1 && alive<2){
                    next[i][j]=0;
                } else if (grid[i][j] == 1 && alive>3){
                    next[i][j]=0;
                } else if (grid[i][j] == 0 && alive==3){
                    next[i][j]=1;
                } else {
                    next[i][j]=grid[i][j];
                }
            }
        }
        return next;
    }
 }