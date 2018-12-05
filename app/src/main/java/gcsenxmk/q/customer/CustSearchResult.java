package gcsenxmk.q.customer;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gcsenxmk.q.R;

public class CustSearchResult extends Fragment {

    private RecyclerView qRecycler;
    private ArrayList<AlohaQueue> queues;
    private CustRecyclerViewAdapter qAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cust_search_result, container,false);

        // Make new list of queues
        queues = new ArrayList<>();

        // Initialize the RecyclerView
        qRecycler = view.findViewById(R.id.cust_queue_recycler);
        qRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the Adapter for RecyclerView
        qAdapter = new CustRecyclerViewAdapter(getContext(), queues);
        qRecycler.setAdapter(qAdapter);

        initializeData();
        return view;

    }

    private void initializeData() {
        //TODO: REMOVE PLACEHOLDER ARRAYS BELOW AFTER FIREBASE DATABASE IS UP

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
