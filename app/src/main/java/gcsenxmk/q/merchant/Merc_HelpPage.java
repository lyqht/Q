package gcsenxmk.q.merchant;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import gcsenxmk.q.R;

//TODO - contain tutorial on how to use the app

public class Merc_HelpPage extends AppCompatActivity {
    private static final String TAG = "Helppage_merc";
    private Button newQueueButton, configQueueButton, deleteQueueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merc_help_page);
        Log.i(TAG,"Executing Merc_HelpPage");

        newQueueButton = (Button) findViewById(R.id.newQueueButton);
        configQueueButton = (Button) findViewById(R.id.configQueueButton);
        deleteQueueButton = (Button) findViewById(R.id.deleteQueueButton);

        Log.d(TAG, "onCreate: Started.");

        newQueueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Merc_HelpPage.this,Merc_HelpPage_CreateQueue.class);
                startActivity(intent);
            }
        });

        deleteQueueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Merc_HelpPage.this,Merc_HelpPage_DeleteQueue.class);
                startActivity(intent);
            }
        });

        configQueueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Merc_HelpPage.this,Merc_HelpPage_ConfigQueue.class);
                startActivity(intent);
            }
        });
    }
}

