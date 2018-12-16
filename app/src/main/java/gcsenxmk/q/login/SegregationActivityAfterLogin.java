package gcsenxmk.q.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import gcsenxmk.q.R;
import gcsenxmk.q.customer.Cust_MainActivity;
import gcsenxmk.q.merchant.Merc_MainActivity;

public class SegregationActivityAfterLogin extends AppCompatActivity {

    private Button btncust, btnmerc, signOutButton;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segregate);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        btncust= findViewById(R.id.btnCustomer);
        btnmerc= findViewById(R.id.btnMerchant);
        signOutButton = findViewById(R.id.segregation_signOutButton);

        btncust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile= new Intent(SegregationActivityAfterLogin.this, Cust_MainActivity.class);
                startActivity(profile);

            }
        });

        btnmerc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent queueactivity= new Intent(SegregationActivityAfterLogin.this, Merc_MainActivity.class);
                startActivity(queueactivity);

            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(SegregationActivityAfterLogin.this, FirebaseLoginActivity.class));
            }
        });

        }
}
