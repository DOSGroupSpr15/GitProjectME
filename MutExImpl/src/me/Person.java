package me;

import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

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
    int velocity;
    String name;
    boolean isMoving=false;
    private short direction;
    private int n;
    private int parking_spot;

    Random random=null;



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

    public Person(int id, int x, int y, int rad, Color c, String name, short direction,int parking_spot) {
        this.id=id;
        this.x=x;
        this.y=y;
        this.rad=rad;
        this.c=c;
        this.velocity = Constants.BASE_VELOCITY;
        this.name=name;
        this.direction=direction;
        this.parking_spot=parking_spot;
        this.random=new Random();

        if (this.direction==Constants.DIRECTION_LEFT){
            Constants.LEFT_PARKING_SPOTS[this.parking_spot]=true;
        }else {
            Constants.RIGHT_PARKING_SPOTS[this.parking_spot]=true;
        }


        n=4;


        _try = _want = _in=false;

        A=new boolean[4];
        for (int i=0;i<A.length;i++){
            A[i]=false;
        }

        N=0;
        myself=this;

        //receive();
        //movePersonInCity();

    }

    public void changeX(int sign){

        x= x+ sign*velocity;
        if(x<100)
            x=100;
        else if (x>1020)
            x=1020;
    }

    public void changeY(int sign){

        y= y+ sign*velocity;

        if (y<130)
            y=130;
        else if (y>390)
            y=390;
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
    public void receive(){

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
                        System.out.println("Person "+Constants.PERSON_NAMES[id]+" got "+Constants.MESSAGE_TYPE[m.type]+" from Person "+Constants.PERSON_NAMES[m.sender]);
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

                                        _in = true;
                                        // critical section begins
                                        //freeParkingSpot();
                                        if (direction==Constants.DIRECTION_LEFT){
                                            x=Constants.LEFT_START[0];
                                            y=Constants.LEFT_START[1];

                                            while (x<1020){
                                                changeX(1);
                                                try {
                                                    Thread.sleep(100);
                                                }catch (Exception ex){

                                                }
                                            }
                                            /*
                                            while (x<Constants.RIGHT_END[0]){
                                                //changeX(1200);
                                                x+= velocity;
                                                try {
                                                    Thread.sleep(100);
                                                }catch (Exception ex){

                                                }
                                            }
                                            */
                                        }else {
                                            x=Constants.RIGHT_START[0];
                                            y=Constants.RIGHT_START[1];

                                            while (x>100){
                                                changeX(-1);
                                                try {
                                                    Thread.sleep(100);
                                                }catch (Exception ex){

                                                }
                                            }
                                            /*
                                            while (x>Constants.LEFT_END[0]){
                                                //changeX(1200);
                                                x-= velocity;
                                                try {
                                                    Thread.sleep(100);
                                                }catch (Exception ex){

                                                }
                                            }
                                            */
                                        }
                                        direction=(short)(-1*direction);
                                        //park();
                                        // critical section ends
                                        _want=false;
                                        sendACKtoALL();

                                    }

                                }.start();
                            }

                        }

                    } catch (Exception e) {
                        System.out.println("Exception in receiver thread of person "+Constants.PERSON_NAMES[id]);
                    }

                }
            }
        }.start();

    }

    public void movePersonInCity(){

        new Thread(){
            @Override
            public void run(){

                while (true){

                    if (!_want && !_in){

                        if(x == 100 && y <390){

                            if (y==130 ){

                                direction=Constants.DIRECTION_LEFT;
                                if( random.nextBoolean()){  //randomly deciding if the person wants to cross the bridge
                                    sendReq();
                                }else {
                                    changeY(1);
                                }


                            }else {
                                changeY(1);
                            }

                        }else if (x < 1020 && y == 390){

                            changeX(1);

                        }else if (x == 1020 && y >130){

                            if(y==390){

                                direction=Constants.DIRECTION_RIGHT;

                                if(random.nextBoolean()){   //randomly deciding if the person wants to cross the bridge
                                    sendReq();
                                }else {
                                    changeY(-1);
                                }

                            }else {
                                changeY(-1);
                            }

                        }else if(x >100 && y == 130){

                            changeX(-1);
                        }
                    }

                    try {

                        Thread.sleep(100);
                    }catch (Exception ex){

                    }
                }
            }
        }.start();
    }

    private void sendACKtoALL(){

        _in = false;
        N=0;

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

        }

    }

    private void freeParkingSpot(){
        if(direction==Constants.DIRECTION_LEFT){
            Constants.LEFT_PARKING_SPOTS[parking_spot]=false;
        }else {
            Constants.RIGHT_PARKING_SPOTS[parking_spot]=false;

        }
    }
    private void park(){
        if (direction==Constants.DIRECTION_LEFT){
            for (int i=0;i<4;i++){
                if (!Constants.LEFT_PARKING_SPOTS[i]){
                    parking_spot=i;
                    Constants.LEFT_PARKING_SPOTS[i]=true;
                    x=Constants.LEFT_PARKING_COORDINATES[i][0];
                    y=Constants.LEFT_PARKING_COORDINATES[i][1];
                    break;
                }
            }
        }else {
            for (int i=0;i<4;i++){
                if (!Constants.RIGHT_PARKING_SPOTS[i]){
                    Constants.RIGHT_PARKING_SPOTS[i]=true;
                    parking_spot=i;
                    x=Constants.RIGHT_PARKING_COORDINATES[i][0];
                    y=Constants.RIGHT_PARKING_COORDINATES[i][1];
                    break;
                }
            }
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
