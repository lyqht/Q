package gcsenxmk.q.customer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import gcsenxmk.q.R;
import gcsenxmk.q.database.UserInformation;
import gcsenxmk.q.login.CustomerHomePageActivity;
import gcsenxmk.q.misc.CircleTransform;
import gcsenxmk.q.misc.Utils;

//TODO: ADD PRIORITY Tags into Database
// Removed Logout Button

public class Init_Cust_Profile extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private StorageReference mStorageRef;
    private DatabaseReference databaseReference;
    private StorageTask mUploadTask;

    private TextView textViewUserEmail;
    private Uri mImageUri;
    private String imageURL = "";
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "Cust_Profile_Init";

    private EditText name;
    private ImageButton profilePic;
    private Button save;

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_customer_profile);

        firebaseAuth=FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        mStorageRef = FirebaseStorage.getInstance().getReference("Customers");
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

        name= findViewById(R.id.nickname);
        profilePic = findViewById(R.id.init_profile_image);
        save=findViewById(R.id.btnsaveinfo);

        FirebaseUser user= firebaseAuth.getCurrentUser();
        textViewUserEmail= findViewById(R.id.textviewemail);
        textViewUserEmail.setText("Welcome "+user.getEmail());

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();

            }
        });

    }

    private void saveUserInfo(){

        String getname=name.getText().toString().trim();
        if (mImageUri != null && mImageUri!= user.getPhotoUrl()) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + Utils.getFileExtension(mImageUri, this));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.d(TAG, "Upload Customer Profile Image successful");

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful()) ;
                            Uri imageUri = urlTask.getResult();
                            imageURL = String.valueOf(imageUri);

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(getname)
                                    .setPhotoUri(Uri.parse(imageURL))
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User profile updated.");
                                            }
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, e.getMessage());
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.d(TAG, "Image Upload in progress");
                        }
                    });
        }


        UserInformation userInformation= new UserInformation(getname,imageURL);
        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this, "Information saved in the database", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, CustomerHomePageActivity.class);
        startActivity(i);
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).transform(new CircleTransform()).into(profilePic);
        }
    }
}
