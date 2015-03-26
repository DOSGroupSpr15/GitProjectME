package me;

/**
 * Created by azhar on 3/13/15.
 */


import sun.font.FontConfigManager;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.*;

public class MovingObjectPanel extends JPanel{

    ArrayList<Ball2D> ball_list;


    public MovingObjectPanel(){

        this.ball_list= new ArrayList();
    }
    public void paint(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //drawing the bridge
        g2.setColor(Color.BLACK);
        int lane_width=80;
        int base_width=200;
        int base_height=340;

        g2.drawLine(300,300,900,300);
        g2.drawLine(300,300+lane_width,900,300+lane_width);
        g2.drawLine(300,300-lane_width,900,300-lane_width);

        //left base of bridge
        g2.draw3DRect(300-base_width,300-base_height/2,base_width,base_height,false);
        //right base of the bridge
        g2.draw3DRect(900,300-base_height/2,base_width,base_height,false);


        //drawing the persons
        for(Ball2D ball:ball_list){
            g.setColor(ball.c);
            Ellipse2D circle = new Ellipse2D.Double(ball.x,ball.y,ball.rad,ball.rad);
            g2.fill(circle);

            g.setColor(Color.white);
            g.drawString(ball.name,ball.x+37,ball.y+45);
        }

    }
    public void move(boolean resume){

        if(resume){
            for(Ball2D b: ball_list){
                b.incX(this.getWidth());
                b.incY(this.getHeight());
            }
            repaint();
        }

    }

    public void addBall(Ball2D b){
        ball_list.add(b);
    }

}