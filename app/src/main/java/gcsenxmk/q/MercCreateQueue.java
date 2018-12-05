package gcsenxmk.q;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MercCreateQueue extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText location;
    private Spinner queueTypeSpinner, prioritySpinner;
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


        prioritySpinner = findViewById(R.id.spinPriority);
        ArrayAdapter<CharSequence> adapterPriority = ArrayAdapter.createFromResource(this,
                R.array.priority_queue, android.R.layout.simple_spinner_item);

        prioritySpinner.setAdapter(adapterPriority);
        prioritySpinner.setOnItemSelectedListener(this);

        btn_createQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MercCreateQueue.this, MercQueueCreated.class);

                //TODO link to firebase submission of data
                //TODO condition to check if queue creation successful
                Toast.makeText(MercCreateQueue.this, "Queue Created!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
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
