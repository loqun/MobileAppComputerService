package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CRUDUser extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addbtn;
    DBhelper db;
    ArrayList<String> book_id, book_title, book_details;
    CustomAdapter customAdapter;
    ImageView empty_view;
    TextView no_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cruduser);
        getSupportActionBar().setTitle("Feed");

            recyclerView = findViewById(R.id.recycleview);
            addbtn = findViewById(R.id.addbuttton);
            empty_view = findViewById(R.id.emptyView);
            no_data = findViewById(R.id.emptyText);
            no_data = findViewById(R.id.emptyText);
//            addbtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(CRUDUser.this,AddActivity.class);
//                    startActivity(intent);
//                }
//            });

            db =new DBhelper(CRUDUser.this);
            book_id = new ArrayList<>();
            book_title =new ArrayList<>();
            book_details = new ArrayList<>();

            //display in list
            storeDataInArrays();
            customAdapter = new CustomAdapter(CRUDUser.this,this,book_id,book_title,book_details);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager( new LinearLayoutManager(CRUDUser.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = db.readAllData();
        if(cursor.getCount()==0){
            empty_view.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_details.add(cursor.getString(2));
            }
            empty_view.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.my_menu,menu);
//
//        return super.onCreateOptionsMenu(menu);
//
//    }

}