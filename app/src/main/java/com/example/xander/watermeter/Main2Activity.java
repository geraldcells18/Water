package com.example.xander.watermeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    int value = 0;
    long back_pressed;
    private View.OnClickListener refresh_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ProgressBar pb = (ProgressBar) findViewById(R.id.progressBarMain);
            TextView pb_text = (TextView) findViewById(R.id.progressBarText);
            if (value == 1000) {
                value = 0;
            } else {
                value += 50;
            }
            pb.setProgress(value);
            pb_text.setText(String.valueOf(value));
        }
    };
    private View.OnClickListener logout_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cells cells = new Cells();
            cells.changeActivity(Main2Activity.this, MainActivity.class, false);
        }
    };

    public void getData() {

    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 1000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(Main2Activity.this, "Press once again to exit", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageButton refresh = (ImageButton) findViewById(R.id.refresh);
        refresh.setOnClickListener(refresh_click);

        RelativeLayout logout = (RelativeLayout) findViewById(R.id.logout_menu);
        logout.setOnClickListener(logout_listener);
    }
}
