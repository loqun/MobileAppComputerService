package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button signin,register;
    DBhelper db;
    SharedPreferences pref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");

        username=findViewById(R.id.usernamelogin);
        password=findViewById(R.id.passwordlogin);
        signin= findViewById(R.id.loginbtn);
        register=findViewById(R.id.createaccbtn);
        db =new DBhelper(this);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();






        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user =username.getText().toString();
                String pass =password.getText().toString();
                Long userId = db.getUserId(user,pass);


                if(user.equals("admins") && pass.equals("1234")){




                    editor.putString("username",user);
                    editor.putString("password",pass);
                    editor.commit();
                    Toast.makeText(LoginActivity.this, "welcome admin", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), adminActivity.class);
                    startActivity(intent);
                }
                else if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this, "All field required", Toast.LENGTH_SHORT).show();

                }else{
                    Boolean checkuserpass = db.checkuserpass(user,pass);
                    if(checkuserpass==true){

                        createSession(user,pass,userId);
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }




        });
    }
    public void createSession(String name, String password, Long userId){
        SharedPreferences.Editor editor = pref.edit();
        // Storing name in pref
        editor.putString("username", name);

        // Storing password in pref
        editor.putString("password", password);

        //Storing user id in pref
        editor.putLong("id", userId);

        // commit changes
        editor.commit();
    }

}