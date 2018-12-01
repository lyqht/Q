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

public class QueueActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button btnlogout2;
    private Button imageupload;
    private DatabaseReference databaseReference;

    private EditText queuename,queuedesc;
    private Button createqueue;






    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createqueue);

        firebaseAuth=FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser()!= null){
//            finish();
//            startActivity(new Intent(getApplicationContext(), FirebaseLoginActivity.class));
//        }

        databaseReference= FirebaseDatabase.getInstance().getReference("Merchants");

        queuename=(EditText) findViewById(R.id.queuenamemerchant);
        queuedesc= (EditText) findViewById(R.id.queuedescription);
        createqueue=(Button) findViewById(R.id.btnaddqueue);

        FirebaseUser user= firebaseAuth.getCurrentUser();
        textViewUserEmail=(TextView) findViewById(R.id.textviewemailmerchant);
        textViewUserEmail.setText("Welcome "+user.getEmail());
        btnlogout2=(Button) findViewById(R.id.btnLogoutMerchant);
        imageupload=(Button) findViewById(R.id.btnuploadimage);

        createqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveMerchantInfo();

            }
        });



        btnlogout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),FirebaseLoginActivity.class));

            }
        });

        imageupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(QueueActivity.this, Upload_Activity.class);
                startActivity(intent);
            }
        });

    }

    private void saveMerchantInfo(){

        String getname=queuename.getText().toString().trim();
        String getdesc=queuedesc.getText().toString().trim();

        MerchantInformation merchantInformation= new MerchantInformation(getname,getdesc);

        FirebaseUser user=firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(merchantInformation);
        Toast.makeText(this, "Merchant info saved in the database", Toast.LENGTH_LONG).show();


    }





}


