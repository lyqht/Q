package gcsenxmk.q.merchant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import gcsenxmk.q.R;

public class MercCreateQueue extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText location;
    private Spinner queueTypeSpinner, lateSpinner, prioritySpinner;
    private EditText editEstTimeText;
    private Button btn_createQueue;
    EditText Qname;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merc_create_queue);
        btn_createQueue = findViewById(R.id.btn_createQ);
        Qname = findViewById(R.id.enterQueueName);
        location = findViewById(R.id.enterLocation);
        editEstTimeText = findViewById(R.id.editEstTimeText);
        queueTypeSpinner = findViewById(R.id.spinQueueSys);
        ArrayAdapter<CharSequence> adapterQueue = ArrayAdapter.createFromResource(this,
                R.array.queue_system, android.R.layout.simple_spinner_item);
        adapterQueue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        queueTypeSpinner.setAdapter(adapterQueue);
        queueTypeSpinner.setOnItemSelectedListener(this);

        lateSpinner = findViewById(R.id.spinLate);
        ArrayAdapter<CharSequence> adapterLate = ArrayAdapter.createFromResource(this,
                R.array.late_policy, android.R.layout.simple_spinner_item);
        adapterLate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lateSpinner.setAdapter(adapterLate);
        lateSpinner.setOnItemSelectedListener(this);

        prioritySpinner = findViewById(R.id.spinPriority);
        ArrayAdapter<CharSequence> adapterPriority = ArrayAdapter.createFromResource(this,
                R.array.priority_queue, android.R.layout.simple_spinner_item);
        adapterLate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(adapterPriority);
        prioritySpinner.setOnItemSelectedListener(this);

        btn_createQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MercCreateQueue.this, MercQueueCreated.class);
                startActivity(intent);
            }
        });
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        //String text = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
