package mydomain.firstapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by moksleivis on 2018-01-19.
 */

public class Login extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        final Button buttonback2 = (Button) findViewById(R.id.button4);
        buttonback2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent backIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(backIntent, 0);
            }
        });
    }
}
