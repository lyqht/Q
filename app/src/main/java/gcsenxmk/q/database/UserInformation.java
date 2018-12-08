package gcsenxmk.q.database;

public class UserInformation {

    public String name;
    public String imageURL;
    public boolean priority = false;


    public  UserInformation(){

    }

    public UserInformation(String name, String imageURL, Boolean priority) {
        this.name = name;
        this.imageURL = imageURL;
        this.priority=priority;
    }
}