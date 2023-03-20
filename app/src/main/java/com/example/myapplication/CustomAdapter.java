package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList book_id, book_title, book_details;



    CustomAdapter(Activity activity, Context context, ArrayList book_id, ArrayList book_title, ArrayList book_details){
        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_details = book_details;
        this.book_title = book_title;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.book_id_txt.setText(String.valueOf(book_id.get(position)));
        holder.book_title_txt.setText(String.valueOf(book_title.get(position)));
        holder.book_details_txt.setText(String.valueOf(book_details.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(book_id.get(position)));
            intent.putExtra("title", String.valueOf(book_title.get(position)));
            intent.putExtra("details", String.valueOf(book_details.get(position)));
            activity.startActivityForResult(intent, 1);
        });


    }

    @Override
    public int getItemCount() {

        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, book_title_txt, book_details_txt;
        LinearLayout mainLayout;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            book_title_txt = itemView.findViewById(R.id.book_title_txt);
            book_details_txt = itemView.findViewById(R.id.book_details_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);


        }
    }
}
