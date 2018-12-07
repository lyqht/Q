package gcsenxmk.q.merchant;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import gcsenxmk.q.R;

public class MercQueueCreation extends Fragment {
    private static final String TAG = "Tab1Fragment";
    private ImageView imageView;
    private Button btn_createQ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.merc_queue_creation,container,false);
        btn_createQ = (Button) view.findViewById(R.id.btn_createQ);
        imageView = (ImageView) view.findViewById(R.id.queuecreationimage);


        btn_createQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Fill up these information to create a Queue!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), QueueActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}