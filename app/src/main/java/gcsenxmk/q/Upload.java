package gcsenxmk.q;


public class Upload {
    private String Name;
    private String ImageUrl;
    private String Desc;
    private String Location;
    private int Avewaiting;
    private int NumPeople;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String imageUrl, String location,
                  String desc, int wait, int numPeople ) {
        if (name.trim().equals("")) {
            name = "Zombie";
        }

        Name = name;
        ImageUrl = imageUrl;
        Desc = desc;
        Avewaiting = wait;
        Location = location;
        NumPeople = numPeople;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public int getAvewaiting() {
        return Avewaiting;
    }

    public void setAvewaiting(int avewaiting) {
        Avewaiting = avewaiting;
    }

    public String getLocation() {return Location;}

    public void setLocation(String location) {Location = location;}

    public int getNumPeople() {return NumPeople;}

    public void setNumPeople(int numpeople) {NumPeople = numpeople;}
}