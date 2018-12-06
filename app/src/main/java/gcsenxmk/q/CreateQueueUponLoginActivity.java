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

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CreateQueueUponLoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button btnlogout2;
    //private Button imageupload;
    private DatabaseReference databaseReference;
    private DatabaseReference queue_databaseReference;

    private EditText queuename,queuedesc, wait_time;
    private Button createqueue;

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private TextView mTextViewShowUploads;
    private EditText mEditTextFileName,mDesc, mWaitingtime;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private Uri mImageUri;

    private FirebaseUser user;
    private StorageReference mStorageRef;


    private StorageTask mUploadTask;






    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_create_queue_after_login);

        firebaseAuth=FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser()!= null){
//            finish();
//            startActivity(new Intent(getApplicationContext(), FirebaseLoginActivity.class));
//        }

        databaseReference= FirebaseDatabase.getInstance().getReference("Merchants");

        queue_databaseReference= FirebaseDatabase.getInstance().getReference("Queue");

        queuename=(EditText) findViewById(R.id.queuenamemerchant);
        queuedesc= (EditText) findViewById(R.id.queuedescription);
        createqueue=(Button) findViewById(R.id.btnaddqueue);
        wait_time=(EditText) findViewById(R.id.average_time);

        user = firebaseAuth.getCurrentUser();
        textViewUserEmail=(TextView) findViewById(R.id.textviewemailmerchant);
        textViewUserEmail.setText("Welcome "+user.getEmail());
        btnlogout2=(Button) findViewById(R.id.btnLogoutMerchant);
        //imageupload=(Button) findViewById(R.id.btnuploadimage);

        createqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadFile();

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

//        imageupload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(QueueActivity.this, FirebaseUploadActivity.class);
//                startActivity(intent);
//            }
//        });

        mButtonChooseImage = findViewById(R.id.button_choose_image);
        mButtonUpload = findViewById(R.id.button_upload);
        mTextViewShowUploads = findViewById(R.id.text_view_show_uploads);
        mEditTextFileName = findViewById(R.id.edit_text_file_name);
        mDesc= findViewById(R.id.queuedescription);
        mWaitingtime=findViewById(R.id.average_time);

        mImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);

        mStorageRef = FirebaseStorage.getInstance().getReference("Merchants");


        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(CreateQueueUponLoginActivity.this, "Upload in progress", Toast.LENGTH_LONG).show();
                } else {
                    uploadFile();
                }
            }
        });

        mTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagesActivity();
            }
        });

    }

    private void saveMerchantInfo(){

        String getname=queuename.getText().toString().trim();
        String getdesc=queuedesc.getText().toString().trim();
        int wait=Integer.parseInt(wait_time.getText().toString().trim());

        // MerchantInformation merchantInformation= new MerchantInformation(getname,getdesc);

        //  FirebaseUser user=firebaseAuth.getCurrentUser();

        //   databaseReference.child(user.getUid()).setValue(merchantInformation);
        Toast.makeText(this, "Merchant info saved in the database", Toast.LENGTH_LONG).show();


    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {

        String getname=queuename.getText().toString().trim();
        String getdesc=queuedesc.getText().toString().trim();
        int wait=Integer.parseInt(wait_time.getText().toString().trim());


        //  FirebaseUser user=firebaseAuth.getCurrentUser();

        //databaseReference.child(user.getUid()).setValue(merchantInformation);
        Toast.makeText(this, "Merchant info saved in the database", Toast.LENGTH_SHORT).show();

        //saveMerchantInfo();
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(CreateQueueUponLoginActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();
                            final String sdownload_url = String.valueOf(downloadUrl);

                            Upload upload = new Upload(mEditTextFileName.getText().toString().trim(), sdownload_url ,Integer.parseInt(mWaitingtime.getText().toString()) ,mDesc.getText().toString());
                            String uploadId = databaseReference.push().getKey();
                            //MerchantInformation merchantInformation= new MerchantInformation(getname, getdesc, wait, upload);
                            QueueInformation queueInformation = new QueueInformation(getname, getdesc, wait);

                            // merchantInformation.queueimage = upload;
                            databaseReference.child(user.getUid()).setValue(upload);
                            queue_databaseReference.child(user.getUid()).setValue(queueInformation);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CreateQueueUponLoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void openImagesActivity() {
        Intent intent = new Intent(this, RecyclerActivity.class);
        startActivity(intent);
    }





}


