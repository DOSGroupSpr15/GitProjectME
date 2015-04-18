package me;

/**
 * Created by azhar on 4/14/15.
 */
public class Constants {


    //MESSAGE TYPE
    public static final int REQUEST=0;
    public static final int ACK=1;

    // DIRECTION CONSTANT
    public static final short DIRECTION_LEFT=0;
    public static final short DIRECTION_RIGHT=1;

    // COLOR CONSTANT
    public static final int RED_PERSON=0;
    public static final int BLUE_PERSON=1;
    public static final int GREEN_PERSON=2;
    public static final int ORANGE_PERSON=3;

    //listening ports for server sockets
    public static final int[] LISTENING_PORTS= {8833,8835,8837,8839};
    public static final String serverIP ="localHost";

    //person names
    public static final String[] PERSON_NAMES={"RED","BLUE","GREEN","ORANGE"};
    public static final String[] MESSAGE_TYPE={"REQUEST","ACK"};


}
