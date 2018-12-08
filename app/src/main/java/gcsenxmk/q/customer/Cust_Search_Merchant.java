package gcsenxmk.q.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import gcsenxmk.q.AppCompatPreferenceActivity;
import gcsenxmk.q.R;
import gcsenxmk.q.database.MerchantInformation_forSearch;

public class Cust_Search_Merchant extends AppCompatPreferenceActivity {

    EditText mSearchField;
    RecyclerView merchantlistResult;
    DatabaseReference mDatabaseReference;
    Button mSearchBtn;
    private static final String TAG = "Merchant_Search";
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_search_result);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Merchants");


        mSearchField = findViewById(R.id.cme_editTxt_SearchBar);
        mSearchBtn = findViewById(R.id.BtnSearchResult);

        merchantlistResult = (RecyclerView) findViewById(R.id.recycler_view);
        merchantlistResult.setHasFixedSize(true);
        merchantlistResult.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

            }
        });

    }*/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cust_merchant_searchresult, container, false);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Merchants");

        mSearchField = findViewById(R.id.cme_editTxt_SearchBar);
        mSearchBtn = findViewById(R.id.BtnSearchResult);

        merchantlistResult = (RecyclerView) findViewById(R.id.recycler_view);
        merchantlistResult.setHasFixedSize(true);
        merchantlistResult.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = mSearchField.getText().toString();
                firebaseUserSearch(searchText);
                startActivity(new Intent(Cust_Search_Merchant.this, UsersViewHolder.class));

            }
        });

        return view;
    }

        private void firebaseUserSearch(String searchText) {
        Log.d(TAG, "firebase Search start");
        Toast.makeText(Cust_Search_Merchant.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mDatabaseReference.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<MerchantInformation_forSearch, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MerchantInformation_forSearch, UsersViewHolder>(

                MerchantInformation_forSearch.class,
                R.layout.cust_merchant_searchresult,
                UsersViewHolder.class,
                firebaseSearchQuery

        )  {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, MerchantInformation_forSearch model, int position) {

                Log.d(TAG, "getdata");
                viewHolder.setDetails(getApplicationContext(), model.getQueuename(), model.getWaiting_time(), model.getQueueimage(), model.getNumberofCust());

            }
        };

        merchantlistResult.setAdapter(firebaseRecyclerAdapter);

    }

    // View Holder Class

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx, String queueName, int Waiting_time, String merchantImage, int NumberofCust) {

            TextView queue_name = (TextView) mView.findViewById(R.id.queueName);
            TextView numberofCust = (TextView) mView.findViewById(R.id.queueNumPeople);
            TextView waiting_time = (TextView) mView.findViewById(R.id.minutes);
            ImageView merchant_image = (ImageView) mView.findViewById(R.id.queueImage);


            queue_name.setText(queueName);
            numberofCust.setText(NumberofCust);
            waiting_time.setText(Waiting_time);
            Glide.with(ctx).load(merchantImage).into(merchant_image);


        }
    }
}