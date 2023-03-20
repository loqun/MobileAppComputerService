package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText titleinput, detailsinput;
    Button updatebtn, delbtn;
    String id, title,details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        titleinput = findViewById(R.id.titleinput2);
        detailsinput = findViewById(R.id.detailinput2);
        updatebtn = findViewById(R.id.buttonupdate2);
        delbtn = findViewById(R.id.buttondelete);

        getAndSetIntentData();

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBhelper db = new DBhelper(UpdateActivity.this);
                title = titleinput.getText().toString().trim();
                details = detailsinput.getText().toString().trim();

                db.updateData(id,title,details);

            }


        });
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmDialog();

            }
        });


        ActionBar ab = getSupportActionBar();
        ab.setTitle("Feed Details");

    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")&& getIntent().hasExtra("title")&& getIntent().hasExtra("details")){

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            details = getIntent().getStringExtra("details");

            titleinput.setText(title);
            detailsinput.setText(details);


        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete"+title+"?");
        builder.setMessage("Are you sure you want to delete "+ title +"?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBhelper db = new DBhelper(UpdateActivity.this);
                db.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }


}