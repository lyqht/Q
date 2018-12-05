package gcsenxmk.q;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ImageAdapterRecycler extends RecyclerView.Adapter<ImageAdapterRecycler.ImageViewHolder>{

    private Context mContext;
    private List<Upload> mUploads;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference queueDatabaseRef;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private FirebaseUser merchant;



    private DatabaseReference merchantDatabaseRef;
    public ImageAdapterRecycler(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_cardview, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        Picasso.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.imageButton);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public ImageButton imageButton;




        public ImageViewHolder(View itemView) {
            super(itemView);

            firebaseAuth=FirebaseAuth.getInstance();

            user = firebaseAuth.getCurrentUser();


            textViewName = itemView.findViewById(R.id.text_view_name);
            imageButton = itemView.findViewById(R.id.image_view_upload);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");
                    merchantDatabaseRef=FirebaseDatabase.getInstance().getReference("Merchants");
                    queueDatabaseRef= FirebaseDatabase.getInstance().getReference("Queue");


                    queueDatabaseRef.orderByChild("queuename").equalTo(textViewName.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    //merchantDatabaseRef.orderByChild("name").equalTo(textViewName.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot childSnapshot: dataSnapshot.getChildren()){
                            user = firebaseAuth.getCurrentUser();
                            QueueInformation queueInformation = childSnapshot.getValue(QueueInformation.class);
                            String merchantID = childSnapshot.getKey();

                            if(queueInformation.queue.contains(user.getUid())){
                                System.out.println("already in the queue");
                               // Toast.makeText(RecyclerActivity, "You already register for this queue", Toast.LENGTH_LONG).show();
                            }else {
                                queueInformation.queue.add(user.getUid());
                                queueDatabaseRef.child(merchantID).setValue(queueInformation);
                            }

                        }
                    }




                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {



                    }
                });









//                    DatabaseReference queueRef = FirebaseDatabase.getInstance().getReference("Queue");
//                    Query query = queueRef.orderByChild("queuename").equalTo(textViewName.getText().toString());
//
//                    query.orderByChild("queue").addChildEventListener(new ChildEventListener() {
//                        @Override
//                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                            ArrayList<String> queue= dataSnapshot.getValue(ArrayList.class);
//                            queue.add("hello");
//                            dataSnapshot.getRef().child("queue").setValue(queue);
//
//
//                        }
//
//                        @Override
//                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                        }
//
//                        @Override
//                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                        }
//
//                        @Override
//                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
//                }
//            });


                }


            });


        }
    }}










