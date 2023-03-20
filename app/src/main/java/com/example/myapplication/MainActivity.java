package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {

    EditText username, password,repassword;
    ImageView pic;
    TextView register;
    Button signup, signin;
    DBhelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        username = findViewById(R.id.namesign);
        password = findViewById(R.id.passsign);
        repassword= findViewById(R.id.cpasssign);
        signup= findViewById(R.id.registerbtn);
        signin = findViewById(R.id.loginreg);
        db =new DBhelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(TextUtils.isEmpty(user)|| TextUtils.isEmpty(pass)||TextUtils.isEmpty(repass)){
                    Toast.makeText(MainActivity.this, "All field required", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(repass)){
                        Boolean checkuser = db.checkusername(user);
                        if(checkuser==false){

                            Boolean insert  =db.insertData(user, pass);
                                    if(insert==true){
                                        Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                        startActivity(intent);
                                    }else {
                                        Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                    }
                        }else {
                            Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        Toast.makeText(MainActivity.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }



}