package gcsenxmk.q;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Cust_SearchResult extends AppCompatActivity {

    private RecyclerView qRecycler;
    private ArrayList<AlohaQueue> queues;
    private QueueAdapter qAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_search_result);

        // Make new list of queues
        queues = new ArrayList<>();

        // Initialize the RecyclerView
        qRecycler = findViewById(R.id.cust_queue_recycler);
        qRecycler.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the Adapter for RecyclerView
        qAdapter = new QueueAdapter(this, queues);
        qRecycler.setAdapter(qAdapter);

        initializeData();

    }

    private void initializeData() {
        //TODO: Get the resources from XML File / SQLiteDatabase

        String[] queueNames;
        int[] queueWaitingTimes;
        int[] queueNumPeople;

        qAdapter.notifyDataSetChanged();

    }


}
