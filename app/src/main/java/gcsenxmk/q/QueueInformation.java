package gcsenxmk.q;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class QueueInformation {

    public String queuename;

    public ArrayList<String> queue;

    public QueueInformation(){
        this.queuename = "nothing";
        queue = new ArrayList<String>();
        this.queue.add("initial");

    }

    public QueueInformation(String name) {
        this.queuename = name;
        queue = new ArrayList<String>();
        this.queue.add("initial");

    }


}