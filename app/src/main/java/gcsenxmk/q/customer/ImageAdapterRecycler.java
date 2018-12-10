package gcsenxmk.q.customer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;


import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import gcsenxmk.q.R;
import gcsenxmk.q.database.QueueInformation;
import gcsenxmk.q.database.Upload;
import gcsenxmk.q.login.CustomerHomePageActivity;

public class ImageAdapterRecycler extends RecyclerView.Adapter<ImageAdapterRecycler.ImageViewHolder>{

    private Context mContext;
    private List<QueueInformation> mUploads;
    
    private DatabaseReference queueDatabaseRef;
    private DatabaseReference customerDatabaseRef;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private FirebaseUser merchant;
    private final String TAG = "ImageAdapterRecycler";
    private String priority = "false";

    public ImageAdapterRecycler(Context context, List<QueueInformation> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.cust_list_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        QueueInformation uploadCurrent = mUploads.get(position);
        holder.name = uploadCurrent.getName();
        holder.description = uploadCurrent.getDesc();
        holder.waitingTime = Integer.toString(uploadCurrent.getAvewaiting());
        holder.location = uploadCurrent.getLocation();
        holder.imageURL = uploadCurrent.getimageUrl();
        holder.numPeople = Integer.toString(uploadCurrent.getNumPeople());
        Log.d(TAG,String.valueOf(uploadCurrent.getQueue().size()));

        holder.qNumPeople.setText(holder.numPeople);
        holder.qName.setText(holder.name);
        Picasso.with(mContext)
                .load(holder.imageURL)
                .fit()
                .centerCrop()
                .into(holder.qImage);
        holder.qWaitTime.setText(holder.waitingTime);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private TextView qName;
        private TextView qWaitTime;
        private TextView qNumPeople;
        private ImageButton qImage;
        private Button joinQButton;

        private String name;
        private String imageURL;
        private String description;
        private String waitingTime;
        private String numPeople;
        private String location;

        public ImageViewHolder(View itemView) {
            super(itemView);
            firebaseAuth=FirebaseAuth.getInstance();
            user = firebaseAuth.getCurrentUser();

            qName = itemView.findViewById(R.id.queueName);
            qWaitTime = itemView.findViewById(R.id.queueWaitTime);
            qImage = itemView.findViewById(R.id.queueImage);
            qNumPeople = itemView.findViewById(R.id.queueNumPeople);
            joinQButton = itemView.findViewById(R.id.joinQ_recycler);

            qImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Card's image Button Clicked.");
                    Intent i = new Intent(mContext,Cust_Gallery.class);
                    i.putExtra("image_url", imageURL);
                    i.putExtra("location", location);
                    i.putExtra("queue_name", name);
                    i.putExtra("queue_waiting_time", waitingTime);
                    i.putExtra("queue_num_people", numPeople);
                    i.putExtra("desc", description);
                    mContext.startActivity(i);
                }
            });

            customerDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
            customerDatabaseRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("priority").getValue() != null) {
                        priority = dataSnapshot.child("priority").getValue().toString();
                    }
                    if (dataSnapshot.hasChild("merchantID")) {
                        joinQButton.setClickable(false);
                        joinQButton.setBackgroundResource(R.drawable.already_join_button);
                    }
                    else {
                        joinQButton.setClickable(true);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            joinQButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //customerDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
                    queueDatabaseRef= FirebaseDatabase.getInstance().getReference("Queue");
                    Log.d(TAG,"joinQ Button clicked");
                    queueDatabaseRef.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
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
    }}






