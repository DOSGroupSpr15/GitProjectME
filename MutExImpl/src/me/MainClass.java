package me;

import javax.swing.*;
import java.awt.*;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
/**
 * Created by azhar on 3/15/15.
 */
public class MainClass {

    static boolean resume=false;
    public static void main(String[] arg){


        try {
            // select Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

            // start application

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


        int initial_Base_x=100;
        int initial_Base_y=130;

        final AnimationPanel obj = new AnimationPanel();
        Person b_red=new Person(initial_Base_x, initial_Base_y, 80, Color.red, 5, 5,"R", Constants.DIRECTION_LEFT);
        Person b_blue=new Person(initial_Base_x, initial_Base_y+340-80, 80, Color.blue, 5, 10,"B", Constants.DIRECTION_LEFT);

        Person b_green=new Person(initial_Base_x+200+600+200-80, initial_Base_y, 80, Color.GREEN, 5, 5,"G", Constants.DIRECTION_RIGHT);
        Person b_orange=new Person(initial_Base_x+200+600+200-80, initial_Base_y+340-80, 80, Color.ORANGE, 5, 10,"O", Constants.DIRECTION_RIGHT);

        obj.addBall(b_red);
        obj.addBall(b_blue);
        obj.addBall(b_green);
        obj.addBall(b_orange);

        JFrame fr = new JFrame();
        fr.add(obj);
        fr.setVisible(true);
        fr.setSize(1200,600);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setTitle("Timed moving obj");




        ControlPanel controlPanel= new ControlPanel();
        controlPanel.setVisible(true);
        controlPanel.setResizable(false);
        /*
        JFrame control_frame = new JFrame();
        control_frame.setSize(400, 200);
        control_frame.setTitle("Control Frame");
        control_frame.setVisible(true);
        control_frame.setResizable(false);
        control_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        JPanel panel =new JPanel();
        control_frame.add(panel);
        JButton btn= new JButton("start/stop");
        panel.add(btn);


        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivateBtnActionPerformed(evt,obj);
                //resume= resume?false:true;
            }
        });
    */

        //obj.move(true);
        /*
        while(true){
            obj.move(true);

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

    private static void ActivateBtnActionPerformed(java.awt.event.ActionEvent evt, AnimationPanel obj) {
        // TODO add your handling code here:
        resume= resume?false:true;
    }

}
