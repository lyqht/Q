package gcsenxmk.q;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class QueueInformation {

    public static int creation = 0;
    public String queuename;

    public ArrayList<String> queue;

    public QueueInformation(){
        this.queuename = "";
        queue = new ArrayList<String>();
       // this.queue.add("FirstUser");

    }

    public QueueInformation(String name) {
        this.queuename = name;
        queue = new ArrayList<String>();
        //this.queue.add("initial");

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

    public void setQueue(ArrayList<String> queue) {
        this.queue = queue;
    }
}