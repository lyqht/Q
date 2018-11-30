package gcsenxmk.q;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button btnlogout1;



    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth=FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser()!= null){
//            finish();
//            startActivity(new Intent(getApplicationContext(), FirebaseLoginActivity.class));
//        }

        FirebaseUser user= firebaseAuth.getCurrentUser();
        textViewUserEmail=(TextView) findViewById(R.id.textviewemail);
        textViewUserEmail.setText("Welcome"+user.getEmail());
        btnlogout1=(Button) findViewById(R.id.btnLogout);

        btnlogout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),FirebaseLoginActivity.class));

            }
        });

    }
}
