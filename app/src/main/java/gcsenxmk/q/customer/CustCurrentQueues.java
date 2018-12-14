package gcsenxmk.q.customer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import gcsenxmk.q.R;
import gcsenxmk.q.database.QueueInformation;

public class CustCurrentQueues extends Fragment {
    private static final String TAG = "CustCurrentQueues";
    private FirebaseAuth firebaseAuth;
    private TextView queue_name;
    private TextView est_wait_time_data;
    private TextView num_people_data;
    private Button btnDropQ, btnRefresh;

    private FirebaseUser user;
    public int len;
    QueueInformation queueInformation;
    private DatabaseReference queueDatabaseRef;
    private DatabaseReference customerDatabaseRef;

    private SwitchCompat NotificationEnabled;
    private TextView NotificationEnabledText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cust_current_queues, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        queue_name = view.findViewById(R.id.queue_name);
        btnDropQ = view.findViewById(R.id.btnDropQueue);
        btnRefresh= view.findViewById(R.id.buttonCurrentRefresh);
        est_wait_time_data= view.findViewById(R.id.waiting_time_data);
        num_people_data = view.findViewById(R.id.num_people_data);

        customerDatabaseRef=FirebaseDatabase.getInstance().getReference("Users");
        queueDatabaseRef = FirebaseDatabase.getInstance().getReference("Queue");

        // Notifications
        NotificationEnabled = view.findViewById(R.id.cust_profile_notifications_switch);
        NotificationEnabledText = view.findViewById(R.id.cust_profile_enable_notifications_textview);
        NotificationEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Toast.makeText(getContext(), "Notifications Enabled", Toast.LENGTH_SHORT).show();
            }
        });

        customerDatabaseRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("merchantID")){

                    queueDatabaseRef.child(dataSnapshot.child("merchantID").getValue().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String merc_name=dataSnapshot.child("name").getValue().toString();

                            queue_name.setText(merc_name);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                    // queue_name.setText(dataSnapshot.child("merchantID").getValue().toString());

                    queueDatabaseRef.child(dataSnapshot.child("merchantID").getValue().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            queueInformation = dataSnapshot.getValue(QueueInformation.class);
                            int queueSize =queueInformation.getNumPeople();
                            int index=queueInformation.queue.indexOf(user.getUid());
                            int average_wait_time=queueInformation.getAvewaiting();
                            int waittime = (index+1) *average_wait_time;
                            est_wait_time_data.setText(Integer.toString(waittime) + " mins");
                            num_people_data.setText(String.valueOf(queueSize));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    System.out.println(dataSnapshot.child("merchantID").getValue());
                }else
                {System.out.println(("hard luck"));
                }

                System.out.println(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(CustCurrentQueues.this).attach(CustCurrentQueues.this).commit();
            }
        });



        btnDropQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                customerDatabaseRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child("merchantID").exists()){
                            String merchant_id=dataSnapshot.child("merchantID").getValue().toString();
                            customerDatabaseRef.child(user.getUid()).child("merchantID").removeValue();
                            //System.out.println("id"+merchant_id);


                            queueDatabaseRef.child(merchant_id).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    int index=queueInformation.queue.indexOf(user.getUid());
                                    queueInformation.queue.remove(index);
                                    System.out.println("indexxx"+index);
                                    Log.d(TAG,"Remove Customer from queue." + queueInformation.getNumPeople());
                                    queueDatabaseRef.child(merchant_id).setValue(queueInformation);

                                    queue_name.setText("Please join a Queue");
                                    est_wait_time_data.setText("No Queue yet");
                                    num_people_data.setText("No Queue Yet");
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                        else {
                            Toast.makeText(getContext(),"You are not in a queue!", Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });

        return view;
    }
}
