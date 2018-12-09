package gcsenxmk.q.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gcsenxmk.q.R;

public class MercMainOverview extends Fragment {
    private static final String TAG = "Tab2Fragment";

    private RecyclerView recyclerView;
    private ArrayList<String> mNames;
    private ArrayList<String> mImageUrls;

    private SwipeRefreshLayout swipeContainer;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNames = new ArrayList<>();
        mImageUrls = new ArrayList<>();
        Log.d(TAG, "onCreate: Started.");



        initImageBitmaps();



    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.merc_main_overview, container, false);

        recyclerView = view.findViewById(R.id.merc_recyclerview);
        MercRecyclerViewAdapter adapter = new MercRecyclerViewAdapter(mNames, mImageUrls, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //adapter.notifyDataSetChanged();

        TextView aveWaitingTime, queue_length;
        aveWaitingTime = view.findViewById(R.id.AveWaitingTime);
        queue_length = view.findViewById(R.id.queue_length);

        Button operateQueue;
        operateQueue = view.findViewById(R.id.operateQueue);
        operateQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),Merc_QueueDisplay.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://images.pexels.com/photos/461198/pexels-photo-461198.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
        mNames.add("Turkish Cuisine.");

        mImageUrls.add("https://images.pexels.com/photos/1268558/pexels-photo-1268558.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
        mNames.add("Cheers!");

        mImageUrls.add("https://images.pexels.com/photos/376464/pexels-photo-376464.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
        mNames.add("Pancakes!");

        mImageUrls.add("https://images.pexels.com/photos/5938/food-salad-healthy-lunch.jpg?auto=compress&cs=tinysrgb&h=750&w=1260");
        mNames.add("Salad.");

        mImageUrls.add("https://images.pexels.com/photos/958545/pexels-photo-958545.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
        mNames.add("Indian Cuisine");

        mImageUrls.add("https://images.pexels.com/photos/8500/food-dinner-pasta-spaghetti-8500.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        mNames.add("Spaghetti");

        mImageUrls.add("https://images.pexels.com/photos/132694/pexels-photo-132694.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        mNames.add("Chocolate Cake");



    }
}