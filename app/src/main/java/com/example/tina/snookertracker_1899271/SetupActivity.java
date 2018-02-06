package com.example.tina.snookertracker_1899271;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
    }

    public void onDoneNames(View view) {
        String name1 = ((EditText)findViewById(R.id.userName1)).getText().toString();
        String name2 = ((EditText)findViewById(R.id.userName2)).getText().toString();
        String name3 = ((EditText)findViewById(R.id.userName3)).getText().toString();
        String name4 = ((EditText)findViewById(R.id.userName4)).getText().toString();
        //System.out.println(name1 + "  " + name2);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.NAME1, name1);
        intent.putExtra(MainActivity.NAME2, name2);
        intent.putExtra(MainActivity.NAME3, name3);
        intent.putExtra(MainActivity.NAME4, name4);
        startActivity(intent);
    }
}
