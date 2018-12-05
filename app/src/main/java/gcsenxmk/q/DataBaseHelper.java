package gcsenxmk.q;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{

    public static DataBaseHelper myDB;

    private static final String DATABASE_NAME="signup1.db";
    private static final String TABLE_NAME= "signup_table";


//    public DataBaseHelper(Context context){
//        super(context,DATABASE_NAME,null,1);
//    }


    public static DataBaseHelper getDataHelper(Context context) {
        if (myDB == null){
            myDB = new DataBaseHelper(context);
        }
        return myDB;
    }

    public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE Table "+ TABLE_NAME+"(Name TEXT, Email TEXT, Password TEXT,maxWaitingtime TEXT, CreditCard TEXT,Cash TEXT," +
                "Disable TEXT, Pregnant TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertHotel(String name, String price, String address){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("PRICE",price);
        contentValues.put("ADDRESS",address);

        long result= db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public boolean signupInsert(String userName,String userEmail,String userPassword,
                                String maxWaitingTime,String creditCard, String cash,
                                String userDisable, String userPregnant){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();
        contentValues.put("Name",userName);
        contentValues.put("Email",userEmail);
        contentValues.put("Password",userPassword);
        contentValues.put("maxWaitingtime",maxWaitingTime);
        contentValues.put("CreditCard",creditCard);
        contentValues.put("Cash",cash);
        contentValues.put("Disable",userDisable);
        contentValues.put("Pregnant",userPregnant);

        long result= db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public boolean login(String userEmail, String password){
        if(accountExist(userEmail)){
            if(checkPassword(userEmail, password)){
                System.out.println("successful login!!!!!");
            }else {
                System.out.println("wrong password!!!!!");

            }
        }
        return false;
    }



    public Cursor getAllHotel(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cu= db.rawQuery("Select * from "+ TABLE_NAME, null);

        return cu;
    }

    public Cursor getLimitedHotels(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cu=db.rawQuery("Select * from hotel_table where name="+ "hello",null);
        return cu;
    }



    public boolean accountExist(String userEmail) {
        SQLiteDatabase db= this.getWritableDatabase();



        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME+ " where email= " + "'" + userEmail + "'" , null);

        if(cursor.getCount() != 0){
            Log.i("account search", userEmail + " account exists，return true");
            return true;
        }

        cursor.close();
        db.close();
        Log.i("account search", "attend:    " + userEmail + " account not exist，return false");
        return false;
    }

    public String getPassword(String userEmail){
        if(!accountExist(userEmail)){
            return null;
        }
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select Password from " + TABLE_NAME+ " where email= " + "'" + userEmail + "'" , null);
        cursor.moveToPosition(0);
        String password = cursor.getString(0);

        return password;
    }

    public boolean checkPassword(String userEmail, String passwordInput){
        String correctPassword = getPassword(userEmail);
        return (correctPassword.equals(passwordInput));
    }




}