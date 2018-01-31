package mydomain.firstapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button = (Button) findViewById(R.id.button);
        final CheckBox cbRemember = (CheckBox) findViewById(R.id.cbRemember);
        final EditText etUsername = (EditText) findViewById(R.id.Username);
        final EditText etPassword = (EditText) findViewById(R.id.Password);

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

            String nametxt = sharedPreferences.getString("usernamepf", "");
            String passwordtxt = sharedPreferences.getString("passwordpf", "");
        etUsername.setText(nametxt, TextView.BufferType.EDITABLE);
        etPassword.setText(passwordtxt, TextView.BufferType.EDITABLE);







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
                else if(cbRemember.isChecked()){
                    SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("usernamepf", etUsername.getText().toString());
                    editor.putString("passwordpf", etPassword.getText().toString());
                    editor.apply();
                    Toast.makeText(MainActivity.this,
                            "Login name: "+etUsername.getText().toString()+"\n"+
                                    "Password: "+etPassword.getText().toString(), Toast.LENGTH_SHORT). show();
                    Intent goToSearchActivity = new Intent(MainActivity.this, Login.class);
                    startActivity(goToSearchActivity);
                }
                else{

                 SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

                 SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("passwordpf", "");
                    editor.putString("usernamepf", "");
                    editor.apply();
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




