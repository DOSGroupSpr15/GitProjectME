package me;

import java.io.Serializable;

/**
 * Created by azhar on 4/14/15.
 */
public class Message implements Serializable{


    public int sender;
    public long time_stamp;
    public int type;

    public Message(int sender, int type, long time_stamp){
        this.sender=sender;
        this.type=type;
        this.time_stamp=time_stamp;

    }

}
