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
    public void move(){

        for(Ball2D b: ball_list){
            b.incX(this.getWidth());
            b.incY(this.getHeight());
        }

        repaint();
    }

    public void addBall(Ball2D b){
        ball_list.add(b);
    }

    public static void main(String[] arg){

        final MovingObjectPanel obj = new MovingObjectPanel();
        Ball2D b_red=new Ball2D(500, 0, 80, Color.red, 5, 5,"A");
        Ball2D b_blue=new Ball2D(600, 0, 80, Color.blue, 5, 10,"B");
        obj.addBall(b_red);
        obj.addBall(b_blue);

        JFrame fr = new JFrame();
        fr.add(obj);
        fr.setVisible(true);
        fr.setSize(1200,600);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setTitle("Timed moving obj");


        JFrame control_frame = new JFrame();
        control_frame.setSize(400, 200);
        control_frame.setTitle("Control Frame");
        control_frame.setVisible(true);
        control_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel =new JPanel();
        control_frame.add(panel);
        JButton btn= new JButton("push");
        panel.add(btn);

        /*
        new Thread() {
            @Override
            public void run() {

                while(true){
                    obj.move();

                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
    */

        while(true){
            obj.move();

            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }

    }

}