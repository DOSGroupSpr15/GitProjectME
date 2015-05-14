package me;

/**
 * Created by azhar on 4/14/15.
 */
public class Constants {


    //MESSAGE TYPE
    public static final int REQUEST=0;
    public static final int ACK=1;

    // DIRECTION CONSTANT
    public static final short DIRECTION_LEFT=1;
    public static final short DIRECTION_RIGHT=-1;

    // COLOR CONSTANT
    public static final int RED_PERSON=0;
    public static final int BLUE_PERSON=1;
    public static final int GREEN_PERSON=2;
    public static final int ORANGE_PERSON=3;

    //listening ports for server sockets
    public static final int[] LISTENING_PORTS= {8733,8735,8737,8739};
    public static final String serverIP ="localHost";

    //person names
    public static final String[] PERSON_NAMES={"RED","BLUE","GREEN","ORANGE"};
    public static final String[] MESSAGE_TYPE={"REQUEST","ACK"};





    //BRIDGE MEASUREMENT CONSTANTS

    static int initial_Base_x=100;
    static int initial_Base_y=130;

    static int BASE_DIM_X=200;
    static int BASE_DIM_Y=340;

    static int BRIDGE_LENGTH=600;

    //for left parking
    public static int LEFT_PARKING_COORDINATES[][]= new int[4][2];
    //starts from bottom left then clock wise

    static {
        LEFT_PARKING_COORDINATES[0][0]=initial_Base_x;
        LEFT_PARKING_COORDINATES[0][1]=initial_Base_y+340-80;

        LEFT_PARKING_COORDINATES[1][0]=initial_Base_x;
        LEFT_PARKING_COORDINATES[1][1]=initial_Base_y;

        LEFT_PARKING_COORDINATES[2][0]=initial_Base_x+200-80;
        LEFT_PARKING_COORDINATES[2][1]=initial_Base_y;

        LEFT_PARKING_COORDINATES[3][0]=initial_Base_x+200-80;
        LEFT_PARKING_COORDINATES[3][1]=initial_Base_y+340-80;
    }

    //left parking ends


    //RIGHT PARKING BRIDGE BEGINS
    static int RIGHT_PARKING_COORDINATES[][]= new int[4][2];
    //starts from bottom right corner and then anti clock wise
    static {
        RIGHT_PARKING_COORDINATES[0][0]=LEFT_PARKING_COORDINATES[3][0]+600+200;
        RIGHT_PARKING_COORDINATES[0][1]=LEFT_PARKING_COORDINATES[3][1];

        RIGHT_PARKING_COORDINATES[1][0]=LEFT_PARKING_COORDINATES[2][0]+600+200;
        RIGHT_PARKING_COORDINATES[1][1]=LEFT_PARKING_COORDINATES[2][1];

        RIGHT_PARKING_COORDINATES[2][0]=LEFT_PARKING_COORDINATES[2][0]+600+80;
        RIGHT_PARKING_COORDINATES[2][1]=LEFT_PARKING_COORDINATES[2][1];

        RIGHT_PARKING_COORDINATES[3][0]=LEFT_PARKING_COORDINATES[3][0]+600+80;
        RIGHT_PARKING_COORDINATES[3][1]=LEFT_PARKING_COORDINATES[3][1];
    }


    //RIGHT PARKING BASE ENDS

    //START AND END POINTS OF THE BRIDGE
    static int LEFT_START[]= {300-80,300-40}; // starting position of a person starting from left end of the bridge
    //static int RIGHT_END[]={LEFT_START[0]+BRIDGE_LENGTH+80,300}; // ending position of a person starting from left side of the bride
    static int RIGHT_START[]={900,300-40}; // starting position of a person starting from right end of the bridge
    //static int LEFT_END[]={LEFT_START[0],LEFT_START[1]-80}; // ending position of a person starting from right side of the bride
    //START AND END POINTS OF THE BRIDGE ENDS HERE


    //parking spots

    //false means position is free
    //true means position is occupied
    static boolean LEFT_PARKING_SPOTS[]={false,false,false,false};// starts from bottom left then clockwise
    static boolean RIGHT_PARKING_SPOTS[]={false,false,false,false}; // starts from bottom right then anti-clockwise
    //parking spots ends here

    static final int BASE_VELOCITY =2;
    //BRIDGE MEASUREMENT CONSTANTS END HERE
    static boolean isConcurrent=false;


}
