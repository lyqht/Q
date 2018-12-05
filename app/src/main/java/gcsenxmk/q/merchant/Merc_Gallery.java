package gcsenxmk.q.merchant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import gcsenxmk.q.R;

public class Merc_Gallery extends AppCompatActivity {

    private static final String TAG = "GalleryActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merc_gallery);
        getIncomingIntent();
        Log.d(TAG, "onCreate: started.");
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")) {
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imageUrl = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("image_name");

            setGallery(imageUrl, imageName);
        }
    }

    private void setGallery(String imageUrl, String imageName) {
        Log.d(TAG, "setGallery:setting to image and name to widgets.");

        TextView name = findViewById(R.id.merc_queue_name);
        name.setText(imageName);

        ImageView image = findViewById(R.id.merc_queue_image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
    }
}