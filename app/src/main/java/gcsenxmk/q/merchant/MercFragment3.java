package gcsenxmk.q.merchant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import gcsenxmk.q.R;

public class MercFragment3 extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "Tab3Fragment - Settings";
    private Spinner queueTypeSpinner, lateSpinner, prioritySpinner;
    private EditText editEstTimeText;
    private Button saveSettingButton, deleteAllQueueButton;
    private SharedPreferences mPreferences;
    private ImageButton helpPageButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.merc_fragment3,container,false);
        saveSettingButton= (Button) view.findViewById(R.id.saveSetting);
        deleteAllQueueButton = (Button) view.findViewById(R.id.deleteAllQueue);
        helpPageButton = (ImageButton) view.findViewById(R.id.helpPage);
        editEstTimeText = (EditText) view.findViewById(R.id.editEstTimeText);
        queueTypeSpinner = (Spinner) view.findViewById(R.id.spinQueueSys);
        ArrayAdapter<CharSequence> adapterQueue = ArrayAdapter.createFromResource(getContext(),
                R.array.queue_system, android.R.layout.simple_spinner_item);
        adapterQueue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        queueTypeSpinner.setAdapter(adapterQueue);
        queueTypeSpinner.setOnItemSelectedListener(this);

        lateSpinner = (Spinner) view.findViewById(R.id.spinLate);
        ArrayAdapter<CharSequence> adapterLate = ArrayAdapter.createFromResource(getContext(),
                R.array.late_policy, android.R.layout.simple_spinner_item);
        adapterLate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lateSpinner.setAdapter(adapterLate);
        lateSpinner.setOnItemSelectedListener(this);

        prioritySpinner = (Spinner) view.findViewById(R.id.spinPriority);
        ArrayAdapter<CharSequence> adapterPriority = ArrayAdapter.createFromResource(getContext(),
                R.array.priority_queue, android.R.layout.simple_spinner_item);
        adapterLate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(adapterPriority);
        prioritySpinner.setOnItemSelectedListener(this);

        saveSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO - Save the setting in Database
                //Intent intent = new Intent(getContext(), MercMainOverview.class);
                //startActivity(intent);
                Toast.makeText(getActivity(), "New Setting is saved",Toast.LENGTH_LONG).show();
            }
        });

        deleteAllQueueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //TODO - Confirmation pop-up Box
                Toast.makeText(getActivity(),"All Queues are deleted",Toast.LENGTH_LONG).show();
            }
        });

        helpPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(),Merc_HelpPage.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        //String text = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}