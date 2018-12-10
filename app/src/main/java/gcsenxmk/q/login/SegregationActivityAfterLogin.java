package gcsenxmk.q.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import gcsenxmk.q.R;
import gcsenxmk.q.customer.Cust_MainActivity;
import gcsenxmk.q.merchant.Merc_MainActivity;

public class SegregationActivityAfterLogin extends AppCompatActivity {

    private ImageButton btncust, btnmerc;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segregate);

        btncust=(ImageButton) findViewById(R.id.btnCustomer);
        btnmerc=(ImageButton) findViewById(R.id.btnMerchant);

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





    }



}
