package me;

/**
 * Created by azhar on 3/13/15.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
        }


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

        MovingObjectPanel obj = new MovingObjectPanel();
        Ball2D b_red=new Ball2D(0, 0, 50, Color.red, 5, 5);
        Ball2D b_blue=new Ball2D(0, 0, 50, Color.blue, 5, 10);
        obj.addBall(b_red);
        obj.addBall(b_blue);
        JFrame fr = new JFrame();
        fr.add(obj);
        fr.setVisible(true);
        fr.setSize(800,700);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setTitle("Timed moving obj");


        while(true){
            obj.move();

            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }

}