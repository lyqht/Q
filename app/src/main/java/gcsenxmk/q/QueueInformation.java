package gcsenxmk.q;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class QueueInformation {

    //public static int creation = 0;
    public String queuename;
    public String desc;
    public int average_waiting_time;

    public static ArrayList<String> queue;

    public QueueInformation(){
        this.queuename = "";
        this.desc="";
        this.average_waiting_time=1;
        queue = new ArrayList<String>();


    }

    public QueueInformation(String name, String desc, int average_waiting_time) {
        this.queuename = name;
        this.desc=desc;
        this.average_waiting_time=average_waiting_time;
        queue = new ArrayList<String>();


    }



    public String getQueuename() {
        return queuename;
    }

    public void setQueuename(String queuename) {
        this.queuename = queuename;
    }

    public ArrayList<String> getQueue() {
        return queue;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getWaiting_time_customer() {
        return average_waiting_time;
    }

    public void setWaiting_time_customer(int waiting_time_customer) {
        this.average_waiting_time = waiting_time_customer;
    }

    public void setQueue(ArrayList<String> queue) {
        this.queue = queue;
    }
}