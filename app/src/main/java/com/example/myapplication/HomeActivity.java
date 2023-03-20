package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.booking.BookingActivity;
import com.example.myapplication.feedback.FeedbackMain;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import im.crisp.client.ChatActivity;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences prf;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView=findViewById(R.id.adminNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.Home);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.Home:
                        return true;

                    case R.id.Profile:
                        startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        TextView result = (TextView)findViewById(R.id.textuser);
        CardView feedUpdate = (CardView) findViewById(R.id.feedUpdate);
        CardView feedbackbtn = (CardView) findViewById(R.id.feedbackbtn);
        CardView booking = (CardView) findViewById(R.id.bookingbtn);
        CardView livebtn = (CardView) findViewById(R.id.livebtn);

        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        result.setText("Hello, "+prf.getString("username",null));

        feedUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,CRUDUser.class);
                startActivity(intent);
            }
        });

        feedbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FeedbackMain.class);
                startActivity(intent);
            }
        });

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BookingActivity.class);
                startActivity(intent);
            }
        });

        livebtn.setOnClickListener(v -> {
                Intent crispIntent = new Intent(HomeActivity.this, ChatActivity.class);
                startActivity(crispIntent);
            });

    }


    }
