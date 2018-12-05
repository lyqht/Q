package gcsenxmk.q;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gcsenxmk.q.R;

public class MercQueueCreated extends AppCompatActivity {

    TextView gobackmercmainactivity;
    ImageView queuecreated;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merc_queue_created);
        queuecreated = findViewById(R.id.queuecreatedimage);
        gobackmercmainactivity = findViewById(R.id.gobackmercmainactivity);

        gobackmercmainactivity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MercQueueCreated.this, Merc_MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            }
        });


    }
}
