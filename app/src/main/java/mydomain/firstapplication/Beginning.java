package mydomain.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by moksleivis on 2018-01-15.
 */

public class Beginning extends AppCompatActivity {

            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.beginning);


        final EditText etUsername = (EditText) findViewById(R.id.regName);
        final EditText etMail = (EditText) findViewById(R.id.regMail);
        final EditText etPassword = (EditText) findViewById(R.id.regPassword);
        final EditText etRepeat = (EditText) findViewById(R.id.regRepeat);


        final Button buttonback = (Button) findViewById(R.id.buttonback);
        final Button buttonregister = (Button) findViewById(R.id.button3);
        buttonback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent backIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(backIntent, 0);
            }
        });

        buttonregister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String pass = etPassword.getText().toString();
                String rpass = etRepeat.getText().toString();
                if(!Validation.isValidCredentials(etUsername.getText().toString())) {
                    Toast.makeText(Beginning.this, "Enter a valid Username", Toast.LENGTH_SHORT).show();
                    etUsername.setError("Enter a valid Username!");
                }
                else if(!Validation.isValidMail(etMail.getText().toString())) {
                    Toast.makeText(Beginning.this, "Enter a valid Mail", Toast.LENGTH_SHORT).show();
                    etMail.setError("Enter a valid Mail!");
                }
                else if(!Validation.isValidCredentials(etPassword.getText().toString())) {
                    Toast.makeText(Beginning.this, "Enter a valid password", Toast.LENGTH_SHORT).show();
                    etPassword.setError("Enter a valid password!");
                }
                else if (!pass.equals(rpass)) {
                    Toast.makeText(Beginning.this, "Your passwords must be the same", Toast.LENGTH_SHORT).show();
                    etRepeat.setError("Your passwords must be the same!");
                }
                else {
                    Toast.makeText(Beginning.this,
                            "Username: "+etUsername.getText().toString()+"\n" +
                                    "Password :"+etPassword.getText().toString()+"\n"+
                                    "Mail :"+etMail.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent goToLogin = new Intent(Beginning.this, MainActivity.class);
                        startActivity(goToLogin);
                }

                    }
                });
                }
                }





