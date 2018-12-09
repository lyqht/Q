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
    private TextView c1,c2,c3,c4,c5,c6,c7,c8, c9,c10;
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

        c1= (TextView) findViewById(R.id.customer1);
        c2= (TextView) findViewById(R.id.customer2);
        c3= (TextView) findViewById(R.id.customer3);
        c4= (TextView) findViewById(R.id.customer4);
        c5= (TextView) findViewById(R.id.customer5);
        c6= (TextView) findViewById(R.id.customer6);
        c7= (TextView) findViewById(R.id.customer7);
        c8= (TextView) findViewById(R.id.customer8);
        c9= (TextView) findViewById(R.id.customer9);
        c10= (TextView) findViewById(R.id.customer10);
        System.out.println("11");


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
                    String customer5="";
                    String customer6="";
                    String customer7="";
                    String customer8="";
                    String customer9="";
                    String customer10="";


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

                    if(queueInformation.queue.size()>4){
                        customer5= queueInformation.queue.get(4);


                        customerDatabaseReference.child(customer5).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.child("name").exists()){
                                    String cust_name5=dataSnapshot.child("name").getValue().toString();
                                    c5.setText(cust_name5);
                                    System.out.println("myname"+cust_name5);
                                }
                                else{
                                    c5.setText("No name entered");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    if(queueInformation.queue.size()>5){
                        customer6= queueInformation.queue.get(5);


                        customerDatabaseReference.child(customer6).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.child("name").exists()){
                                    String cust_name6=dataSnapshot.child("name").getValue().toString();
                                    c6.setText(cust_name6);
                                    System.out.println("myname"+cust_name6);
                                }
                                else{
                                    c6.setText("No name entered");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    if(queueInformation.queue.size()>6){
                        customer7= queueInformation.queue.get(6);


                        customerDatabaseReference.child(customer7).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.child("name").exists()){
                                    String cust_name7=dataSnapshot.child("name").getValue().toString();
                                    c7.setText(cust_name7);
                                    System.out.println("myname"+cust_name7);
                                }
                                else{
                                    c7.setText("No name entered");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    if(queueInformation.queue.size()>7){
                        customer8= queueInformation.queue.get(7);


                        customerDatabaseReference.child(customer8).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.child("name").exists()){
                                    String cust_name8=dataSnapshot.child("name").getValue().toString();
                                    c8.setText(cust_name8);
                                    System.out.println("myname"+cust_name8);
                                }
                                else{
                                    c8.setText("No name entered");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    if(queueInformation.queue.size()>8){
                        customer9= queueInformation.queue.get(8);


                        customerDatabaseReference.child(customer9).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.child("name").exists()){
                                    String cust_name9=dataSnapshot.child("name").getValue().toString();
                                    c9.setText(cust_name9);
                                    System.out.println("myname"+cust_name9);
                                }
                                else{
                                    c9.setText("No name entered");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    if(queueInformation.queue.size()>9){
                        customer10= queueInformation.queue.get(9);


                        customerDatabaseReference.child(customer10).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.child("name").exists()){
                                    String cust_name10=dataSnapshot.child("name").getValue().toString();
                                    c10.setText(cust_name10);
                                    System.out.println("myname"+cust_name10);
                                }
                                else{
                                    c10.setText("No name entered");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }




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
                    Toast.makeText(MerchantViewQueuesActivityAfterLogin.this, "finish the service for onen customer", Toast.LENGTH_SHORT).show();

                    queueDatabaseRef.child(user.getUid()).child("queue").setValue(queueInformation.queue);
                    len--;
                    queue_length.setText(Integer.toString(len));
                }

                else{
                    Toast.makeText(MerchantViewQueuesActivityAfterLogin.this,"You have no customer right now :(", Toast.LENGTH_SHORT ).show();
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
                            String customer5="";
                            String customer6="";
                            String customer7="";
                            String customer8="";
                            String customer9="";
                            String customer10="";

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
                                c1.setText("NIL");
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
                                c2.setText("NIL");
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
                                c3.setText("NIL");
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

                                c4.setText("NIL");
                            }

                            if(queueInformation.queue.size()>4){
                                customer5= queueInformation.queue.get(4);


                                customerDatabaseReference.child(customer5).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.child("name").exists()){
                                            String cust_name5=dataSnapshot.child("name").getValue().toString();
                                            c4.setText(cust_name5);
                                            System.out.println("myname"+cust_name5);
                                        }
                                        else{
                                            c5.setText("No name entered");
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }

                            else{

                                c5.setText("NIL");
                            }

                            if(queueInformation.queue.size()>5){
                                customer6= queueInformation.queue.get(5);


                                customerDatabaseReference.child(customer6).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.child("name").exists()){
                                            String cust_name6=dataSnapshot.child("name").getValue().toString();
                                            c6.setText(cust_name6);
                                            System.out.println("myname"+cust_name6);
                                        }
                                        else{
                                            c6.setText("No name entered");
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }

                            else{

                                c6.setText("NIL");
                            }

                            if(queueInformation.queue.size()>6){
                                customer7= queueInformation.queue.get(6);


                                customerDatabaseReference.child(customer7).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.child("name").exists()){
                                            String cust_name7=dataSnapshot.child("name").getValue().toString();
                                            c4.setText(cust_name7);
                                            System.out.println("myname"+cust_name7);
                                        }
                                        else{
                                            c7.setText("No name entered");
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }

                            else{

                                c7.setText("NIL");
                            }

                            if(queueInformation.queue.size()>7){
                                customer8= queueInformation.queue.get(7);


                                customerDatabaseReference.child(customer8).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.child("name").exists()){
                                            String cust_name8=dataSnapshot.child("name").getValue().toString();
                                            c8.setText(cust_name8);
                                            System.out.println("myname"+cust_name8);
                                        }
                                        else{
                                            c8.setText("No name entered");
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }

                            else{

                                c8.setText("NIL");
                            }

                            if(queueInformation.queue.size()>8){
                                customer9= queueInformation.queue.get(8);


                                customerDatabaseReference.child(customer9).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.child("name").exists()){
                                            String cust_name9=dataSnapshot.child("name").getValue().toString();
                                            c9.setText(cust_name9);
                                            System.out.println("myname"+cust_name9);
                                        }
                                        else{
                                            c9.setText("No name entered");
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }

                            else{

                                c9.setText("NIL");
                            }

                            if(queueInformation.queue.size()>9){
                                customer10= queueInformation.queue.get(9);


                                customerDatabaseReference.child(customer10).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.child("name").exists()){
                                            String cust_name10=dataSnapshot.child("name").getValue().toString();
                                            c10.setText(cust_name10);
                                            System.out.println("myname"+cust_name10);
                                        }
                                        else{
                                            c10.setText("No name entered");
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }

                            else{

                                c10.setText("NIL");
                            }






                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

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
