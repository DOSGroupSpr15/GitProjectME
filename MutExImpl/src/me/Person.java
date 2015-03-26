package me;

import java.awt.*;

/**
 * Created by azhar on 3/13/15.
 */
public class Person {
    public static final short DIRECTION_LEFT=0;
    public static final short DIRECTION_RIGHT=1;

    public static final short RED_PERSON=0;
    public static final short BLUE_PERSON=1;
    public static final short GREEN_PERSON=2;
    public static final short ORANGE_PERSON=3;

    int x;
    int y;
    int rad;
    Color c;
    int valX;
    int valY;
    String name;
    boolean isMoving=false;
    private short direction;
    public Person(int x, int y, int rad, Color c, int valX, int valY, String name, short direction) {
        this.x=x;
        this.y=y;
        this.rad=rad;
        this.c=c;
        this.valX=valX;
        this.valY=valY;
        this.name=name;
        this.direction=direction;
    }

    public void incX(int maxWidth){
        /*
        if(x>=0 && x<=(maxWidth-rad)){
            x+=valX;
        }
                */

        if(x<=0){
            valX=valX<0?(-valX):valX;
        }else if(x>=maxWidth-rad){
            valX=valX>0?(-valX):valX;
        }
        x+=valX;
    }

    public void incY(int maxHeight){
        /*
        if(y>=0 && y<=(maxHeight-rad)){
            y+=valY;
        }
                */
        if(y<=0){
            valY=valY<=0?(-valY):valY;
        }
        if(y>=maxHeight-rad){
            valY=valY>=0?(-valY):valY;
        }
        y+=valY;
    }
    public boolean isMoving(){
        return isMoving;
    }
    public void start(){
        isMoving = true;
    }
    public void stop(){
        isMoving=false;
    }
    private void toggleDirection(){
        direction=(short)(1-direction);
    }
    private short getDirection(){
        return this.direction;
    }
}
