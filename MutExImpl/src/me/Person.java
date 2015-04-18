package me;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
    private int n;


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
        n=4;

        _try = _want = _in=false;

        A=new boolean[4];
        for (int i=0;i<A.length;i++){
            A[i]=false;
        }

        N=0;
        myself=this;

        receive();

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
        t=System.currentTimeMillis();
        for (int i=0;i<4;i++){
            if (i!=id){
                //sendReq request
                sendMessage(new Message(id,Constants.REQUEST,System.currentTimeMillis()),i);
            }
        }

        _try=false;
        _want=true;
    }
    private void receive(){

        // implement the receiver thread here
        // need to call the method from the constructor

        try {
            serverSocket= new ServerSocket(Constants.LISTENING_PORTS[this.id]);
            System.out.println("Person "+Constants.PERSON_NAMES[id]+" is listening to port "+Constants.LISTENING_PORTS[id]);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        receiving=true;

        new Thread() {
            @Override

            public void run() {
                System.out.println("Listening thread started for person "+Constants.PERSON_NAMES[id]);

                while(receiving){

                    try {

                        Socket client= serverSocket.accept();
                        Message m = (Message) new ObjectInputStream(client.getInputStream()).readObject();
                        System.out.println("Person "+Constants.PERSON_NAMES[id]+" got "+Constants.MESSAGE_TYPE[m.type]+" type message from Person "+Constants.PERSON_NAMES[m.sender]);
                        if((m.type==Constants.REQUEST)&&(!_want || m.time_stamp<t)){
                            sendACK(m.sender);

                        }else if ((m.type==Constants.REQUEST)&&(_want && m.time_stamp>t)){
                            A[m.sender]=true;

                        }else if (m.type==Constants.ACK){
                            N++;
                            if (N==(n-1)){
                                //now can enter the critical section

                                new Thread(){
                                    @Override
                                    public void run(){
                                        while (x<1000){
                                            //incX(1200);
                                            x=x+valX;
                                            try {
                                                Thread.sleep(50);
                                            }catch (Exception ex){

                                            }
                                        }

                                    }



                                }.run();
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("Exception in receiver thread of person "+Constants.PERSON_NAMES[id]);
                    }

                }
            }
        }.start();

    }

    private void sendACKtoALL(){
        for (int i=0;i<4;i++){
            if (A[i]){
                //sendReq request
                sendACK(i);
                A[i]=false;
            }
        }
    }
    private void sendACK(int to_personID){
        //send ack to the specified person with id
        sendMessage(new Message(id,Constants.ACK,System.currentTimeMillis()),to_personID );
    }


    private void sendMessage(Message m,int to_Person){

        Socket client=null;
        ObjectOutputStream objectOutputStream=null;

        try {
            client= new Socket(Constants.serverIP,Constants.LISTENING_PORTS[to_Person]);
            objectOutputStream =  new ObjectOutputStream(client.getOutputStream());
            objectOutputStream.writeObject(m);
        }catch (Exception ex){
            System.out.println("Exception while connecting to person "+Constants.PERSON_NAMES[to_Person]+"\n"+ex);

        }
        finally {
            /*
            try {
                client.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            try {
                objectOutputStream.close();

            }catch (Exception ex){
                ex.printStackTrace();
            }
            */
        }

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
