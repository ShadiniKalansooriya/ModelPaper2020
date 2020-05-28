package com.example.modelpaper2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.modelpaper2020.Database.DBHelper;

public class ProfileManagement extends AppCompatActivity {

    Button buttonupdate;
    EditText editTextusername, editTextPW, editTextDOB;
    RadioGroup radioGroupgender;
    RadioButton radioButton;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        buttonupdate = findViewById(R.id.buttonUpdateProf);
        editTextusername = findViewById(R.id.editTextUserName);
        editTextPW = findViewById(R.id.editTextPW);
        editTextDOB = findViewById(R.id.editTextDOB);
        radioGroupgender = findViewById(R.id.gender);
        db = new DBHelper(this);


        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get selected radio button from radioGroup
                int selectedID = radioGroupgender.getCheckedRadioButtonId();

                //find the radio button by returned id
                radioButton = (RadioButton) findViewById(selectedID);


                String name = editTextusername.getText().toString();
                String password = editTextPW.getText().toString();
                String Dob = editTextDOB.getText().toString();
                String gender = radioButton.getText().toString().trim();


                boolean i= db.addInfo(name,password,Dob,gender);

                if(i)
                {
                    Toast.makeText(ProfileManagement.this, "Successfully added", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(ProfileManagement.this, "Error in adding details", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}
