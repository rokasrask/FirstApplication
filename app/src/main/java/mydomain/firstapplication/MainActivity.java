package mydomain.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button = (Button) findViewById(R.id.button);
        final EditText etUsername = (EditText) findViewById(R.id.Username);
        final EditText etPassword = (EditText) findViewById(R.id.Password);



        button2.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view2) {
                    Intent registerIntent = new Intent(view2.getContext(), Beginning.class);
                    startActivityForResult(registerIntent, 0);
                }

            });


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!Validation.isValidCredentials(etUsername.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter a valid Username", Toast.LENGTH_SHORT).show();
                }
                else if (!Validation.isValidCredentials(etPassword.getText().toString())){
                    Toast.makeText(MainActivity.this, "Enter a valid Password", Toast.LENGTH_SHORT).show();
                }
                else{


                Toast.makeText(MainActivity.this,
                        "Login name: "+etUsername.getText().toString()+"\n"+
                                "Password: "+etPassword.getText().toString(), Toast.LENGTH_SHORT). show();
                Intent goToSearchActivity = new Intent(MainActivity.this, Login.class);
                startActivity(goToSearchActivity);
            }
        }
        });
    }
}




