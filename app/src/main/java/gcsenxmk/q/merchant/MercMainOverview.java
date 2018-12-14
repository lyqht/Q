package gcsenxmk.q.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class MercMainOverview extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<String> mNames;
    private ArrayList<String> mImageUrls;

    private Button btnNext;

    private FirebaseAuth firebaseAuth;
    private TextView queue_length;
    private TextView aveWaitingTime;

    private Button refresh;

    private FirebaseUser user;
    private TextView c1,c2,c3,c4,c5,c6,c7,c8, c9,c10;
    private ImageView c1_pic,c2_pic,c3_pic,c4_pic,c5_pic,c6_pic,c7_pic,c8_pic, c9_pic,c10_pic;
    String cust_name1, cust_name2, cust_name3, cust_name4, cust_name5, cust_name6, cust_name7, cust_name8, cust_name9, cust_name10;
    String cc1,cc2,cc3,cc4,cc5,cc6,cc7,cc8,cc9,cc10;

    public int len;
    QueueInformation queueInformation;

    private DatabaseReference queueDatabaseRef,customerDatabaseReference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNames = new ArrayList<>();
        mImageUrls = new ArrayList<>();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.merc_main_overview, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        queueDatabaseRef = FirebaseDatabase.getInstance().getReference("Queue");
        customerDatabaseReference=FirebaseDatabase.getInstance().getReference("Users");
        c1= view.findViewById(R.id.c1);
        c2= view.findViewById(R.id.c2);
        c3= view.findViewById(R.id.c3);
        c4= view.findViewById(R.id.c4);
        c5= view.findViewById(R.id.c5);
        c6= view.findViewById(R.id.c6);
        c7= view.findViewById(R.id.c7);
        c8= view.findViewById(R.id.c8);
        c9= view.findViewById(R.id.c9);
        c10= view.findViewById(R.id.c10);

        refresh = view.findViewById(R.id.queuerefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Refreshed!", Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(MercMainOverview.this).attach(MercMainOverview.this).commit();

            }
        });

        queue_length = view.findViewById(R.id.queue_length);
        aveWaitingTime = view.findViewById(R.id.AveWaitingTime);
        btnNext = view.findViewById(R.id.btnNext);
        queue_length.setText("-");
        aveWaitingTime.setText("-");

        queueDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(user.getUid())) {
                    queueDatabaseRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String time = dataSnapshot.child("avewaiting").getValue().toString();
                            aveWaitingTime.setText(time);
                            queueInformation = dataSnapshot.getValue(QueueInformation.class);
                            len = queueInformation.queue.size();
                            queue_length.setText(Integer.toString(len));
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

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

        return view;
    }


}