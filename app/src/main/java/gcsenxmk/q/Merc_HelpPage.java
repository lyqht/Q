package gcsenxmk.q;

import android.support.annotation.Nullable;
import android.support.v4.view.ScrollingView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

//TODO - contain tutorial on how to use the app

public class Merc_HelpPage extends AppCompatActivity {
    private static final String TAG = "Helppage_merc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merc_help_page);
        Log.d(TAG, "onCreate: Starting Merc_HelpPage.");

    }

}

