package gcsenxmk.q;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {


    public DataBaseHelper myDB;
    EditText email, pass;
    Button btnlogin, btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        btnsignup = (Button) findViewById(R.id.btnSignup);
        btnlogin = (Button) findViewById(R.id.btnLogin);
        myDB = new DataBaseHelper(this);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });



        //addHotel();
        //viewAllHotel();


    }
}
    //Create a method for adding data to hotel table


//    public void addHotel(){
//        btnSave= (Button) findViewById(R.id.btnSave);
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                boolean isInserted= myDB.insertHotel(txtName.getText().toString(), txtPrice.getText().toString(), txtAddress.getText().toString());
//
//                if(isInserted){
//                    Toast.makeText(LoginActivity.this, "Data is inserted", Toast.LENGTH_LONG).show();
//                }else{
//
//                    Toast.makeText(LoginActivity.this, "Data is not inserted", Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });
//
//    }



//
//    public String createUserTable() throws SQLException {
//        btnsignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });
//
//
//
//        String sql = "CREATE TABLE QSystem.users "
//                + "(userId INT PRIMARY KEY NOT NULL,"
//                + " userName TEXT NOT NULL,"
//                + " userPhone CHAR(8),"
//                + " maxWaitingTime INT NOT NULL,"
//                + " creditCard BOOLEAN NOT NULL,"
//                + " cash BOOLEAN NOT NULL,"
//                + " userDisable BOOLEAN NOT NULL,"
//                + " userPregnant BOOLEAN NOT NULL,"
//                + " userIfSubscribe BOOLEAN NOT NULL, "
//                + " creditRecord INT NOT NULL) ; ";
//        return sql;
//    }

































//    public void displayHotel(String title, String content){
//        AlertDialog.Builder builder= new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(content);
//        builder.show();
//    }

//    public void viewAllHotel(){
//
//        btnView= (Button) findViewById(R.id.btnView);
//        btnView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Cursor result= myDB.getAllHotel();
//                if(result.getCount()==0){
//                    displayHotel("Error","No Hotel to display");
//                }else{
//                    StringBuffer buffer=new StringBuffer();
//                    while ((result.moveToNext())){
//
//                        buffer.append("Hotel ID : "+ result.getString(0)+"\n");//index 0 refers to the first column of hotel table
//                        buffer.append("Hotel Name : "+ result.getString(1)+"\n");
//                        buffer.append("Hotel Price: "+ result.getString(2)+"\n");
//                        buffer.append("Hotel Address : "+ result.getString(3)+"\n");
//                        displayHotel("Hotel List", buffer.toString());
//
//                    }
//                }
//            }
//        });
//
//    }
//}
//
