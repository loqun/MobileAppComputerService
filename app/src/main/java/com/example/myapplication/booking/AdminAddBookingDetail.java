package com.example.myapplication.booking;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DBhelper;
import com.example.myapplication.R;
import com.example.myapplication.recyclerview.Booking;


public class AdminAddBookingDetail extends AppCompatActivity {

    EditText et_bookingName, it_notes,  it_bookingContactPhoneNumber,it_bookingContactEmail;
    RadioGroup rg_bookingCategory;
    RadioButton radioButtonCategory,radioButtonPaymentStatus;
    Button btn_saveChanges;
    int id;
    String user;
    String username;
    int bookingid;
    DBhelper db;
    SharedPreferences prf;
    String process="insert";

    Booking currentBooking;

    Intent extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbookingdetail);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Booking Details");
        ab.setDisplayHomeAsUpEnabled(true);

        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        user = prf.getString("username",null);
        et_bookingName = findViewById(R.id.et_bookingName);
        it_notes = findViewById(R.id.it_notes);
        it_bookingContactPhoneNumber = findViewById(R.id.it_bookingContactPhoneNumber);
        it_bookingContactEmail = findViewById(R.id.it_bookingContactEmail);
        btn_saveChanges = findViewById(R.id.btn_saveChangesGuest);
        rg_bookingCategory = findViewById(R.id.rg_bookingCategory);
        db =new DBhelper((Context) this);

//        if(savedInstanceState==null) {

        extra = getIntent();
        id = extra.getIntExtra("id",0);




//            Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();


        username = extra.getStringExtra("username");


        if(extra.hasExtra("edit")){

            process = "update";
            currentBooking = (Booking) getIntent().getSerializableExtra("edit");
            bookingid = currentBooking.getBookingid();

            et_bookingName.setText(currentBooking.getName());
            it_notes.setText(currentBooking.getNotes());
            it_bookingContactPhoneNumber.setText(currentBooking.getPhone());
            it_bookingContactEmail.setText(currentBooking.getEmail());

            switch(currentBooking.getServiceID()){
                case 0: rg_bookingCategory.check(rg_bookingCategory.getChildAt(0).getId()); break;
                case 1: rg_bookingCategory.check(rg_bookingCategory.getChildAt(1).getId()); break;
                case 2: rg_bookingCategory.check(rg_bookingCategory.getChildAt(2).getId()); break;
                case 3: rg_bookingCategory.check(rg_bookingCategory.getChildAt(3).getId()); break;
                case 4: rg_bookingCategory.check(rg_bookingCategory.getChildAt(4).getId()); break;
            }


        }else if(currentBooking==null){

            bookingid = 0;

        }

//        }else{

//            id=(int)savedInstanceState.getSerializable("id");
//            username=(String)savedInstanceState.getSerializable("username");

//        }


        btn_saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //for radio group

                String name, category, notes,  phone, email ;

                int radioIdCategory = rg_bookingCategory.getCheckedRadioButtonId();
                radioButtonCategory = findViewById(radioIdCategory);

                Integer serviceID ;
                int bookingcheck;


//                username = String.valueOf(textInputEditTextUsername.getText());
                name = String.valueOf(et_bookingName.getText());
                notes = String.valueOf(it_notes.getText());
                phone = String.valueOf(it_bookingContactPhoneNumber.getText());
                email = String.valueOf(it_bookingContactEmail.getText());
                category = String.valueOf(radioButtonCategory.getText());
                serviceID = Integer.valueOf(radioButtonCategory.getInputType());
                bookingcheck = 0;



                if(!name.equals("") && serviceID != null && !notes.equals("")  ) {

                    //Start ProgressBar first (Set visibility VISIBLE)
//                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[11];
                            field[0] = "name";
                            field[1] = "category";
                            field[2] = "notes";
                            field[3] = "payment";
                            field[4] = "payment_status";
                            field[5] = "phone";
                            field[6] = "email";
                            field[7] = "process";
                            field[8] = "username";
                            field[9] = "event_id";
                            field[10] = "booking_id";

                            //Creating array for data
                            String[] data = new String[11];
                            data[0] = name;
                            data[1] = category;
                            data[2] = notes;
                            data[3] = phone;
                            data[4] = email;
                            data[5] = process;
                            data[6] = username;
                            data[7] = String.valueOf(id);
                            data[8] = String.valueOf(bookingid);
                            // todo host
                            db.addNewBooking( serviceID,name, notes,user, phone,  category,bookingcheck);
                            Toast.makeText(getApplicationContext(), "Booking added",Toast.LENGTH_SHORT).show();

                        }
                    });
                    finish();
                    Intent intent = new Intent(AdminAddBookingDetail.this,adminBookingActivity.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(), "All fields required",Toast.LENGTH_SHORT).show();
                }

            }



        });
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), adminBookingActivity.class);
        startActivity(myIntent);
        return true;
    }
//
}