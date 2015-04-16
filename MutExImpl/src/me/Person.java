package me;

import java.awt.*;

/**
 * Created by azhar on 3/13/15.
 */
public class Person {


    int x;
    int y;
    int rad;
    Color c;
    int valX;
    int valY;
    String name;
    boolean isMoving=false;
    private short direction;


    // R&A parameters starts
    boolean _try;
    boolean _want;
    boolean _in;

    long t;

    boolean A[];

    int N;
    // R&A parameters ends


    public Person(int x, int y, int rad, Color c, int valX, int valY, String name, short direction) {
        this.x=x;
        this.y=y;
        this.rad=rad;
        this.c=c;
        this.valX=valX;
        this.valY=valY;
        this.name=name;
        this.direction=direction;

        _try = _want = _in=false;

        A=new boolean[4];
        for (int i=0;i<A.length;i++){
            A[i]=false;
        }

        N=0;
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

    public void mutexRA(){

    }
    public void send(){

        //implement all the sending things
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
