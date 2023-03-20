package com.example.myapplication.booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DBhelper;
import com.example.myapplication.R;
import com.example.myapplication.adminActivity;
import com.example.myapplication.recyclerview.Booking;
import com.example.myapplication.recyclerview.adminbookingRecycleView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class adminBookingActivity extends AppCompatActivity {


    DBhelper db;

    LinearLayoutManager linearLayoutManager;


    public FloatingActionButton fbtn_createBooking;
    private adminbookingRecycleView BookingRecycleView;
    private ArrayList<Booking> BookingArrayList;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Booking List");
        ab.setDisplayHomeAsUpEnabled(true);

        BookingArrayList = new ArrayList<>();
        db =new DBhelper(this);
        BookingArrayList = db.readCourses();


        BookingRecycleView = new adminbookingRecycleView(BookingArrayList, adminBookingActivity.this);
        recyclerView = findViewById(R.id. recycler_view);

        linearLayoutManager = new LinearLayoutManager(adminBookingActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(BookingRecycleView);
        BookingRecycleView.notifyDataSetChanged();



        fbtn_createBooking =findViewById(R.id.fbtn_createBooking);

        fbtn_createBooking.setOnClickListener(new View.OnClickListener() {
            String name;
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(adminBookingActivity.this, AdminAddBookingDetail.class);

                startActivity(intent);



            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), adminActivity.class);
        startActivity(myIntent);
        return true;
    }
}
