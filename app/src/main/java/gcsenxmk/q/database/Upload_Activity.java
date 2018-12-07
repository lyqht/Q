package gcsenxmk.q.database;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import gcsenxmk.q.R;

public class Upload_Activity extends AppCompatActivity {

    private static final String TAG= "UploadActivity";
    private ImageView image;
    private Button upload,next,previous;
    private ProgressDialog progressDialog;

    private final static int mWidth=512;
    private final static int mLength=512;

    private ArrayList<String> pathArray;
    private int array_position;

    private StorageReference mStorageRef;
    private FirebaseAuth auth;


    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_upload);

            image=(ImageView) findViewById(R.id.uploadImage);
            upload=(Button) findViewById(R.id.btnUpload);
            next=(Button) findViewById(R.id.btnNext);
            previous=(Button) findViewById(R.id.btnBack);

            pathArray=new ArrayList<>();
            progressDialog= new ProgressDialog(Upload_Activity.this);
            auth=FirebaseAuth.getInstance();

            mStorageRef= FirebaseStorage.getInstance().getReference();

            checkFilePermissions();

            addFilePaths();

            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(array_position>0){
                        Log.d(TAG, "onClick: back to image");
                        array_position=array_position-1;
                        loadImageFromStorage();
                    }
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(array_position<pathArray.size()-1){
                        Log.d(TAG, "onClick: Next image");
                        array_position=array_position+1;
                        loadImageFromStorage();
                    }

                }
            });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Log.d(TAG, "onClick: Uploading image");
                    progressDialog.setMessage("Uploading Image...");
                    progressDialog.show();

                    FirebaseUser user= auth.getCurrentUser();
                    String userID= user.getUid();

                    Uri uri= Uri.fromFile(new File(pathArray.get(array_position)));
                    StorageReference storageReference= mStorageRef.child("images/merchants/"+userID+".jpg");

                    storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(Upload_Activity.this, "Uploaded successfully", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Upload_Activity.this, "Not Uploaded ", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();

                        }
                    })

                    ;





                }


        });





        }




    private void checkFilePermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = Upload_Activity.this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += Upload_Activity.this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if (permissionCheck != 0) {
                this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
            }
        }else{
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    private void addFilePaths(){

        Log.d(TAG, "addFilePaths: Adding file paths.");
        String path= System.getenv("STORAGE");
        pathArray.add(path+"/storage/emulated/0/Download/Americanfood.jpg");
        pathArray.add(path+"/storage/emulated/0/Download/image2.jpg");
        loadImageFromStorage();

    }

    private void loadImageFromStorage(){
        try {
            String path = pathArray.get(array_position);
            File d = new File(path, "");
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(d));
            image.setImageBitmap(bitmap);
        }catch (FileNotFoundException e){
            Log.e(TAG, "loadImageFromStorage not found");
        }

    }



    }
