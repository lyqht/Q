package gcsenxmk.q.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    SearchView mSearch;
    EditText mysearchView;
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
        mSearch =view.findViewById(R.id.cme_editTxt_SearchBar);
        mSearch.setIconified(false);

        SearchQueryButton = view.findViewById(R.id.BtnSearchResult);
        SearchQueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RecyclerActivity.class));
            }
        });

        /*mysearchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
               mysearchView.post(new Runnable() {
                    @Override
                    public void run() {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(mysearchView, InputMethodManager.SHOW_IMPLICIT);
                    }
                });
            }
        });
        mysearchView.requestFocus();*/
        return view;
    }




}
