package gcsenxmk.q.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import gcsenxmk.q.R;
import gcsenxmk.q.database.QueueInformation;
import gcsenxmk.q.login.MerchantViewQueuesActivityAfterLogin;

public class MercMainOverview extends Fragment {
    private static final String TAG = "Tab2Fragment";

    private RecyclerView recyclerView;
    private ArrayList<String> mNames;
    private ArrayList<String> mImageUrls;


    private Button btnNext;


    private FirebaseAuth firebaseAuth;
    private TextView queue_length;
    private TextView aveWaitingTime;



    private FirebaseUser user;
    private TextView c1,c2,c3,c4,c5,c6,c7,c8, c9,c10;
    private ImageView c1_pic,c2_pic,c3_pic,c4_pic,c5_pic,c6_pic,c7_pic,c8_pic, c9_pic,c10_pic;
    public int len;
    QueueInformation queueInformation;

    private DatabaseReference queueDatabaseRef,customerDatabaseReference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNames = new ArrayList<>();
        mImageUrls = new ArrayList<>();
        Log.d(TAG, "onCreate: Started.");








    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.merc_main_overview, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        queueDatabaseRef = FirebaseDatabase.getInstance().getReference("Queue");
        customerDatabaseReference=FirebaseDatabase.getInstance().getReference("Users");
        //callFirebase();


        //recyclerView = view.findViewById(R.id.merc_recyclerview);
        //MercRecyclerViewAdapter adapter = new MercRecyclerViewAdapter(mNames, mImageUrls, getContext());
        //recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        queue_length = (TextView) view.findViewById(R.id.queue_length);
        aveWaitingTime = view.findViewById(R.id.AveWaitingTime);
        btnNext = (Button) view.findViewById(R.id.btnNext);
        //btnDeleteQueue = (Button) view.findViewById(R.id.btnDeleteQueue);
        //btnCreateQueue= (Button) view.findViewById(R.id.btnCreateNewQueue);
        queue_length.setText("test");

        queueDatabaseRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("avewaiting").getValue() != null) {
                    String time = dataSnapshot.child("avewaiting").getValue().toString();
                    aveWaitingTime.setText(time);
                }
                queueInformation = dataSnapshot.getValue(QueueInformation.class);
                len = queueInformation.queue.size();
                queue_length.setText(Integer.toString(len));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        c1= (TextView) view.findViewById(R.id.c1);
        c2= (TextView) view.findViewById(R.id.c2);
        c3= (TextView) view.findViewById(R.id.c3);
        c4= (TextView) view.findViewById(R.id.c4);
        c5= (TextView) view.findViewById(R.id.c5);
        c6= (TextView) view.findViewById(R.id.c6);
        c7= (TextView) view.findViewById(R.id.c7);
        c8= (TextView) view.findViewById(R.id.c8);
        c9= (TextView) view.findViewById(R.id.c9);
        c10= (TextView) view.findViewById(R.id.c10);

        //callFirebase();

        //adapter.notifyDataSetChanged();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        queueDatabaseRef = FirebaseDatabase.getInstance().getReference("Queue");
        customerDatabaseReference=FirebaseDatabase.getInstance().getReference("Users");



        queueDatabaseRef.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    queueInformation = dataSnapshot.getValue(QueueInformation.class);
                    len = queueInformation.queue.size();
                    queue_length.setText(Integer.toString(len));
                    System.out.println(len);

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
                                    cust_name1=dataSnapshot.child("name").getValue().toString();
                                    cc1 = cust_name1;
                                    c1.setText(cust_name1);
                                    //final String[] cust = new String[(cust_name1)];
                                }
                                else{
                                    c1.setText("No name entered");
                                    cc1 = "-1-";
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
                                    cust_name2=dataSnapshot.child("name").getValue().toString();
                                    c2.setText(cust_name2);
                                    cc2 = cust_name2;

                                    System.out.println(cust_name2);

                                    //final String[] cust = new String[Integer.parseInt(cust_name2)];
                                }
                                else{
                                    c2.setText("No name entered");
                                    cc2 = "-2-";
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
                                    cust_name3=dataSnapshot.child("name").getValue().toString();
                                    c3.setText(cust_name3);
                                    cc3 = cust_name3;

                                    System.out.println("myname"+cust_name3);

                                }
                                else{
                                    c3.setText("No name entered");
                                    cc3 = "-3-";
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
                                    cust_name4=dataSnapshot.child("name").getValue().toString();
                                    c4.setText(cust_name4);
                                    cc4 = cust_name4;

                                    System.out.println("myname"+cust_name4);
                                }
                                else{
                                    c4.setText("No name entered");
                                    cc4 = "-4-";
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
                                    cust_name5=dataSnapshot.child("name").getValue().toString();
                                    c5.setText(cust_name5);
                                    cc5 = cust_name5;

                                    System.out.println("myname"+cust_name5);
                                }
                                else{
                                    c5.setText("No name entered");
                                    cc5 = "-5-";
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
                                    cust_name6=dataSnapshot.child("name").getValue().toString();
                                    c6.setText(cust_name6);
                                    cc6 = cust_name6;

                                    System.out.println("myname"+cust_name6);
                                }
                                else{
                                    c6.setText("No name entered");
                                    cc6 = "-6-";
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
                                    cust_name7=dataSnapshot.child("name").getValue().toString();
                                    c7.setText(cust_name7);
                                    cc7 = cust_name7;

                                    System.out.println("myname"+cust_name7);
                                }
                                else{
                                    c7.setText("No name entered");
                                    cc7 = "-7-";
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
                                    cust_name8=dataSnapshot.child("name").getValue().toString();
                                    c8.setText(cust_name8);
                                    cc8 = cust_name8;

                                    System.out.println("myname"+cust_name8);
                                }
                                else{
                                    c8.setText("No name entered");
                                    cc8 = "-8-";
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
                                    cust_name9=dataSnapshot.child("name").getValue().toString();
                                    c9.setText(cust_name9);
                                    cc9 = cust_name9;

                                    System.out.println("myname"+cust_name9);
                                }
                                else{
                                    c9.setText("No name entered");
                                    cc9 = "-9-";
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
                                    cust_name10=dataSnapshot.child("name").getValue().toString();
                                    c10.setText(cust_name10);
                                    cc10 = cust_name10;

                                    System.out.println("myname"+cust_name10);
                                }
                                else{
                                    c10.setText("No name entered");
                                    cc10 = "-10-";
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





        Button operateQueue;
        operateQueue = view.findViewById(R.id.operateQueue);
        operateQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),Merc_QueueDisplay.class);
                startActivity(intent);
            }
        });

        //initImageBitmaps();


        //adapter.notifyDataSetChanged();


        return view;
    }
    String cust_name1, cust_name2, cust_name3, cust_name4, cust_name5, cust_name6, cust_name7, cust_name8, cust_name9, cust_name10;
    String cc1,cc2,cc3,cc4,cc5,cc6,cc7,cc8,cc9,cc10;
    final static ArrayList<String> cus = new ArrayList<>(20);
   private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://images.pexels.com/photos/8500/food-dinner-pasta-spaghetti-8500.jpg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        mNames.add("Indian Food");

        mImageUrls.add("https://images.pexels.com/photos/132694/pexels-photo-132694.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        mNames.add("");

        mImageUrls.add("https://images.pexels.com/photos/461198/pexels-photo-461198.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
        mNames.add("");

        mImageUrls.add("https://images.pexels.com/photos/1268558/pexels-photo-1268558.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
        mNames.add(cc4);

        mImageUrls.add("https://images.pexels.com/photos/376464/pexels-photo-376464.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
        mNames.add(cust_name5);

        mImageUrls.add("https://images.pexels.com/photos/5938/food-salad-healthy-lunch.jpg?auto=compress&cs=tinysrgb&h=750&w=1260");
        mNames.add(cust_name6);

        mImageUrls.add("https://images.pexels.com/photos/958545/pexels-photo-958545.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
        mNames.add(cc7);





    }



/*
    private void callFirebase(){

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

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
                    System.out.println(len);


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

        /*

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
                    Toast.makeText(getContext(), "finish the service for one customer", Toast.LENGTH_SHORT).show();

                    queueDatabaseRef.child(user.getUid()).child("queue").setValue(queueInformation.queue);
                    len--;
                    queue_length.setText(Integer.toString(len));
                }

                else{
                    Toast.makeText(getContext(),"You have no customer right now :(", Toast.LENGTH_SHORT ).show();
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

                                c5.setText("-5-");
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

                                c6.setText("-6-");
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

                                c7.setText("-7-");
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

                                c8.setText("-8-");
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

                                c9.setText("-9-");
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

                                c10.setText("-10-");
                            }






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


    }*/

}