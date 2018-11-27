package gcsenxmk.q;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class Cust_SearchResult extends AppCompatActivity {

    private RecyclerView qRecycler;
    private ArrayList<AlohaQueue> queues;
    private CustQueueAdapter qAdapter;


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
        qAdapter = new CustQueueAdapter(this, queues);
        qRecycler.setAdapter(qAdapter);

        initializeData();

    }

    private void initializeData() {
        //TODO: Get the resources from XML File / SQLiteDatabase

        String[] queueNames = getResources().getStringArray(R.array.placeholder_queue_names);
        int[] queueWaitingTimes = getResources().getIntArray(R.array.placeholder_queue_waiting_time);
        int[] queueNumPeople = getResources().getIntArray(R.array.placeholder_queue_num_people);
        TypedArray queueImages = getResources().obtainTypedArray(R.array.placeholder_queue_images);

        int numQueues = queueNames.length;
        for (int i = 0; i < numQueues; i ++) {
            queues.add(new AlohaQueue(queueNames[i],
                    queueWaitingTimes[i],
                    queueNumPeople[i],
                    queueImages.getResourceId(i, 0)));
        }

        Log.i("Logcat","able to initialize data");

        qAdapter.notifyDataSetChanged();

    }


}
