package tcs;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Stage extends JPanel {
    int hhh;
    public  static final int COLS = 35;
    public  static final int ROWS = 35;
    public  static final int CELL_SIZE = 10;

    private Snake snake;
    private Cell food;

    public Stage(){
        snake = new Snake();
        food = createFood();
    }

    private Cell createFood(){
        Random random  = new Random();
        int x , y;
        do{
            x = random.nextInt(COLS);
            y = random.nextInt(ROWS);
        }while(snake.contains(x,y));
        return  new Cell(x,y, Color.yellow);
    }

    public void paint(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect(0,0,getWidth(),getHeight());

        g.setColor(Color.red);
        g.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);

        g.setColor(food.getColor());
        g.fill3DRect(food.getX()*CELL_SIZE,food.getY()*CELL_SIZE,CELL_SIZE,CELL_SIZE,true);
        snake.paint(g);
    }

    private Timer timer;
    private  void go(){
        if(timer == null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               creepForFood();
            }
        },0,1000/10);

        this.requestFocus();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:creepForFood(Snake.UP);break;
                    case  KeyEvent.VK_DOWN:creepForFood(Snake.DOWN);break;
                    case KeyEvent.VK_LEFT:creepForFood(Snake.LEFT);break;
                    case KeyEvent.VK_RIGHT:creepForFood(Snake.RIGHT);break;
                }
            }
        }

        );



    }

    private  void creepForFood(int direction){
        if(snake.hit(direction)){
            snake = new Snake();
            food = createFood();
        }
        boolean eat = snake.creep(direction,food);
        if(eat){
            food = createFood();
        }
        repaint();
    }
    private void creepForFood(){
        if(snake.hit()){
            snake = new Snake();
            food = createFood();
        }
        boolean eat = snake.creep(food);
        if(eat){
            food = createFood();
        }
        repaint();

    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("tanchishe");
        jFrame.setSize(500,500);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setLayout(null);
        Stage stage = new Stage();
        stage.setSize(CELL_SIZE*COLS,CELL_SIZE*ROWS);
        stage.setLocation(50,50);
        stage.setBorder(new LineBorder(Color.black));
        jFrame.add(stage);

        stage.go();
    }











}
