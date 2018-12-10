package gcsenxmk.q.database;

import java.util.ArrayList;

public class MerchantInformation_forSearch {

    public String name, imageUrl,desc,location;
    public int avewaiting, numPeople;
    public static ArrayList<String> queue;

    public MerchantInformation_forSearch(){

    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setQueue(ArrayList<String> queue) {
        this.queue = queue;
    }

    public void setAvewaiting(int avewaiting) {
        this.avewaiting = avewaiting;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvewaiting() {
        return avewaiting;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }


    public int getNumPeople() {
        try {
            return queue.size();
        } catch (NullPointerException ex) {
            return numPeople;
        }
    }



    public String getLocation() {
        return location;
    }

    public ArrayList<String> getQueue() {
        return queue;
    }

    public String getDesc() {
        return desc;
    }

    public MerchantInformation_forSearch(String name, String imageUrl, int avewaiting, String desc) {
        this.name = name;
        this.avewaiting=avewaiting;
        this.imageUrl=imageUrl;
        this.desc=desc;
    }
}
