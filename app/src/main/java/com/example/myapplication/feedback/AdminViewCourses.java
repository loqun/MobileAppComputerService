package com.example.myapplication.feedback;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class AdminViewCourses extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<CourseModal> courseModalArrayList;
    private DBHandler dbHandler;
    private AdminCourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_feedback);
        getSupportActionBar().setTitle("Feedback History");

        // initializing our all variables.
        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(AdminViewCourses.this);

        // getting our course array
        // list from db handler class.
        courseModalArrayList = dbHandler.readCourses();

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new AdminCourseRVAdapter(courseModalArrayList, AdminViewCourses.this);
        coursesRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminViewCourses.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);
    }
}
