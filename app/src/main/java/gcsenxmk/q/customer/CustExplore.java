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
    //SearchView mSearch;
    EditText mSearch;

    private static final String TAG = "CustExplore";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cust_main_explore, container,false);
        // Widget References
        imageButtonPhotography = view.findViewById(R.id.buttonPhotography);
        imageButtonFnB = view.findViewById(R.id.buttonFood_and_Beverages);
        imageButtonMusic = view.findViewById(R.id.buttonMusicConcert);
        //imageButtonIT = view.findViewById(R.id.imageButtonIT);
        imageButtonRetail = view.findViewById(R.id.buttonRetail_Outlets);
        imageButtonLimitedT = view.findViewById(R.id.buttonLimitedT);
        imageButtonExtra = view.findViewById(R.id.buttonExtraCategory);
        //mSearch =view.findViewById(R.id.cme_editTxt_SearchBar);
        SearchQueryButton = view.findViewById(R.id.BtnSearchResult);

        imageButtonFnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if (searchEnquiry.equals("")) {
                    startActivity(new Intent(getContext(), RecyclerActivity.class));
                    Log.d(TAG, "Empty input");
                }

                else {
                    startActivity(new Intent(getContext(), Cust_Search_Merchant.class));
                }*/
                startActivity(new Intent(getContext(), RecyclerActivity.class));
            }


        });
        SearchQueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String searchEnquiry = mSearch.getText().toString().trim();
                /*if (searchEnquiry.equals("")) {
                    startActivity(new Intent(getContext(), RecyclerActivity.class));
                    Log.d(TAG, "Empty input");
                }

                else {
                    startActivity(new Intent(getContext(), Cust_Search_Merchant.class));
                }*/

                startActivity(new Intent(getContext(), Cust_Search_Merchant.class));
            }


        });

        return view;
    }



}
