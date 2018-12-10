package gcsenxmk.q.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import gcsenxmk.q.R;
import gcsenxmk.q.database.QueueInformation;

public class Cust_Gallery extends AppCompatActivity {

    private static final String TAG = "GalleryActivity";
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private String priority = "false";
    private boolean joinOnce = false;
    protected boolean makeToast = false;

    private DatabaseReference queueDatabaseRef;
    private DatabaseReference customerDatabaseRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_desc);
        getIncomingIntent();
        Log.d(TAG, "onCreate: started.");
    }

    // TODO : MODIFY getIncomingIntent() & setGallery for more details to be displayed.
    // TODO - tags, operating hours, location (Google Map API)

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent for Customer: checking for incoming intents.");
        if ( getIntent().getExtras() != null) {
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            String imageUrl = getIntent().getStringExtra("image_url");
            String queueName = getIntent().getStringExtra("queue_name");
            String queueWaitingTime = getIntent().getStringExtra("queue_waiting_time");
            String queueNumPeople = String.valueOf(getIntent().getStringExtra("queue_num_people"));
            String queueLocation = getIntent().getStringExtra("location");
            String queueDesc = getIntent().getStringExtra("description");

            setGallery(imageUrl, queueName, queueWaitingTime, queueNumPeople, queueLocation, queueDesc);
        }
    }

    private void setGallery(String imageUrl, String queueName,
                            String queueTime, String queueNumPeople,
                            String queueLocation, String queueDesc) {
        Log.d(TAG, "setGallery:setting to image and name to widgets.");

        TextView name = findViewById(R.id.stall_desc_name);
        name.setText(queueName);

        TextView waitingTime = findViewById(R.id.stall_desc_waiting_time);
        waitingTime.setText(queueTime);

        TextView numPeople = findViewById(R.id.stall_desc_num_people);
        numPeople.setText(queueNumPeople);

        TextView description = findViewById(R.id.stall_desc);
        description.setText(queueDesc);

        TextView location = findViewById(R.id.stall_location);
        location.setText(queueLocation);

        ImageView image = findViewById(R.id.stall_image);
        Picasso.with(this)
                .load(imageUrl)
                .fit()
                .centerInside()
                .into(image);
        Button joinQButton = findViewById(R.id.joinQ);
        joinQButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth=FirebaseAuth.getInstance();
                customerDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
                queueDatabaseRef= FirebaseDatabase.getInstance().getReference("Queue");
                Log.d(TAG,"joinQ Button clicked");
                queueDatabaseRef.orderByChild("name").equalTo(name.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    //merchantDatabaseRef.orderByChild("name").equalTo(textViewName.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot childSnapshot: dataSnapshot.getChildren()){
                            user = firebaseAuth.getCurrentUser();
                            QueueInformation queueInformation = childSnapshot.getValue(QueueInformation.class);
                            String merchantID = childSnapshot.getKey();

                            if(queueInformation.queue.contains(user.getUid())){
                                Log.d("Join Queue", "Already in queue.");

                            }
                            else {
                                if(priority.equals("true")){
                                    queueInformation.queue.add(0,user.getUid());
                                }else{
                                    queueInformation.queue.add(user.getUid());
                                }
                                queueDatabaseRef.child(merchantID).setValue(queueInformation);
                                customerDatabaseRef.child(user.getUid()).child("merchantID").setValue(merchantID);
                                numPeople.setText(String.valueOf(queueInformation.getNumPeople()));
                                Log.d(TAG, "adding customer to queue.");

                                Toast toast = Toast.makeText(v.getContext(),"Joined Queue!", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                                Intent intent = new Intent (v.getContext(), Cust_MainActivity.class);
                                v.getContext().startActivity(intent);

                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("Database", "Error");

                    }
                });


            }


        });

    }
}