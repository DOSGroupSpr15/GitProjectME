package me;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSetMetaData;

/**
 * Created by azhar on 3/15/15.
 */
public class MainClass {

    static boolean resume=false;
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
        JButton btn= new JButton("start/stop");
        panel.add(btn);

        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivateBtnActionPerformed(evt,obj);
            }
        });

        while(true){
            obj.move(resume);

            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }
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
    }

    private static void ActivateBtnActionPerformed(java.awt.event.ActionEvent evt, MovingObjectPanel obj) {
        // TODO add your handling code here:
        resume= resume?false:true;
    }

}
