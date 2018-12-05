package gcsenxmk.q.database;

public class UserList {

    String username;
    String password;

    public UserList(){

    }

    public UserList(String name, String password){
        this.username=name;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
