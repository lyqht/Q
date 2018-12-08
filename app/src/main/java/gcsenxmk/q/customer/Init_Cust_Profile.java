package gcsenxmk.q.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import gcsenxmk.q.R;
import gcsenxmk.q.database.UserInformation;
import gcsenxmk.q.login.CustomerHomePageActivity;

//TODO: ADD PRIORITY Tags into Database
// Removed Logout Button

public class Init_Cust_Profile extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private DatabaseReference databaseReference;

    private EditText name,area;
    private Button save;

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_customer_profile);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

        name= findViewById(R.id.nickname);
        area=  findViewById(R.id.areastaying);
        save=findViewById(R.id.btnsaveinfo);

        FirebaseUser user= firebaseAuth.getCurrentUser();
        textViewUserEmail= findViewById(R.id.textviewemail);
        textViewUserEmail.setText("Welcome "+user.getEmail());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();

            }
        });

    }

    private void saveUserInfo(){

        String getname=name.getText().toString().trim();
        String getarea=area.getText().toString().trim();

        // Getting User inputs and sending to firebase
        UserInformation userInformation= new UserInformation(getname,getarea);
        FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this, "Information saved in the database", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, CustomerHomePageActivity.class);
        startActivity(i);
    }
}
