package com.example.kundandalmia.androidsql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kundandalmia.androidsql.R;

public class MainActivity extends AppCompatActivity {
    //TODO: clickbox and all the parameters
    DataBaseHelper myDB;
    EditText txtName, txtPrice, txtAddress;
    Button btnSave,btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName=(EditText) findViewById(R.id.name);
        txtPrice=(EditText) findViewById(R.id.price);
        txtAddress=(EditText) findViewById(R.id.address);
        myDB= new DataBaseHelper(this);
        addCustomer();
        viewAllHotel();

    }

    //Create a method for adding data to hotel table
    // TODO: the login and sign up part
    public void addCustomer(){
        return;
    }
//    public void addHotel(){
//        btnSave= (Button) findViewById(R.id.btnSave);
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////
////                boolean isInserted= myDB.insertUser(SQLiteDatabase db, int userId, String userName,String userPhone,
////                int maxWaitingTime,boolean creditCard, boolean cash,
////                boolean userDisable, boolean userPregnant, boolean userSubscribe,
////                String userSubscribeShop);
//
//                if(isInserted){
//                    Toast.makeText(MainActivity.this, "Data is inserted", Toast.LENGTH_LONG).show();
//                }else{
//
//                    Toast.makeText(MainActivity.this, "Data is not inserted", Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });
//    }

    public void displayHotel(String title, String content){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(content);
        builder.show();
    }

    // TODO: what is needed here to be seen on the app??
    public void viewAllHotel(){

        btnView= (Button) findViewById(R.id.btnView);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result= myDB.getAllMerchant();
                if(result.getCount()==0){
                    displayHotel("Error","No Hotel to display");
                }else{
                    StringBuffer buffer=new StringBuffer();
                    while ((result.moveToNext())){

                        // TODO: change the buffer parameters
                        buffer.append("Hotel ID : "+ result.getString(0)+"\n");//index 0 refers to the first column of hotel table
                        buffer.append("Hotel Name : "+ result.getString(1)+"\n");
                        buffer.append("Hotel Price: "+ result.getString(2)+"\n");
                        buffer.append("Hotel Address : "+ result.getString(3)+"\n");
                        displayHotel("Hotel List", buffer.toString());

                    }
                }
            }
        });

    }
}
