package com.example.myapplication.feedback;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class ViewCourses extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<CourseModal> courseModalArrayList;
    private DBHandler dbHandler;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;
    String username;
    SharedPreferences prf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);
        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        getSupportActionBar().setTitle("Feedback History");

        // initializing our all variables.
        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewCourses.this);

        // getting our course array
        // list from db handler class.
        username =prf.getString("username",null);
        courseModalArrayList = dbHandler.readCoursesUser(username);

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new CourseRVAdapter(courseModalArrayList, ViewCourses.this);
        coursesRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewCourses.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);
    }
}
