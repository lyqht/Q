package gcsenxmk.q;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MercFragment3 extends Fragment {
    private static final String TAG = "Tab3Fragment - Settings";
    private Spinner queueTypeSpinner, lateSpinner, prioritySpinner;
    private EditText editEstTimeText;
    private Button saveSettingButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.merc_fragment3,container,false);
        saveSettingButton= (Button) view.findViewById(R.id.saveSetting);
        editEstTimeText = (EditText) view.findViewById(R.id.editEstTimeText);

        saveSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "New Setting is saved",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}