package gcsenxmk.q;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="signup.db";
    private static final String TABLE_NAME= "signup_table";

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE Table "+ TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PRICE TEXT, ADDRESS TEXT)");

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
        contentValues.put("NAME",userName);
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


}

