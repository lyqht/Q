package gcsenxmk.q;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MercMainOverview extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private RecyclerView recyclerView;
    private ArrayList<String> mNames;
    private ArrayList<String> mImageUrls;

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

        recyclerView = view.findViewById(R.id.queue_recyclerview);
        MercRecyclerViewAdapter adapter = new MercRecyclerViewAdapter(mNames, mImageUrls, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://images5.alphacoders.com/787/thumb-350-787097.png");
        mNames.add("Kono Suba!");

        mImageUrls.add("https://images5.alphacoders.com/782/thumb-350-782977.png");
        mNames.add("Megumin");

        mImageUrls.add("https://images6.alphacoders.com/806/thumb-350-806274.png");
        mNames.add("Megumin & Chomusuke");

        mImageUrls.add("https://images2.alphacoders.com/704/thumb-350-704380.png");
        mNames.add("Megumin Cafe");

        mImageUrls.add("https://images2.alphacoders.com/782/thumb-350-782992.png");
        mNames.add("ArchWizard Megumin!");

        mImageUrls.add("https://images8.alphacoders.com/782/thumb-1920-782972.png");
        mNames.add("Chibi Wiz");

        mImageUrls.add("https://images5.alphacoders.com/798/thumb-350-798247.png");
        mNames.add("Iris");


    }
}