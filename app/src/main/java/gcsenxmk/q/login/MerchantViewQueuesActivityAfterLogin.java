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

import gcsenxmk.q.R;
import gcsenxmk.q.database.QueueInformation;
import gcsenxmk.q.merchant.QueueActivity;

public class MerchantViewQueuesActivityAfterLogin extends AppCompatActivity{
    private FirebaseAuth firebaseAuth;
    private TextView queue_length;
    private Button btnNext;
    private  Button btnDeleteQueue;
    private  Button btnCreateQueue;
    private FirebaseUser user;
    public int len;
    QueueInformation queueInformation;

    private DatabaseReference queueDatabaseRef,customerDatabaseReference, merchantDatabaseReference;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchantviewqueuesafterlogin);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        queue_length = (TextView) findViewById(R.id.queue_length);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnDeleteQueue = (Button) findViewById(R.id.btnDeleteQueue);
        btnCreateQueue= (Button) findViewById(R.id.btnCreateNewQueue);

        queueDatabaseRef = FirebaseDatabase.getInstance().getReference("Queue");
        customerDatabaseReference=FirebaseDatabase.getInstance().getReference("Users");
        merchantDatabaseReference=FirebaseDatabase.getInstance().getReference("Merchants");

        queueDatabaseRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    queueInformation = dataSnapshot.getValue(QueueInformation.class);
                    len = queueInformation.queue.size();
                    //System.out.println(len);
                    queue_length.setText(Integer.toString(len));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(len>=1) {
                    System.out.println(queueInformation.queue.size());
                    String current_user_in_queue= queueInformation.queue.get(0);
                    queueInformation.queue.remove(0);
                    System.out.println(current_user_in_queue);
                    customerDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot childSnapshot:dataSnapshot.getChildren()){
                                //System.out.println("yo"+childSnapshot);
                                if(childSnapshot.getKey().equals(current_user_in_queue)) {
                                    // System.out.println(childSnapshot.child(current_user_in_queue));
                                    System.out.println(customerDatabaseReference.child(childSnapshot.getKey().toString()).child("merchantID").toString());
                                    customerDatabaseReference.child(childSnapshot.getKey().toString()).child(("merchantID").toString()).removeValue();

                                    //customerDatabaseReference.child(childSnapshot.child(current_user_in_queue).toString()).child(("merchantID").toString()).removeValue();
                                    //queueDatabaseRef.child(user.getUid()).removeValue();
                                }else{
                                    System.out.println("Error");
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    Toast.makeText(MerchantViewQueuesActivityAfterLogin.this, "finish the service for onen customer", Toast.LENGTH_SHORT).show();

                    queueDatabaseRef.child(user.getUid()).child("queue").setValue(queueInformation.queue);
                    len--;
                    queue_length.setText(Integer.toString(len));
                }

                else{
                    Toast.makeText(MerchantViewQueuesActivityAfterLogin.this,"You have no customer right now :(", Toast.LENGTH_SHORT ).show();
                }

            }
        });


        btnDeleteQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queueDatabaseRef.child(user.getUid()).removeValue();
            }
        });

        btnCreateQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                queueDatabaseRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Toast.makeText(MerchantViewQueuesActivityAfterLogin.this,
                                    "You already have a queue", Toast.LENGTH_LONG).show();
                        }else{
                            Intent intent= new Intent(MerchantViewQueuesActivityAfterLogin.this, QueueActivity.class);
                            startActivity(intent);
                            //Toast.makeText(MerchantViewQueuesActivityAfterLogin.this,"Add queue now", Toast.LENGTH_SHORT).show();
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
