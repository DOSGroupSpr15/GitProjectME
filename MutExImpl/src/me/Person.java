package me;

import com.sun.xml.internal.ws.resources.SenderMessages;

import java.awt.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by azhar on 3/13/15.
 */

public class Person {


    int id;
    Person myself;
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

    // variables for network connectivity
    ServerSocket serverSocket;

    //variables for network connectivity ends here

    private boolean receiving;

    public Person(int id, int x, int y, int rad, Color c, int valX, int valY, String name, short direction) {
        this.id=id;
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
        myself=this;

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

    public boolean sendReq(){

        //implement the sender

        if (_want || _in){
           return false;
        }else {
            _try=true;
            sendReqToAll();
            return true;
        }
    }

    public void sendReqToAll(){
        //sendReq request to all
        for (int i=0;i<4;i++){
            if (i!=id){
                //sendReq request
            }
        }

        _try=false;
    }
    private void receive(){

        // implement the receiver thread here
        // need to call the method from the constructor

        try {
            serverSocket= new ServerSocket(Constants.LISTENING_PORTS[this.id]);
        }catch (Exception ex){

        }
        receiving=true;

        new Thread() {
            @Override

            public void run() {

                while(receiving){

                    try {
                        Socket client= serverSocket.accept();
                        //Thread.sleep(50);
                    } catch (Exception e) {
                        System.out.println("Exception in receiver thread of person "+Constants.PERSON_NAMES[id]);
                    }

                }
            }
        }.start();

    }

    private void sendACKtoALL(){
        for (int i=0;i<4;i++){
            if (i!=id){
                //sendReq request
                sendACK(i);
            }
        }
    }
    private void sendACK(int to_personID){
        //send ack to the specified person with id
        sendMessage(new Message(id,Constants.ACK,t),to_personID );
    }
    private void sendMessage(Message m,int to_Person){

    }

    public void stopReceiving(){
        receiving=false;
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
