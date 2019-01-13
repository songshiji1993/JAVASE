package tcs;

import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private int currenDirection = DOWN;

    private ArrayList<Cell> cells = new ArrayList<Cell>();

    private Color color;

    public static final int UP = 1;
    public static final int DOWN = -1;
    public static final int RIGHT = 2;
    public static final int LEFT = -2;

    public Snake() {
        color = Color.red;
        for (int x = 0, y = 0, i = 0; x < 12; x++) {
            cells.add(new Cell(x, y, color));
        }
    }

    public boolean contains(int x, int y) {
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            if (cell.getX() == x && cell.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return cells.toString();
    }

    public Cell createHead(int direction) {
        Cell head = cells.get(0);
        int x = head.getX();
        int y = head.getY();
        switch (direction) {
            case DOWN:
                y++;
                break;
            case UP:
                y--;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
        return new Cell(x, y);
    }

    public void creep() {
        cells.remove(cells.size() - 1);
        cells.add(createHead(currenDirection));
    }


    public boolean creep(Cell food) {
        Cell head = createHead(currenDirection);
        boolean eat = head.equals(food);
        if (!eat) {
            cells.remove(cells.size() - 1);
        }
        cells.add(0, head);
        return eat;
    }

    public boolean creep(int direction, Cell food) {
        if (direction + currenDirection == 0) {
            return false;
        }
        this.currenDirection = direction;//this 调用本类的变量
        Cell head = createHead(currenDirection);
        boolean eat = head.equals(food);
        if (!eat) {
            cells.remove(cells.size() - 1);
        }
        cells.add(0, head);
        return eat;
    }

    public boolean hit(int direction) {
        if (direction + currenDirection == 0) {
            return false;
        }
        Cell head = createHead(direction);
        int x = head.getX();
        int y = head.getY();
        if (x < 0 || x >= Stage.COLS || y < 0 || y > Stage.ROWS) {
            return true;
        }
        for (int i = 0; i < cells.size(); i++) {
            Cell c = cells.get(i);
            if (c.equals(head)) {
                return true;
            }
        }
        return false;

    }

    public boolean hit(){
        return  hit(currenDirection);
    }

    public  void paint(Graphics g){
        g.setColor(this.color);
        for (int i = 0;i<cells.size();i++){
            Cell cell = cells.get(i);
            int x = cell.getX();
            int y = cell.getY();
            g.fill3DRect(x*Stage.CELL_SIZE,y*Stage.CELL_SIZE,Stage.CELL_SIZE,Stage.CELL_SIZE,true);
        }



    }










}
