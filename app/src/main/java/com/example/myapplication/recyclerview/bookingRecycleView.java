package com.example.myapplication.recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DBhelper;
import com.example.myapplication.R;
import com.example.myapplication.booking.BookingActivity;
import com.example.myapplication.booking.adminBookingActivity;

import java.util.ArrayList;

public class bookingRecycleView extends RecyclerView.Adapter<bookingRecycleView.ViewHolder> {

    // variable for our array list and context
    private ArrayList<Booking> BookingArrayList;
    private Context context;
    DBhelper db;
    String name;
    // constructor
    public bookingRecycleView(ArrayList<Booking> BookingArrayList, Context context) {
        this.BookingArrayList = BookingArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rbooking_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data 
        // to our views of recycler view item.
        Booking modal = BookingArrayList.get(position);
        holder.tv_name.setText(BookingArrayList.get(position).getuserName());
        holder.tv_bookingBategory.setText(BookingArrayList.get(position).getNotes());
        holder.tv_paymentStatus.setText(BookingArrayList.get(position).getBookingCheck());
        holder.description.setText(BookingArrayList.get(position).getEmail());



        holder.button_delete_booking.setOnClickListener(view -> {

            Toast.makeText(view.getContext(),String.valueOf(BookingArrayList.get(position).getBookingid()),Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            // Set a title for alert dialog
            builder.setTitle("Are you sure you want to delete this ?");

            // Ask the final question
            builder.setMessage("Deleted data cannot be undone.");

            // Set the alert dialog yes button click listener
            builder.setPositiveButton("Yes", (dialog, which) -> {
                db = new DBhelper(context);
                int id = modal.getBookingid();
                String bookingid = Integer.toString(id);
                db.deleteCourse(bookingid);
                Intent intent = new Intent(context, BookingActivity.class);
                context.startActivity(intent);

            });

            // Set the alert dialog no button click listener
            builder.setNegativeButton("No", (dialog, which) -> {

            });

            AlertDialog dialog = builder.create();
            // Display the alert dialog on interface
            dialog.show();

        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return BookingArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        public TextView tv_name,tv_bookingBategory,tv_paymentStatus,description;
        public Button button_delete_booking, delbutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            tv_name = itemView.findViewById(R.id.tv_nameCrew);
            tv_bookingBategory = itemView.findViewById(R.id.tv_crewProgress);
            tv_paymentStatus = itemView.findViewById(R.id.tv_crewQuantity);
            description = itemView.findViewById(R.id.textView5);
            button_delete_booking = itemView.findViewById(R.id.button_delete_booking);
            delbutton = itemView.findViewById(R.id.delbut);
        }
    }
}