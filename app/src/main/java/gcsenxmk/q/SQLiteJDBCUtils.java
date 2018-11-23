/*
package gcsenxmk.q;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import  java.sql.Statement;
import java.sql.Time;

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

    public String createUserTable() throws  SQLException{
        String sql = "CREATE TABLE QSystem.users "
                + "(userId INT PRIMARY KEY NOT NULL,"
                + " userName TEXT NOT NULL,"
                + " userEmail CHAR(8) NOT NULL,"
                + " userPassword CHAR(50) NOT NULL,"
                + " maxWaitingTime INT NOT NULL,"
                + " creditCard BOOLEAN NOT NULL,"
                + " cash BOOLEAN NOT NULL,"
                + " userDisable BOOLEAN NOT NULL,"
                + " userPregnant BOOLEAN NOT NULL,"
                + " userIfSubscribe BOOLEAN NOT NULL, "
                + " creditRecord INT NOT NULL) ; ";
        return sql;
    }

    public String createShopTable() throws  SQLException{
        String sql = "CREATE TABLE QSystem.shops "
                + "(shopId INT PRIMARY KEY NOT NULL,"
                + " shopName TEXT NOT NULL,"
                + " shopPhone CHAR(8),"
                + " shopHalal BOOLEAN NOT NULL,"
                + " shopCategory TEXT NOT NULL,"
                + " shopAvePrice INT NOT NULL,"
                + " creditCard BOOLEAN NOT NULL,"
                + " cash BOOLEAN NOT NULL,"
                + " shopAveWaitingTime INT NOT NULL) ;" ;
        return sql;
    }

    public String createUserInstance(int userId, String userName,String userEmail,String userPassword,
                                     int maxWaitingTime,boolean creditCard, boolean cash,
                                     boolean userDisable, boolean userPregnant, boolean userSubscribe,
                                     String userSubscribeShop)
            throws  SQLException{
        String sql = "INSERT INTO QSystem.users VALUES ("
                + Integer.toString(userId) + ","
                + userName + ","
                + userEmail + ","
                + userPassword +  ","
                + Integer.toString(maxWaitingTime) + ","
                + Boolean.toString(creditCard) + ","
                + Boolean.toString(cash) + ","
                + Boolean.toString(userDisable) + ","
                + Boolean.toString(userPregnant) + ","
                + Boolean.toString(userSubscribe) + ","
                + userSubscribeShop + ");" ;
        return sql;
    }

    public String createShopInstance(int shopId, String shopName, String shopPhone,
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
        return sql;
    }




    //TODO: add two more tables here
    // TODO: AUTO- INCRIMENTAL

    public String createPeakTimeRecordTable() throws SQLException{
        String sql = "";
        return sql;
    }

    public String createBulkyOrderTable() throws SQLException{
        String sql = "";
        return sql;
    }

    public String createQueue() throws SQLException{
        String sql = "CREATE TABLE QSystem.queue "
                + "(queueId INT PRIMARY KEY NOT NULL,"
                + " userId INT NOT NULL,"
                + " shopId INT NOT NULL, "
                + " time DATE NOT NULL) ; ";
        return sql;
    }


    public String joinQ(String table_name, int queueId, int userId,int shopId){
        java.util.Date javaDate = new java.util.Date();
        long javaTime = javaDate.getTime();
        Time sqlTime = new java.sql.Time(javaTime);

        System.out.println("The SQL TIME is: " + sqlTime.toString());

        String sql = "INSERT INTO "
                + table_name + " VALUES ("
                + queueId + " , "
                + userId + ","
                + shopId + ","
                + sqlTime + " );";
        return sql;
    }
    public String leaveQ(String table_name, int queueId){
        String sql = "DELETE FROM "
                +table_name
                + " WHERE queueId "
                + "= " + queueId + ";";
        return sql;
    }
    public String finishQ(String table_name, int queueId){
        String sql = "DELETE FROM "
                + table_name
                + " WHERE queueId "
                + "= " + queueId + ";";
        return sql;
    }


}*/
