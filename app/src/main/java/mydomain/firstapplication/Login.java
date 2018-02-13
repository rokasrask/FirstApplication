package mydomain.firstapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import android.database.Cursor;

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




                String catstuff = "";
                if (cb1.isChecked()) {
                    catstuff +="Interesting, ";
                }
                if (cb2.isChecked()) {
                    catstuff += "Very, ";
                }
                if (cb3.isChecked()) {
                    catstuff += "Yes. ";
                }
                int selectedRb = radiogroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedRb);

                if(!Validation.isValidId(etId.getText().toString())) {
                    etId.setError("Enter a valid ID!");
                }
                else if(!Validation.isValidCredentials(etName.getText().toString())) {
                    etName.setError("Enter a valid name");
                }

               else {
                    Toast.makeText(Login.this,
                                    "Id: " + etId.getText().toString() + "\n" +
                                    "Name: " + etName.getText().toString() + "\n" +
                                    "Facts: " + etFacts.getText().toString() + "\n" +
                                    "Words: " + etWords.getText().toString() + "\n" +
                                    "Interesting :" + catstuff  + "\n" +
                                    radioButton.getText(), Toast.LENGTH_SHORT).show();

                    Facts facts = new Facts(etName.getText().toString(), etWords.getText().toString(), etFacts.getText().toString(),catstuff , radioButton.getText().toString());
                    dbHandler.addCatfact(facts);
                }
            }






        });

        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dbHandler.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "No data available");
                    return;
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("id: " + res.getString(0) + "\n");
                        buffer.append("name: " + res.getString(1) + "\n");
                        buffer.append("words: " + res.getString(2) + "\n");
                        buffer.append("fact: " + res.getString(3) + "\n");
                        buffer.append("interesting: " + res.getString(4) + "\n");
                        buffer.append("type: " + res.getString(5) + "\n");
                        buffer.append("cats: " + res.getString(6) + "\n");
                    }

                    showMessage("Data", buffer.toString());


                }
            }
        });

    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }





}

