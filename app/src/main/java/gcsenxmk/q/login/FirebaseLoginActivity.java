package gcsenxmk.q.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import gcsenxmk.q.R;
import gcsenxmk.q.login.SegregationActivityAfterLogin;

public class FirebaseLoginActivity extends AppCompatActivity {

    private  Button btnSignIn;
    private EditText useremail;
    private EditText pass;
    private TextView signUp;

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
//            startActivity(new Intent(FirebaseLoginActivity.this, Init_Cust_Profile.class));
//        }

/*        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        while (page != null) {
            for (ExportedUserRecord user : page.getValues()) {
                String uid =  user.getUid();
                FirebaseAuth.getInstance().deleteUser(uid);
            }
            page = page.getNextPage();
        }*/



        //users=database.getReference("Users");
        useremail= (EditText) findViewById(R.id.emaillogin);
        pass=(EditText) findViewById(R.id.passwordlogin);
        btnSignIn= (Button) findViewById(R.id.btntoSignin);
        progressDialog=new ProgressDialog(this);
        signUp = (TextView) findViewById(R.id.linktoSignup);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

                // signIn(useremail.getText().toString(), pass.getText().toString());

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
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
