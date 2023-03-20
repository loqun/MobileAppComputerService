package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityAdminProfileBinding;

public class AdminProfileActivity extends AppCompatActivity {
    SharedPreferences prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        BottomNavigationView bottomNavigationView=findViewById(R.id.adminNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.Home);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(), adminActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                        case R.id.Profile:
                            return true;
                }
                return false;
            }
    });

        TextView result = (TextView)findViewById(R.id.textuser2);
        TextView result2 = (TextView)findViewById(R.id.textuser3);
        Button btnLogOut = (Button)findViewById(R.id.logoutuser);

        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        Intent intent = new Intent(AdminProfileActivity.this, MainActivity.class);
        result.setText("Name: "+prf.getString("username",null));
        result2.setText("ID: "+prf.getLong("id",0));

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();
                startActivity(intent);
            }
        });

    }
}