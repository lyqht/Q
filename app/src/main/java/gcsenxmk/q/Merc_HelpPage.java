package gcsenxmk.q;

import android.graphics.Bitmap;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Blob;
import java.util.ArrayList;

public class Merc_HelpPage extends AppCompatActivity {
    private static final String TAG = "Helppage_merc";
    private RecyclerView recyclerView;
    private ArrayList<String> mDescriptions;
    private ArrayList<String> mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merc_help_page);

        mDescriptions = new ArrayList<>();
        mImage = new ArrayList<>();
        Log.d(TAG, "onCreate: Started.");

        initImageBitmaps();

    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.merc_help_page, container, false);

        recyclerView = view.findViewById(R.id.merc_recyclerview);
        MercRecyclerViewAdapter adapter = new MercRecyclerViewAdapter(mDescriptions, mImage, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        return view;
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImage.add("https://www.iconfinder.com/icons/1398912/check_circle_correct_mark_success_tick_yes_icon");
        mDescriptions.add("Confirmed that customer arrived on time");

        mImage.add("");
        mDescriptions.add("Wheelchair bounded");

        mImage.add("");
        mDescriptions.add("Bulk order - you might want to start to prepare early");

        mImage.add("");
        mDescriptions.add("Pregnant individual");
    }
}

