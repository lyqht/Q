package gcsenxmk.q;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.sql.SQLException;

public class SignupActivity  extends AppCompatActivity {

    DataBaseHelper myDBsign;
    EditText username, email, password, waitingtime, creditcard, cash, disable, pregnant;
    Button btnenter;

//    DataBaseHelper myDB = myDB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        myDBsign = DataBaseHelper.getDataHelper(this);

        username = findViewById(R.id.usernamesignup);
        email = findViewById(R.id.emailsignup);
        password = findViewById(R.id.passwordsignup);
        waitingtime = findViewById(R.id.max_wait);
        creditcard = findViewById(R.id.creditcard);
        cash = findViewById(R.id.cash);
        disable = findViewById(R.id.disable);
        pregnant = findViewById(R.id.pregnant);


        btnenter= findViewById(R.id.btnenterdb);



        btnenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInsertedData = myDBsign.signupInsert(username.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        waitingtime.getText().toString(),
                        creditcard.getText().toString(),
                        cash.getText().toString(),
                        disable.getText().toString(),
                        pregnant.getText().toString());

                if (isInsertedData) {
                    Toast.makeText(SignupActivity.this, "Successfull", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(SignupActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();

                }

            }
        });
    }}
