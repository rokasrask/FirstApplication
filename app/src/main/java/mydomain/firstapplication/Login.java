package mydomain.firstapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;

/**
 * Created by moksleivis on 2018-01-19.
 */

public class Login extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.)*/
        final DBcats dbHandler = new DBcats(this, null, null, 1);
        final String catstuff = "";
        final Button buttonback2 = (Button) findViewById(R.id.button4);
        final Button buttonadd = (Button) findViewById(R.id.buttonadd);
        final Button buttonDisplay = (Button) findViewById(R.id.displayButton);
        final EditText etId = (EditText) findViewById(R.id.etId);
        final EditText etName = (EditText)findViewById(R.id.etName);
        final EditText etFacts = (EditText)findViewById(R.id.etFacts);
        final EditText etWords = (EditText)findViewById(R.id.etWords);
        final CheckBox cb1 = (CheckBox)findViewById(R.id.cb1);
        final CheckBox cb2 = (CheckBox)findViewById(R.id.cb2);
        final CheckBox cb3 = (CheckBox)findViewById(R.id.cb3);
        final RadioGroup radiogroup = (RadioGroup)findViewById(R.id.radiogroup);

        buttonback2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent backIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(backIntent, 0);
            }
        });





        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedRb = radiogroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedRb);

                Toast.makeText(Login.this,
                        "Id: " + etId.getText().toString() + "\n" +
                                "Name: " + etName.getText().toString() + "\n" +
                                "Facts: " + etFacts.getText().toString() + "\n" +
                                "Words: " + etWords.getText().toString() + "\n" +
                                "Interesting :" + catstuff.toString() + "\n" +
                                radioButton.getText(), Toast.LENGTH_SHORT).show();

                Facts facts = new Facts(etName.getText().toString(), etWords.getText().toString(), etFacts.getText().toString(),  catstuff.toString(), (String) radioButton.getText());
                dbHandler.addCatfact(facts);
            }
        });

        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }

    public void isChecked(View view, CheckBox cb1, CheckBox cb2, CheckBox cb3, String catstuff) {
        if (cb1.isChecked()) {
            catstuff +="Interesting, ";
        }
        if (cb2.isChecked()) {
            catstuff += "Very, ";
        }
        if (cb3.isChecked()) {
            catstuff += "Yes. ";
        }

    }



}

