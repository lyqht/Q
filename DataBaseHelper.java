package com.example.kundandalmia.androidsql;
import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.kundandalmia.androidsql.SQLiteJDBCUtils;

import java.sql.SQLException;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="QSystem.db";

    private static final String TABLE1_NAME= "customer_table";
    private static final String TABLE2_NAME= "merchant_table";
    private static final String TABLE3_NAME= "peakTime_table";
    private static final String TABLE4_NAME= "bulkyOrder_table";
    private static final String TABLE5_NAME= "queue_table";


    private static SQLiteJDBCUtils sqlMethods = new SQLiteJDBCUtils();

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(sqlMethods.createUserTable());
            db.execSQL(sqlMethods.createShopTable());
            db.execSQL(sqlMethods.createPeakTimeRecordTable());
            //TODO: add the Bulky order table and the queueSubscribe TABLE
        }catch(SQLException e){
            System.out.println("error on initialising the table");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: directly drop the table will not save the data inside
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE1_NAME);
        onCreate(db);
    }

    public boolean insertUser(SQLiteDatabase db, int userId, String userName,String userPhone,
                              int maxWaitingTime,boolean creditCard, boolean cash,
                              boolean userDisable, boolean userPregnant, boolean userSubscribe,
                              String userSubscribeShop) {
        try {
            db.execSQL(sqlMethods.createUserInstance(userId, userName, userPhone,
                    maxWaitingTime, creditCard, cash, userDisable, userPregnant, userSubscribe, userSubscribeShop));
        } catch (SQLException e) {
            System.out.println("error in inserting the user");
            return false;
        }
        return true;
    }
    //        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues contentValues= new ContentValues();
//        contentValues.put("NAME",name);
//        contentValues.put("PRICE",price);
//        contentValues.put("ADDRESS",address);
//
//        long result= db.insert(TABLE_NAME,null,contentValues);
//        if(result==-1){
//            return false;
//        }else{
//            return true;
//        }

    public Cursor getAllCostumer(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cu= db.rawQuery("Select * from "+ TABLE1_NAME, null);
        return cu;
    }
    public Cursor getAllMerchant(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cu= db.rawQuery("Select * from "+ TABLE2_NAME, null);
        return cu;
    }
    public Cursor getAllQueue(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cu= db.rawQuery("Select * from "+ TABLE5_NAME, null);
        return cu;
    }

    //TODO: get information form a specific shop
    public Cursor getLimitedHotels(String shopID){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cu=db.rawQuery("Select * from " +  TABLE5_NAME +" where shopId = "+ shopID, null);
        return cu;
    }



}
