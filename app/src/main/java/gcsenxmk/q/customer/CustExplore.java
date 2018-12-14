package gcsenxmk.q.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import gcsenxmk.q.R;

public class CustExplore extends Fragment {

    ImageButton imageButtonPhotography;
    ImageButton imageButtonFnB;
    ImageButton imageButtonMusic;
    ImageButton imageButtonIT;
    ImageButton imageButtonLimitedT;
    ImageButton imageButtonExtra;
    ImageButton imageButtonRetail;
    Button SearchQueryButton;

    private static final String TAG = "CustExplore";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cust_main_explore, container,false);
        imageButtonPhotography = view.findViewById(R.id.buttonPhotography);
        imageButtonFnB = view.findViewById(R.id.buttonFood_and_Beverages);
        imageButtonMusic = view.findViewById(R.id.buttonMusicConcert);
        imageButtonRetail = view.findViewById(R.id.buttonRetail_Outlets);
        imageButtonLimitedT = view.findViewById(R.id.buttonLimitedT);
        imageButtonExtra = view.findViewById(R.id.buttonExtraCategory);
        SearchQueryButton = view.findViewById(R.id.BtnSearchResult);

        SearchQueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Cust_Search_Merchant.class));
            }


        });

        return view;
    }



}
