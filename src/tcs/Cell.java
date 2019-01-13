package tcs;

import java.awt.*;

public class Cell {

    private  int  x;
    private int y;
    private Color color;


    public Cell(int x,int y){
       this.x = x;
       this.y = y;
    }

    public  Cell(int x,int y,Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public  Color getColor(){
        return  color;
    }
    public  int getX(){
        return  x;
    }
    public  int getY(){
        return  y;
    }
    public String toString(){
        return  "["+x+","+y+"]";
    }

    public boolean equals(Object obj){
        if(obj == null){
            return  false;
        }
        if(this==obj){
            return true;
        }
        if(obj instanceof Cell){
            Cell other = (Cell)obj;
            return  this.x == other.x && this.y == other.y;
        }
        return  false;
    }





}
