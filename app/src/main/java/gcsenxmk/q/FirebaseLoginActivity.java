package gcsenxmk.q;

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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseLoginActivity extends AppCompatActivity {

    private Button btnSignUp;
    private  Button btnSignIn;
    private EditText useremail;
    private EditText pass;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;






    @Override
    protected void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);


        //database =new Firebase("https://qsystem-5001.firebaseio.com/Users");
        firebaseAuth= FirebaseAuth.getInstance();
//        if(firebaseAuth.getCurrentUser()!= null){
//            finish();
//            startActivity(new Intent(FirebaseLoginActivity.this, ProfileActivity.class));
//        }



        //users=database.getReference("Users");
        useremail= (EditText) findViewById(R.id.emaillogin);
        pass=(EditText) findViewById(R.id.passwordlogin);
        btnSignUp= (Button) findViewById(R.id.btntoSignup);
        btnSignIn= (Button) findViewById(R.id.btntoSignin);
        progressDialog=new ProgressDialog(this);



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

               // signIn(useremail.getText().toString(), pass.getText().toString());

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), FirebaseTestActivity.class));
            }
        });




}

    private  void userLogin(){
        String email =useremail.getText().toString().trim();
        String password=pass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email ID", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(FirebaseLoginActivity.this, SegregationActivityAfterLogin.class));
                }

                else{
                    Toast.makeText(FirebaseLoginActivity.this,"You entered wrong email/password. Please try again!", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }}








//    private void signIn(final String username, final String password) {
////
////        users.addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
////                if(dataSnapshot.child(username).exists())
////                    if(!username.isEmpty()){
////
////                    }
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError databaseError) {
////
////            }
////        });
////
////
////
////    }
//
