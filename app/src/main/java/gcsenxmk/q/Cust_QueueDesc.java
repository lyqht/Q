package gcsenxmk.q;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;


public class Cust_QueueDesc extends AppCompatActivity {

    Button joinQButton;
    Button prevImgButton;
    Button nextImgButton;
    ArrayList<Integer> images;
    ImageView currentImage;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_desc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Widget References
        images = new ArrayList<>();
        images.add(R.drawable.kinmoo_01);
        images.add(R.drawable.kinmoo_02);
        images.add(R.drawable.kinmoo_03);
        currentImage = findViewById(R.id.stall_image);
        prevImgButton = findViewById(R.id.stall_image_prev_button);
        nextImgButton = findViewById(R.id.stall_image_next_button);

        // Binding onClickListener to Buttons to switch images
        prevImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < 0) count = -1 * count;
                int index = (count)%images.size();
                count -= 1;
                int id = images.get(index);
                currentImage.setImageResource(id);
            }
        });

        nextImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = (count)%images.size();
                count += 1;
                int id = images.get(index);
                currentImage.setImageResource(id);
            }
        });


        // TODO: Join Queue Button
        joinQButton = findViewById(R.id.joinQ);
        joinQButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO 1.1: Pending Database Part
                // TODO 1.2: upon click, user joins queue
                // TODO 1.3: if queue joining successful, then change text and animate joinQButton
                joinQButton.setBackgroundColor(getColor(R.color.colorAccent));
                joinQButton.setText(R.string.joined_queue);

                // TODO 1.4: update number of people queueing
            }
        });
    }

}
