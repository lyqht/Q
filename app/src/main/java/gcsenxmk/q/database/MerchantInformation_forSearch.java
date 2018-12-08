package gcsenxmk.q.database;

public class MerchantInformation_forSearch {
    public String queuename;
    public String queuedescripton;
    public String queueimage;
    public int waiting_time;
    int NumberofCust;
    public  MerchantInformation_forSearch(){

    }

    public MerchantInformation_forSearch(String name, String desc, int waiting_time, String queueimage,int NumberofCust) {
        this.queuename = name;
        this.queuedescripton = desc;
        this.waiting_time=waiting_time;
        this.queueimage=queueimage;
        this.NumberofCust=NumberofCust;
    }

    public void setNumberofCust(int numberofCust) {
        NumberofCust = numberofCust;
    }

    public void setWaiting_time(int waiting_time) {
        this.waiting_time = waiting_time;
    }

    public void setQueuename(String queuename) {
        this.queuename = queuename;
    }

    public void setQueuedescripton(String queuedescripton) {
        this.queuedescripton = queuedescripton;
    }

    public void setQueueimage(String queueimage) {
        this.queueimage = queueimage;
    }

    public int getNumberofCust() {
        return NumberofCust;
    }

    public String getQueuename() {
        return queuename;
    }

    public String getQueuedescripton() {
        return queuedescripton;
    }

    public int getWaiting_time() {
        return waiting_time;
    }

    public String getQueueimage() {
        return queueimage;
    }
}
