package gcsenxmk.q.merchant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import gcsenxmk.q.login.MerchantViewQueuesActivityAfterLogin;

public class Merc_QueueDisplay extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView queuedisplayrefresh;
    private FirebaseUser user;
    private TextView c1,c2,c3,c4;
    public int len;
    QueueInformation queueInformation;

    private DatabaseReference queueDatabaseRef,customerDatabaseReference, merchantDatabaseReference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //Hides Title bar
        getSupportActionBar().hide();

        setContentView(R.layout.merc_queue_display);

        Button btnNext;
        btnNext = findViewById(R.id.nextCustomer);



        queuedisplayrefresh = findViewById(R.id.queuedisplayrefresh);
        queuedisplayrefresh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent (Merc_QueueDisplay.this, Merc_QueueDisplay.class);
                finish();
                overridePendingTransition( 0, 0);
                startActivity(getIntent());
                overridePendingTransition( 0, 0);
                return false;
            }
        });




        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        TextView queue_length = (TextView) findViewById(R.id.queue_length);
        c1= (TextView) findViewById(R.id.cust1);
        c2= (TextView) findViewById(R.id.cust2);
        c3= (TextView) findViewById(R.id.cust3);
        c4= (TextView) findViewById(R.id.cust4);


        queueDatabaseRef = FirebaseDatabase.getInstance().getReference("Queue");
        customerDatabaseReference=FirebaseDatabase.getInstance().getReference("Users");
        merchantDatabaseReference=FirebaseDatabase.getInstance().getReference("Merchants");

        queueDatabaseRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    queueInformation = dataSnapshot.getValue(QueueInformation.class);
                    len = queueInformation.queue.size();
                    queue_length.setText(Integer.toString(len));

                    String customer1="";
                    String customer2="";
                    String customer3="";
                    String customer4="";

                    if(queueInformation.queue.size()>0){
                        customer1= queueInformation.queue.get(0);


                        customerDatabaseReference.child(customer1).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.child("name").exists()){
                                    String cust_name=dataSnapshot.child("name").getValue().toString();
                                    c1.setText(cust_name);
                                    System.out.println("myname"+cust_name);
                                }
                                else{
                                    c1.setText("No name entered");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    if(queueInformation.queue.size()>1){
                        customer2= queueInformation.queue.get(1);


                        customerDatabaseReference.child(customer2).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.child("name").exists()){
                                    String cust_name2=dataSnapshot.child("name").getValue().toString();
                                    c2.setText(cust_name2);
                                    System.out.println("myname"+cust_name2);
                                }
                                else{
                                    c2.setText("No name entered");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    if(queueInformation.queue.size()>2){
                        customer3= queueInformation.queue.get(2);


                        customerDatabaseReference.child(customer3).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.child("name").exists()){
                                    String cust_name3=dataSnapshot.child("name").getValue().toString();
                                    c3.setText(cust_name3);
                                    System.out.println("myname"+cust_name3);
                                }
                                else{
                                    c3.setText("No name entered");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    if(queueInformation.queue.size()>3){
                        customer4= queueInformation.queue.get(3);


                        customerDatabaseReference.child(customer4).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.child("name").exists()){
                                    String cust_name4=dataSnapshot.child("name").getValue().toString();
                                    c4.setText(cust_name4);
                                    System.out.println("myname"+cust_name4);
                                }
                                else{
                                    c4.setText("No name entered");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    //System.out.println(c1);
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

                                if(childSnapshot.getKey().equals(current_user_in_queue)) {

                                    System.out.println(customerDatabaseReference.child(childSnapshot.getKey().toString()).child("merchantID").toString());
                                    customerDatabaseReference.child(childSnapshot.getKey().toString()).child(("merchantID").toString()).removeValue();



                                }else{
                                    System.out.println("Error");
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    Toast.makeText(Merc_QueueDisplay.this, "finish the service for onen customer", Toast.LENGTH_SHORT).show();

                    queueDatabaseRef.child(user.getUid()).child("queue").setValue(queueInformation.queue);
                    len--;
                    queue_length.setText(Integer.toString(len));
                }

                else{
                    Toast.makeText(Merc_QueueDisplay.this,"You have no customer right now :(", Toast.LENGTH_SHORT ).show();
                }

                queueDatabaseRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            queueInformation = dataSnapshot.getValue(QueueInformation.class);
                            len = queueInformation.queue.size();
                            queue_length.setText(Integer.toString(len));

                            String customer1="";
                            String customer2="";
                            String customer3="";
                            String customer4="";

                            if(queueInformation.queue.size()>0){
                                customer1= queueInformation.queue.get(0);


                                customerDatabaseReference.child(customer1).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.child("name").exists()){
                                            String cust_name=dataSnapshot.child("name").getValue().toString();
                                            c1.setText(cust_name);
                                            System.out.println("myname"+cust_name);
                                        }
                                        else{
                                            c1.setText("No name entered");
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }
                            else{
                                c1.setText("-1-");
                            }

                            if(queueInformation.queue.size()>1){
                                customer2= queueInformation.queue.get(1);


                                customerDatabaseReference.child(customer2).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.child("name").exists()){
                                            String cust_name2=dataSnapshot.child("name").getValue().toString();
                                            c2.setText(cust_name2);
                                            System.out.println("myname"+cust_name2);
                                        }
                                        else{
                                            c2.setText("No name entered");
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }
                            else{
                                c2.setText("-2-");
                            }

                            if(queueInformation.queue.size()>2){
                                customer3= queueInformation.queue.get(2);


                                customerDatabaseReference.child(customer3).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.child("name").exists()){
                                            String cust_name3=dataSnapshot.child("name").getValue().toString();
                                            c3.setText(cust_name3);
                                            System.out.println("myname"+cust_name3);
                                        }
                                        else{
                                            c3.setText("No name entered");
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }

                            else{
                                c3.setText("-3-");
                            }

                            if(queueInformation.queue.size()>3){
                                customer4= queueInformation.queue.get(3);


                                customerDatabaseReference.child(customer4).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.child("name").exists()){
                                            String cust_name4=dataSnapshot.child("name").getValue().toString();
                                            c4.setText(cust_name4);
                                            System.out.println("myname"+cust_name4);
                                        }
                                        else{
                                            c4.setText("No name entered");
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }

                            else{

                                c4.setText("-4-");
                            }

                            //System.out.println(c1);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

            }


        });

        /*
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
                            Toast.makeText(Merc_QueueDisplay.this,
                                    "You already have a queue", Toast.LENGTH_LONG).show();
                        }else{
                            Intent intent= new Intent(Merc_QueueDisplay.this, QueueActivity.class);
                            startActivity(intent);
                            //Toast.makeText(MerchantViewQueuesActivityAfterLogin.this,"Add queue now", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });*/




    }
}
