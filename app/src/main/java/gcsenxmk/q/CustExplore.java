package gcsenxmk.q;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class CustExplore extends Fragment {

    ImageButton imageButtonPhotography;
    ImageButton imageButtonFnB;
    ImageButton imageButtonMusic;
    ImageButton imageButtonIT;
    ImageButton imageButtonLimitedT;
    ImageButton imageButtonExtra;

    private static final String TAG = "CustExplore";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cust_main_explore, container,false);
        // Widget References
        imageButtonPhotography = view.findViewById(R.id.imageButtonPhotography);
        imageButtonFnB = view.findViewById(R.id.imageButtonFnB);
        imageButtonMusic = view.findViewById(R.id.imageButtonMusic);
        imageButtonIT = view.findViewById(R.id.imageButtonIT);
        imageButtonLimitedT = view.findViewById(R.id.imageButtonLimitedT);
        imageButtonExtra = view.findViewById(R.id.imageButtonExtra);

        return view;
    }

}
