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

public class EditProfile extends AppCompatActivity {

    Button buttonEdit, buttonDelete, buttonSearch;
    EditText editTextUsername, editTextDOB, editTextPW;
    RadioGroup radioGroupgender;
    RadioButton radioButton;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        buttonEdit = findViewById(R.id.buttonEdit);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSearch = findViewById(R.id.buttonSearch);
        editTextUsername = findViewById(R.id.editTextUserName);
        editTextDOB = findViewById(R.id.editTextDOB);
        editTextPW = findViewById(R.id.editTextPW);

        radioGroupgender = findViewById(R.id.gender);
        db = new DBHelper(this);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.readAllInfo();
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get selected radio button from radioGroup
                int selectedID = radioGroupgender.getCheckedRadioButtonId();

                //find the radio button by returned id
                radioButton = (RadioButton) findViewById(selectedID);


                String name = editTextUsername.getText().toString();
                String password = editTextPW.getText().toString();
                String Dob = editTextDOB.getText().toString();
                String gender = radioButton.getText().toString().trim();

               boolean i = db.updateInfo(name,password,Dob,gender);
               if(i){
                   Toast.makeText(EditProfile.this,"Successfully Updated",Toast.LENGTH_SHORT).show();

               }
               else{
                   Toast.makeText(EditProfile.this,"Error in Updating",Toast.LENGTH_SHORT).show();

               }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextUsername.getText().toString();

               int i =  db.deleteInfo(name);

                if(i == -1)
                {
                    Toast.makeText(EditProfile.this,"Error",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(EditProfile.this,"Successfully Deleted",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
