package gcsenxmk.q.database;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import gcsenxmk.q.R;

public class FirebaseTestActivity extends AppCompatActivity {

    private Button btnSignUp;
    private  Button btnSignIn;
    private EditText username;
    private EditText password;
    private EditText emailID;
    private Firebase mRef;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        firebaseAuth=FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser()!= null){
//            finish();
//            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
//        }



        emailID= (EditText) findViewById(R.id.uniquemail);
        //username= (EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        btnSignUp= (Button) findViewById(R.id.btnSignup);
        btnSignIn= (Button) findViewById(R.id.btnLogin);

        progressDialog= new ProgressDialog(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent s= new Intent(FirebaseTestActivity.this, FirebaseLoginActivity.class);
                startActivity(s);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUsers();



            }
        });

        }

    private void addUsers(){
        String email=emailID.getText().toString().trim();
        String pass=password.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email ID", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {


                    progressDialog.hide();

                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();

                    Intent segregate= new Intent(FirebaseTestActivity.this, SegregationActivity.class);
                    startActivity(segregate);

////                    if(firebaseAuth.getCurrentUser()!= null){
//                        finish();
//                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
////                    }

                }
                else{
                    progressDialog.hide();

                    if(pass.length()<6){
                        Toast.makeText(getApplicationContext(),"Make sure password is at least 6 characters long", Toast.LENGTH_LONG).show();


                    }else
                    Toast.makeText(getApplicationContext(),"Account already exists", Toast.LENGTH_LONG).show();
                }
            }
        });

        }

}

//        if(!TextUtils.isEmpty(name)){
//
//            String id= mRef.push().getKey();
//            UserList users= new UserList(name,pass);
//
//            mRef.child((id)).setValue(users);
//            Toast.makeText(this,"User entered the database", Toast.LENGTH_LONG).show();
//
//
//        }else{
//            Toast.makeText(this,"Enter a name", Toast.LENGTH_LONG).show();
//
//        }


        //                String value= username.getText().toString();
//                String valuepass= mockpass.getText().toString();
//                Firebase childRef=mRef.child("Users");
//                //Firebase mRefChild=mRef.child("Name");
//
//                //mRefChild.setValue("Harry");
//                childRef.push().setValue(value);
//                childRef.push().setValue(valuepass);

//Firebase.setAndroidContext(this);
// mRef=new Firebase("https://qsystem-5001.firebaseio.com/Users");

