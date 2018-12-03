package gcsenxmk.q;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MercCreateQueue extends AppCompatActivity {

    EditText location;
    EditText queuetype;
    EditText latepolicy;
    EditText Qname;
    EditText priority;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merc_create_queue);
    }
}
