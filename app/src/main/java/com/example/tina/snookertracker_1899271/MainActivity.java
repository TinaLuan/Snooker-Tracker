package com.example.tina.snookertracker_1899271;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String NAME1 = "name1";
    public static final String NAME2 = "name2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String name1 = intent.getStringExtra(NAME1);
        //TextView nameTextView1 = (TextView)findViewById(R.id.nameDisplay1);
        ((TextView)findViewById(R.id.nameDisplay1)).setText(name1);
    }
}
