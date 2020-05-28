package com.example.modelpaper2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonReg, buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonReg = findViewById(R.id.buttonRegister);
        buttonUpdate = findViewById(R.id.update);

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentreg = new Intent(MainActivity.this,ProfileManagement.class );
                startActivity(intentreg);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentupdate = new Intent(MainActivity.this,EditProfile.class );
                startActivity(intentupdate);
            }
        });
    }
}
