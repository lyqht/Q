package gcsenxmk.q.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import gcsenxmk.q.AppCompatPreferenceActivity;
import gcsenxmk.q.R;
import gcsenxmk.q.database.MerchantInformation_forSearch;
import gcsenxmk.q.database.QueueInformation;

public class Cust_Search_Merchant extends AppCompatActivity {


    private EditText mSearchField;
    private ImageView mSearchBtn;
    private Context mContext;
    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_merchant_searchresult);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Merchants");


        mSearchField = findViewById(R.id.testcme_editTxt_SearchBar);
        mSearchBtn = findViewById(R.id.imageView2);

        mResultList = (RecyclerView) findViewById(R.id.testmerchant_search_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = mSearchField.getText().toString();
                firebaseUserSearch(searchText);
            }
        });

    }

    private void firebaseUserSearch(String searchText) {

        Toast.makeText(Cust_Search_Merchant.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<MerchantInformation_forSearch, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MerchantInformation_forSearch, UsersViewHolder>(

                MerchantInformation_forSearch.class,
                R.layout.cust_list_item,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, MerchantInformation_forSearch model, int position) {


                viewHolder.setDetails(getApplicationContext(), model.getName(), Integer.toString(model.getAvewaiting()), model.getImageUrl(),
                        Integer.toString(model.getNumPeople()),
                        model.getDesc(), model.getLocation());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }


    // View Holder Class

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;
        private FirebaseAuth firebaseAuth;
        private FirebaseUser user;
        private FirebaseUser merchant;
        private final String TAG = "ImageAdapterRecycler";
        private String priority = "false";
        private boolean joinOnce = false;
        protected boolean makeToast = false;
        Context mContext;
        private DatabaseReference merchantDatabaseRef;
        private DatabaseReference queueDatabaseRef;
        private DatabaseReference customerDatabaseRef;

        public UsersViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDetails(Context ctx, String userName, String avewaiting, String userImage, String numPeople, String desc, String location){
            TextView user_name = (TextView) mView.findViewById(R.id.queueName);
            TextView user_waitingTime = (TextView) mView.findViewById(R.id.queueWaitTime);
            ImageView user_image = (ImageView) mView.findViewById(R.id.queueImage);
            TextView qNumPeople= mView.findViewById(R.id.queueNumPeople);
            TextView description= mView.findViewById(R.id.stall_desc);
            Button joinQButton = mView.findViewById(R.id.joinQ_recycler);
            TextView merchant_location = mView.findViewById(R.id.stall_location);
            user_name.setText(userName);
            qNumPeople.setText(numPeople);
            //description.setText(desc);
            //merchant_location.setText(location);
            qNumPeople.setText(numPeople);
            user_waitingTime.setText(avewaiting);
            Glide.with(ctx).load(userImage).into(user_image);
            firebaseAuth=FirebaseAuth.getInstance();
            user_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Card's image Button Clicked.");
                    Intent i = new Intent(v.getContext(),Cust_Gallery.class);
                    i.putExtra("image_url", userImage);
                    i.putExtra("location", location);
                    i.putExtra("queue_name", userName);
                    i.putExtra("queue_waiting_time", avewaiting);
                    i.putExtra("queue_num_people", numPeople);
                    i.putExtra("description", desc);
                    v.getContext().startActivity(i);
                }
            });

            joinQButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customerDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
                    merchantDatabaseRef=FirebaseDatabase.getInstance().getReference("Merchants");
                    queueDatabaseRef= FirebaseDatabase.getInstance().getReference("Queue");
                    Log.d(TAG,"joinQ Button clicked");

                    queueDatabaseRef.orderByChild("name").equalTo(user_name.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        //merchantDatabaseRef.orderByChild("name").equalTo(textViewName.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot childSnapshot: dataSnapshot.getChildren()){
                                user = firebaseAuth.getCurrentUser();
                                QueueInformation queueInformation = childSnapshot.getValue(QueueInformation.class);
                                String merchantID = childSnapshot.getKey();

                                if(queueInformation.queue.contains(user.getUid())){
                                    Log.d("Join Queue", "Already in queue.");

                                }
                                else {
                                    if(priority.equals("true")){
                                        queueInformation.queue.add(0,user.getUid());
                                    }else{
                                        queueInformation.queue.add(user.getUid());
                                    }
                                    queueDatabaseRef.child(merchantID).setValue(queueInformation);
                                    joinOnce = true;
                                    customerDatabaseRef.child(user.getUid()).child("merchantID").setValue(merchantID);
                                    qNumPeople.setText(String.valueOf(queueInformation.getNumPeople()));
                                    Log.d(TAG, "adding customer to queue.");

                                    Toast toast = Toast.makeText(v.getContext(),"Joined Queue!", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER, 0, 0);
                                    toast.show();

                                    Intent intent = new Intent (v.getContext(), Cust_MainActivity.class);
                                    v.getContext().startActivity(intent);

                                }

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.e("Database", "Error");

                        }
                    });


                }


            });

        }




    }

}
