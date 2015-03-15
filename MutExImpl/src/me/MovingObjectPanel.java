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
        for(Ball2D ball:ball_list){
            g.setColor(ball.c);
            Ellipse2D circle = new Ellipse2D.Double(ball.x,ball.y,ball.rad,ball.rad);
            g2.fill(circle);

            g.setColor(Color.white);
            g.drawString(ball.name,ball.x+37,ball.y+45);
        }

        g2.setColor(Color.BLACK);
        g2.draw3DRect(50,50,100,70,false);


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