package gcsenxmk.q.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

import gcsenxmk.q.database.QueueInformation;
import gcsenxmk.q.R;
import gcsenxmk.q.customer.RecyclerActivity;

public class CustomerHomePageActivity extends AppCompatActivity{




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

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerhomepage);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        queue_name = (TextView) findViewById(R.id.queue_name);
        btnjoinNewQ = (Button) findViewById(R.id.btnjoinq);
        btnDropQ = (Button) findViewById(R.id.btnDropQueue);
        est_wait_time_data= (TextView) findViewById(R.id.waiting_time_data);

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



        btnjoinNewQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customerDatabaseRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child("merchantID").exists()){
                            Toast.makeText(CustomerHomePageActivity.this,"You're already in a queue",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Intent intent = new Intent(CustomerHomePageActivity.this, RecyclerActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });



    }


}
