package gcsenxmk.q.customer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import gcsenxmk.q.login.CustomerHomePageActivity;

public class CustCurrentQueues extends Fragment {
    private static final String TAG = "CustCurrentQueues";
    private FirebaseAuth firebaseAuth;
    private TextView queue_name;
    private TextView est_wait_time_data;
    private Button btnjoinNewQ;
    private  Button btnDropQ;

    private FirebaseUser user;
    public int len;
    QueueInformation queueInformation;


    private DatabaseReference queueDatabaseRef;
    private DatabaseReference customerDatabaseRef;
    private DatabaseReference merchantDatabaseRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cust_current_queues, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        queue_name = view.findViewById(R.id.queue_name);
        btnjoinNewQ = view.findViewById(R.id.btnjoinq);
        btnDropQ = view.findViewById(R.id.btnDropQueue);
        est_wait_time_data= view.findViewById(R.id.waiting_time_data);

        merchantDatabaseRef=FirebaseDatabase.getInstance().getReference("Merchants");
        customerDatabaseRef=FirebaseDatabase.getInstance().getReference("Users");

        queueDatabaseRef = FirebaseDatabase.getInstance().getReference("Queue");

        System.out.println("hola");
        customerDatabaseRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("merchantID")){

                    merchantDatabaseRef.child(dataSnapshot.child("merchantID").getValue().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
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
                            len = queueInformation.queue.size();
                            int index=queueInformation.queue.indexOf(user.getUid());
                            int average_wait_time=queueInformation.wait_time;
                            int waittime = index *average_wait_time;
                            est_wait_time_data.setText(Integer.toString(waittime));
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

                                    queueDatabaseRef.child(merchant_id).child("queue").setValue(queueInformation.queue);

                                    queue_name.setText("Please join a Queue");
                                    est_wait_time_data.setText("No Queue yet");
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

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
