package gcsenxmk.q.merchant;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import gcsenxmk.q.R;

//TODO - contain tutorial on how to use the app

public class Merc_HelpPage extends Fragment {
    private static final String TAG = "Helppage_merc";
    private Button operatingButton, configQueueButton, emailButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.merc_help_page,container,false);
        super.onCreate(savedInstanceState);
        Log.i(TAG,"Executing Merc_HelpPage");

        operatingButton = (Button) view.findViewById(R.id.operatingButton);
        configQueueButton = (Button) view.findViewById(R.id.configQueueButton);
        emailButton = (Button) view.findViewById(R.id.emailButton);

        operatingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(),Merc_HelpPage_CreateQueue.class);
                startActivity(intent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(),Merc_HelpPage_DeleteQueue.class);
                startActivity(intent);
            }
        });

        configQueueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(),Merc_HelpPage_ConfigQueue.class);
                startActivity(intent);
            }
        });
        return view;
    }
}

