//region
//imports
import java.util.concurrent.TimeUnit;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Frame; 
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Button;
import java.awt.Component;
import javax.swing.*;  
import java.awt.*;
import java.awt.event.*; 
import java.util.Random;
//endregion
public class Conrad extends Frame{
    //region
    //Set up
    static int counter = 0;
    static int curr = 30;
    static boolean cont = true;
    static int[][] grid = new int[10][10];
    static Conrad c = new Conrad();

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
    //endregion
    //region
    //Paint
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
    //endregion
    public static void main(String[] args) {
        //region
        //Pause
        Button b = new Button("Pause");
        b.setBounds(60,400,100,50);
        b.addActionListener(new ActionListener() {    
            public void actionPerformed (ActionEvent e) {    
                    if (cont){
                        cont = false;
                        b.setLabel("Continue");
                    } else {
                        cont = true;
                        b.setLabel("Pause");
                    }
                }    
            });
        c.add(b);
        //endregion
        //region
        //Scramble
        Button scramble = new Button("Scramble");
        scramble.setBounds(60,340,120,50);
        scramble.addActionListener(new ActionListener() {    
            public void actionPerformed (ActionEvent e) {    
                    for (int i = 0; i<10; i++){
                        for (int j = 0; j<10; j++){
                            Random random = new Random();
                            int temp = random.nextInt(2);
                            grid[i][j]=temp;
                        }
                    }
                }    
            });
        c.add(scramble);
        //endregion
        //region
        //speed
        TextField speed = new TextField("Speed (0-50)");
        speed.setBounds(60,280,150,50);
        speed.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                String temp = speed.getText();
                int buffer = Integer.parseInt(temp);
                if (buffer >= 0 && buffer <= 50){
                    curr = buffer;
                    counter = 0;
                } 
            }
        });
        c.add(speed);
        //endregion
        //region
        //Load buttons
        for (int i = 0; i<200; i+=20){
            final int x = i;
            for (int j=0; j<200; j+=20){
                final int y = j;
                JButton temp = new JButton();
                temp.updateUI();
                temp.setBounds(i+60,j+60,20,20);
                temp.setOpaque(true);
                temp.setBackground(Color.WHITE);
                temp.addActionListener(new ActionListener() {    
                public void actionPerformed (ActionEvent e) {    
                        if(grid[x/20][y/20]==1){
                            grid[x/20][y/20]=0;
                        } else {
                            grid[x/20][y/20]=1;
                        }
                    }    
                });
                c.add(temp);
                }
        }
        //endregion
        //region
        //Execution
        while (true){
            if (counter == 51-curr){
                if (cont){
                    grid = next(grid, 10, 10);
                }
                counter = 0;
            }
            c.paint(c.getGraphics(),grid);
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e){
                System.out.print("fuck");
            }
            counter++;
        }
        //endregion
    }
    //region
    //Update grid
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
    //endregion
 }