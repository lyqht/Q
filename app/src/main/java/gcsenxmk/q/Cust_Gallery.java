package gcsenxmk.q;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class Cust_Gallery extends AppCompatActivity {

    private static final String TAG = "GalleryActivity";

    //TODO: replace current Cust_QueueDesc when firebase is ready.

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_gallery);
        getIncomingIntent();
        Log.d(TAG, "onCreate: started.");
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("queue_name")) {
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imageUrl = getIntent().getStringExtra("image_url");
            String queueName = getIntent().getStringExtra("queue_name");

            //TODO: after setGallery method is set
            //setGallery(imageUrl, queueName);
        }
    }

    //TODO: setGallery method
    private void setGallery(String imageUrl, String queueName, String queueTime, String queueNumPeople) {
        Log.d(TAG, "setGallery:setting to image and name to widgets.");

        TextView name = findViewById(R.id.stall_desc_name_kinmoo);
        name.setText(queueName);


        ImageView image = findViewById(R.id.stall_image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
    }
}