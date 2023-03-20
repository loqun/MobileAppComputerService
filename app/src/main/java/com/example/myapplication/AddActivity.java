package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText titleinput, detailsinput;
    Button addbtn;
    DBhelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        titleinput = findViewById(R.id.titleinput);
        detailsinput = findViewById(R.id.detailinput);
        addbtn = findViewById(R.id.buttonadd);
        db =new DBhelper(this);
        addbtn.setOnClickListener(view -> db.addBook(titleinput.getText().toString().trim(),detailsinput.getText().toString().trim()));
    }
}