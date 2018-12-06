package gcsenxmk.q;
import java.util.ArrayList;

public class QueueInformation {
    public String queuename;
    public String desc;
    public String location;
    public int wait_time;
    public int size;

    public static ArrayList<String> queue;

    public QueueInformation(){
        this.queuename = "No Queue Name";
        this.desc="No Desc";
        this.location = "No location";
        this.wait_time=1;
        this.size = 0;
        queue = new ArrayList<String>();

    }

    public QueueInformation(String name, String location, String desc, int wait_time, int size) {
        this.queuename = name;
        this.desc=desc;
        this.location=location;
        this.wait_time=wait_time;
        this.size = size;
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

    public int getWaitingtime() {
        return wait_time;
    }

    public void setWaitingtime(int w) {
        this.wait_time = w;
    }

    public String getLocation() {return location;}

    public void setLocation() {this.location = location;}

    public int getSize() {return size;}

    public void setSize(int numpeople) {this.size = numpeople;}

    public void setQueue(ArrayList<String> queue) {
        this.queue = queue;
    }


}