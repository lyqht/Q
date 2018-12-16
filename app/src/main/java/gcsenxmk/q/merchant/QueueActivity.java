package gcsenxmk.q.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gcsenxmk.q.R;
import gcsenxmk.q.database.QueueInformation;

public class QueueActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "CustQueueActivity";

    private FirebaseAuth firebaseAuth;
    private DatabaseReference queue_databaseReference;
    private Uri mImageUri;
    private FirebaseUser user;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;

    private EditText queuename,queuedesc, queuelocation, queuetime;
    private Button createqueue;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    String getname;
    String getdesc;
    String getlocation;
    int gettime;
    String getphotoURL;

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merc_configure_queue);
        Log.d(TAG, "onCreate");
        
        // Firebase Instances
        firebaseAuth=FirebaseAuth.getInstance();
        queue_databaseReference= FirebaseDatabase.getInstance().getReference("Queue");
        mStorageRef = FirebaseStorage.getInstance().getReference("Merchants");
        user = firebaseAuth.getCurrentUser();

        // UI Variables
        createqueue = findViewById(R.id.btn_createQ);

        queuename= findViewById(R.id.enterQueueName);
        queuelocation = findViewById(R.id.enterLocation);
        queuetime = findViewById(R.id.editEstTimeText);
        queuedesc = findViewById(R.id.enterDesc);
        mButtonChooseImage = findViewById(R.id.button_choose_image);
        mButtonUpload = findViewById(R.id.button_upload);
        mImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);

        createqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameInput = queuename.getText().toString();
                String timeInput = queuetime.getText().toString();
                String locateInput = queuelocation.getText().toString();
                String sd = queuedesc.getText().toString();
                if(nameInput.equals("")||timeInput.equals("")||locateInput.equals("")||sd.equals("")){
                    Log.i(TAG, "Please fill up all information");
                    Toast.makeText(QueueActivity.this, "Please fill up all information", Toast.LENGTH_LONG).show();
                }
                else{
                    publishToDatabase();
                    Intent intent = new Intent(QueueActivity.this, MercQueueConfigured.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        
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
                    Toast.makeText(QueueActivity.this, "Upload in progress", Toast.LENGTH_LONG).show();
                } else {
                    uploadFile();
                }
            }
        });

    }

    // <========== UI FUNCTIONS ====================> //

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    // <========== DATABASE FUNCTIONS ====================> //

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

    private void publishToDatabase() {
        getname = queuename.getText().toString().trim();
        getdesc = queuedesc.getText().toString().trim();
        getlocation = queuelocation.getText().toString().trim();
        gettime = Integer.parseInt(queuetime.getText().toString().trim());

        QueueInformation upload = new QueueInformation(getname, getphotoURL, getlocation, getdesc, gettime);
        //String uploadId = databaseReference.push().getKey();
        ArrayList<String> testqueue = upload.getQueue();
        if(testqueue != null){
            for (String s : testqueue){
                System.out.println("extra consumers : "+ s);
            }
        }
        Log.i("testqueue", getname);

        queue_databaseReference.child(user.getUid()).setValue(upload);

        Log.d(TAG, "Merchant info saved");
        Toast.makeText(QueueActivity.this, "Merchant info saved in the database", Toast.LENGTH_SHORT).show();
    }

    private void uploadFile() {
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

                            Log.d(TAG, "Upload successful");
                            Toast.makeText(QueueActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();
                            getphotoURL = String.valueOf(downloadUrl);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(QueueActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

}


