package gcsenxmk.q.merchant;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import gcsenxmk.q.R;
import gcsenxmk.q.login.SegregationActivityAfterLogin;

//TODO - contain tutorial on how to use the app

public class Merc_HelpPage extends Fragment {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private static final String TAG = "Helppage_merc";
    private Button operatingButton, configQueueButton, emailButton, switchModeButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.merc_help_page,container,false);
        super.onCreate(savedInstanceState);
        Log.i(TAG,"Executing Merc_HelpPage");
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        switchModeButton = view.findViewById(R.id.switchModeButton);
        operatingButton = view.findViewById(R.id.operatingButton);
        configQueueButton = view.findViewById(R.id.configQueueButton);
        emailButton = view.findViewById(R.id.emailButton);

        operatingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(),Merc_HelpPage_OperateQueue.class);
                startActivity(intent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(),Merc_HelpPage_EmailSetting.class);
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

        switchModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SegregationActivityAfterLogin.class));
            }
        });
        return view;
    }
}

