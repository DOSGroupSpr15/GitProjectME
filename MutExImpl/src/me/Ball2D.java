package me;

import java.awt.*;

/**
 * Created by azhar on 3/13/15.
 */
public class Ball2D{
    int x;
    int y;
    int rad;
    Color c;
    int valX;
    int valY;
    public Ball2D(int x, int y, int rad, Color c, int valX, int valY) {
        this.x=x;
        this.y=y;
        this.rad=rad;
        this.c=c;
        this.valX=valX;
        this.valY=valY;
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

}
