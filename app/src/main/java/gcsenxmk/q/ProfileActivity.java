package gcsenxmk.q;

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
import com.google.firebase.iid.FirebaseInstanceIdService;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button btnlogout1;
    private DatabaseReference databaseReference;

    private EditText name,area;
    private Button save;






    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth=FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser()!= null){
//            finish();
//            startActivity(new Intent(getApplicationContext(), FirebaseLoginActivity.class));
//        }

        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

        name=(EditText) findViewById(R.id.nickname);
        area= (EditText) findViewById(R.id.areastaying);
        save=(Button) findViewById(R.id.btnsaveinfo);

        FirebaseUser user= firebaseAuth.getCurrentUser();
        textViewUserEmail=(TextView) findViewById(R.id.textviewemail);
        textViewUserEmail.setText("Welcome "+user.getEmail());
        btnlogout1=(Button) findViewById(R.id.btnLogout);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveUserInfo();

            }
        });



        btnlogout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),FirebaseLoginActivity.class));

            }
        });

    }

    private void saveUserInfo(){

        String getname=name.getText().toString().trim();
        String getarea=area.getText().toString().trim();

        UserInformation userInformation= new UserInformation(getname,getarea);

            FirebaseUser user=firebaseAuth.getCurrentUser();

            databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this, "Information saved in the database", Toast.LENGTH_LONG).show();


    }
}
