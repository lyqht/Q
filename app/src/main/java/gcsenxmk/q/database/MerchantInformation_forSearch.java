package gcsenxmk.q.database;

public class MerchantInformation_forSearch {

    public String name, imageUrl,desc;
    public int avewaiting,numPeople;

    public MerchantInformation_forSearch(){

    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
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
        return numPeople;
    }

    public String getDesc() {
        return desc;
    }

    public MerchantInformation_forSearch(String name, String imageUrl, int avewaiting, int numPeople, String desc) {
        this.name = name;
        this.avewaiting=avewaiting;
        this.imageUrl=imageUrl;
        this.desc=desc;
        this.numPeople=numPeople;
    }
}
