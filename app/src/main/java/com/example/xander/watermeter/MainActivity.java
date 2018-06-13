package com.example.xander.watermeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    long BackPress_Iden;
    Cells cells = new Cells();

    private View.OnClickListener button_listener = new View.OnClickListener() {
        public void onClick(View v) {

            EditText textbox1 = (EditText) findViewById(R.id.textBox1);
            EditText textbox2 = (EditText) findViewById(R.id.textBox2);

            String username = textbox1.getText().toString();
            String password = textbox2.getText().toString();
            String mess_title = "Mobile Water Meter";

            if (username.isEmpty() && password.isEmpty()) {
                cells.MessageBox("Kindly input username and password first.", mess_title, MainActivity.this);
            } else if (username.isEmpty()) {
                cells.MessageBox("Kindly enter your username.", mess_title, MainActivity.this);
            } else if (password.isEmpty()) {
                cells.MessageBox("Kindly enter your password.", mess_title, MainActivity.this);
            } else if (username.equals("admin") && password.equals("admin")) {
                ChangeActivity();
            } else {
                cells.MessageBox("Invalid username or password.", mess_title, MainActivity.this);
            }
        }
    };

    public void ChangeActivity() {
        cells.changeActivity(this, Main2Activity.class, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(button_listener);
        ;
    }

    @Override
    public void onBackPressed() {
        if (BackPress_Iden + 1000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(MainActivity.this, "Press once again to exit", Toast.LENGTH_SHORT).show();
        }
        BackPress_Iden = System.currentTimeMillis();
    }
}
