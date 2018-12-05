package gcsenxmk.q;

import android.arch.lifecycle.CompositeGeneratedAdaptersObserver;
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

import java.util.ArrayList;

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

        queueDatabaseRef = FirebaseDatabase.getInstance().getReference("Queue");




        queueDatabaseRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    queueInformation = dataSnapshot.getValue(QueueInformation.class);
                    len = queueInformation.queue.size();
                    int waittime = len *5;

                    est_wait_time_data.setText(Integer.toString(waittime));
                    queue_name.setText(queueInformation.queuename);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        btnDropQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int index= queueInformation.queue.indexOf(user.getUid());
                    queueInformation.queue.remove(index);
                    Toast.makeText(CustomerHomePageActivity.this, "drop the service", Toast.LENGTH_SHORT).show();
                    queueDatabaseRef.child(user.getUid()).child("queue").setValue(queueInformation.queue);




            }
        });



        btnjoinNewQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerHomePageActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });



    }


}
