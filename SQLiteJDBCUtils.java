import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import  java.sql.Statement;

public class SQLiteJDBCUtils {
    Connection c;
    Statement stmt;

    //Before
    public void before(){
        try{
            Class.forName("org.sqlite.JDBA");
            c = DriverManager.getConnection("jdbc:sqlite:D:\\SQLite\\db\\test.db");
            System.out.println("OPened database successfully");
            stmt = c.createStatement();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //after
    public void after(){
        try{
            stmt.close();
            c.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void createUserTable() throws  SQLException{
        String sql = "CREATE TABLE QSystem.users "
                + "(userId INT PRIMARY KEY NOT NULL,"
                + " userName TEXT NOT NULL,"
                + " userEmail CHAR(50),"
                + " maxWaitingTime INT NOT NULL,"
                + " creditCard BOOLEAN NOT NULL,"
                + " cash BOOLEAN NOT NULL,"
                + " userDisable BOOLEAN NOT NULL,"
                + " userPregnant BOOLEAN NOT NULL,"
                + " userIfSubscribe BOOLEAN NOT NULL,"
                + " userSubscribeShop TEXT);" ;
        stmt.executeUpdate(sql);
    }

    public void createShopTable() throws  SQLException{
        String sql = "CREATE TABLE QSystem.shops "
                + "(shopId INT PRIMARY KEY NOT NULL,"
                + " shopName TEXT NOT NULL,"
                + " shopPhone CHAR(8),"
                + " shopHalal BOOLEAN NOT NULL,"
                + " shopCategory TEXT NOT NULL,"
                + " shopAvePrice INT NOT NULL,"
                + " creditCard BOOLEAN NOT NULL,"
                + " cash BOOLEAN NOT NULL,"
                + " shopAveWaitingTime INT NOT NULL,"
                + " shopSubscribers TEXT);" ;
        stmt.executeUpdate(sql);
    }

    public void createUserInstance(int userId, String userName,String userEmail,
                                   int maxWaitingTime,boolean creditCard, boolean cash,
                                   boolean userDisable, boolean userPregnant, boolean userSubscribe,
                                   String userSubscribeShop)
            throws  SQLException{
        String sql = "INSERT INTO QSystem.users VALUES ("
                + Integer.toString(userId) + ","
                + userName + ","
                + userEmail + ","
                + Integer.toString(maxWaitingTime) + ","
                + Boolean.toString(creditCard) + ","
                + Boolean.toString(cash) + ","
                + Boolean.toString(userDisable) + ","
                + Boolean.toString(userPregnant) + ","
                + Boolean.toString(userSubscribe) + ","
                + userSubscribeShop + ");" ;
        stmt.executeUpdate(sql);
    }

    public void createShopInstance(int shopId, String shopName, String shopPhone,
                                   boolean shopHalal,String shopCategory,double shopAvePrice, boolean creditCard, boolean cash,
                                   int shopAveWaitingTime,
                                   String userSubscribeShop)
            throws  SQLException{
        String sql = "INSERT INTO QSystem.shops VALUES ("
                + Integer.toString(shopId) + ","
                + shopName + ","
                + shopPhone + ","
                + Boolean.toString(shopHalal) + ","
                + shopCategory + ","
                + Double.toString(shopAvePrice) + ","
                + Boolean.toString(creditCard) + ","
                + Boolean.toString(cash) + ","
                + Integer.toString(shopAveWaitingTime) + ","
                + userSubscribeShop + " ;" ;
        stmt.executeUpdate(sql);
    }






    public void createPeakTimeRecordTable() throws SQLException{
        String sql = "CREATE TABLE COMPANY "
                + "(ID INT PRIMARY KEY NOT NULL,"
                + " NAME TEXT NOT NULL,"
                + " AGE INT NOT NULL,"
                + " ADDRESS CHAR(50),"
                + " SALARY REAL)";
        stmt.executeUpdate(sql);
    }
}
