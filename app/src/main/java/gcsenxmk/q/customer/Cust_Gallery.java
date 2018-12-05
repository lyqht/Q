package gcsenxmk.q.customer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import gcsenxmk.q.R;

public class Cust_Gallery extends AppCompatActivity {

    private static final String TAG = "GalleryActivity";

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
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("queue_name")) {
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imageUrl = getIntent().getStringExtra("image_url");
            String queueName = getIntent().getStringExtra("queue_name");
            String queueWaitingTime = getIntent().getStringExtra("queue_waiting_time");
            String queueNumPeople = String.valueOf(getIntent().getStringExtra("queue_num_people"));

            setGallery(imageUrl, queueName, queueWaitingTime, queueNumPeople);
        }
    }

    private void setGallery(String imageUrl, String queueName,
                            String queueTime, String queueNumPeople) {
        Log.d(TAG, "setGallery:setting to image and name to widgets.");

        TextView name = findViewById(R.id.stall_desc_name);
        name.setText(queueName);
        TextView waitingTime = findViewById(R.id.stall_desc_waiting_time);
        waitingTime.setText(queueTime);
        TextView numPeople = findViewById(R.id.stall_desc_num_people);
        numPeople.setText(queueNumPeople);
        ImageView image = findViewById(R.id.stall_image);
        //Glide.with(this).load(Integer.valueOf(imageUrl)).into(image);

        Picasso.with(this)
                .load(imageUrl)
                .fit()
                .centerCrop()
                .into(image);
    }
}