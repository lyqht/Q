package gcsenxmk.q;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class QueueActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DatabaseReference queue_databaseReference;

    private EditText queuename,queuedesc;
    private Button createqueue;

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private TextView mTextViewShowUploads;
    private EditText mEditTextLocation;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private Uri mImageUri;
    private FirebaseUser user;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;

    // From Sean's MercCreateQueue
    EditText location;
    private Spinner queueTypeSpinner, prioritySpinner;
    private EditText editEstTimeText;
    private Button btn_createQueue;
    EditText Qname;

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merc_create_queue);
        
        // Firebase Functions
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("Merchants");
        queue_databaseReference= FirebaseDatabase.getInstance().getReference("Queue");
        mStorageRef = FirebaseStorage.getInstance().getReference("Merchants");
        user = firebaseAuth.getCurrentUser();

        // UI Variables
        queuename= findViewById(R.id.enterQueueName);
        createqueue=(Button) findViewById(R.id.btn_createQ);
        location = findViewById(R.id.enterLocation);
        //editEstTimeText = findViewById(R.id.editEstTimeText);
        queuedesc= (EditText) findViewById(R.id.enterDesc);
        
        queueTypeSpinner = findViewById(R.id.spinQueueSys);
        ArrayAdapter<CharSequence> adapterQueue = ArrayAdapter.createFromResource(this,
                R.array.queue_system, android.R.layout.simple_spinner_item);
        adapterQueue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        queueTypeSpinner.setAdapter(adapterQueue);
        queueTypeSpinner.setOnItemSelectedListener(this);

        prioritySpinner = findViewById(R.id.spinPriority);
        ArrayAdapter<CharSequence> adapterPriority = ArrayAdapter.createFromResource(this,
                R.array.priority_queue, android.R.layout.simple_spinner_item);

        prioritySpinner.setAdapter(adapterPriority);
        prioritySpinner.setOnItemSelectedListener(this);
        
        
        // Merchant Upload Image Area 
        mButtonChooseImage = findViewById(R.id.button_choose_image);
        mButtonUpload = findViewById(R.id.button_upload);
        mTextViewShowUploads = findViewById(R.id.text_view_show_uploads);
        mEditTextLocation = findViewById(R.id.enterLocation);
        mImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);

        //textViewUserEmail=(TextView) findViewById(R.id.textviewemailmerchant);
        //textViewUserEmail.setText("Welcome "+user.getEmail());

        createqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
                Intent intent = new Intent(QueueActivity.this, MercQueueCreated.class);
                startActivity(intent);
                finish();
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

        mTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagesActivity();
            }
        });

        //TODO: MOVE THIS LOGOUT BUTTON TO MERCHANT ACCOUNT SETTINGS
/*        btnlogout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),FirebaseLoginActivity.class));

            }
        });*/

    }

    // <========== UI FUNCTIONS ====================> //

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        //String text = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    // <========== DATABASE FUNCTIONS ====================> //

    private void saveMerchantInfo(){

        String getname=queuename.getText().toString().trim();
        String getdesc=queuedesc.getText().toString().trim();

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

                            Toast.makeText(QueueActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();
                            final String sdownload_url = String.valueOf(downloadUrl);

                            Upload upload = new Upload(mEditTextLocation.getText().toString().trim(),
                                    sdownload_url);
                            String uploadId = databaseReference.push().getKey();
                            MerchantInformation merchantInformation= new MerchantInformation(getname,getdesc,upload);
                            QueueInformation queueInformation = new QueueInformation(queuename.getText().toString());

                           // merchantInformation.queueimage = upload;
                            databaseReference.child(user.getUid()).setValue(upload);
                            queue_databaseReference.child(user.getUid()).setValue(queueInformation);
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

    private void openImagesActivity() {
        Intent intent = new Intent(this, RecyclerActivity.class);
        startActivity(intent);
    }





}


