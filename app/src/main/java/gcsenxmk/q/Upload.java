package gcsenxmk.q;


public class Upload {
    private String Name;
    private String ImageUrl;
    private String Desc;
    private int Avewaiting;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String imageUrl,int wait, String desc) {
        if (name.trim().equals("")) {
            name = "Zombie";
        }

        Name = name;
        ImageUrl = imageUrl;
        Desc = desc;
        Avewaiting = wait;
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
}