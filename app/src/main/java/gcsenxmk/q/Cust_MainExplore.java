package gcsenxmk.q;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Cust_MainExplore extends AppCompatActivity {

    ImageButton imageButtonPhotography;
    ImageButton imageButtonFnB;
    ImageButton imageButtonMusic;
    ImageButton imageButtonIT;
    ImageButton imageButtonLimitedT;
    ImageButton imageButtonExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_main_explore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Widget References
        imageButtonPhotography = findViewById(R.id.imageButtonPhotography);
        imageButtonFnB = findViewById(R.id.imageButtonFnB);
        imageButtonMusic = findViewById(R.id.imageButtonMusic);
        imageButtonIT = findViewById(R.id.imageButtonIT);
        imageButtonLimitedT = findViewById(R.id.imageButtonLimitedT);
        imageButtonExtra = findViewById(R.id.imageButtonExtra);

        // TODO: add functions to filter out results
        imageButtonPhotography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageButtonFnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageButtonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageButtonIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageButtonLimitedT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageButtonExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
