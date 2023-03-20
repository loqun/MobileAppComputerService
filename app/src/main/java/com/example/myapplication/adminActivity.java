package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.booking.adminBookingActivity;
import com.example.myapplication.feedback.AdminFeedbackViewCourses;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class adminActivity extends AppCompatActivity {
    SharedPreferences prf;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.adminNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.Home);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.Home:
                        return true;
                    case R.id.Profile:
                        startActivity(new Intent(getApplicationContext(),AdminProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        TextView result = (TextView)findViewById(R.id.textuser);
        CardView feedUpdate = (CardView) findViewById(R.id.feedUpdate);
        CardView feedbackbtn = (CardView) findViewById(R.id.feedbackbtn);
        CardView buttonbook = (CardView) findViewById(R.id.buttonbook);

        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        Intent intent = new Intent(adminActivity.this, MainActivity.class);
        result.setText("Hello, "+prf.getString("username",null));
        //booking function
        feedUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(adminActivity.this,CRUD.class);
                startActivity(intent);
            }
        });
        feedbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(adminActivity.this, AdminFeedbackViewCourses.class);
                startActivity(intent);
            }
        });
        buttonbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(adminActivity.this, adminBookingActivity.class);
                startActivity(intent);
            }
        });
    }



        
    }
