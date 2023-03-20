package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.recyclerview.Booking;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
    public static final  String DBNAME="database.db";
    private Context context;
    public static final String name = "NAME";

    public DBhelper(@Nullable Context context) {
        super(context,"database.db", null, 1);
        this.context =context;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

         db.execSQL("create table users(user_id INTEGER PRIMARY KEY AUTOINCREMENT ,username TEXT , password TEXT )");
        db.execSQL("create table booking (bookingid  INTEGER primary key autoincrement, serviceID INTEGER ,username TEXT references users('username') , name TEXT,notes TEXT,email TEXT,phone TEXT,bookingcheck INTEGER )");
         db.execSQL("create table library(id INTEGER PRIMARY KEY AUTOINCREMENT  , title TEXT, details TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists booking");
        db.execSQL("drop table if exists library");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("password", password);


        long result  = db.insert("users",null,values);
        if(result==-1)return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else
            return false;
    }

    public Boolean checkuserpass(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username =? and password =?", new String[] {username,password});
        if(cursor.getCount()>0){
            return true;
        }else
            return false;
    }
    public Long getUserId(String username, String password) throws SQLException
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT user_id FROM users WHERE username=? AND password=?", new String[]{username,password});
        if (mCursor != null && mCursor.moveToFirst()) {
            if(mCursor.getCount() > 0)
            {
                return mCursor.getLong(0);            }
        }
        return null;
    }

    void addBook(String title, String details){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();

        cv.put("title",title);
        cv.put("details",details);

        long result= db.insert("library",null,cv);
        if(result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

        }else
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();

    }

    Cursor readAllData(){

        String query = "SELECT * FROM " + "library";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }

    void updateData(String row_id, String title, String details){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title",title);
        cv.put("details",details);

        long result = db.update("library",cv, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Succesfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result =db.delete("library", "id=?", new String[]{row_id});
        if(result==-1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Succesfully Deleted", Toast.LENGTH_SHORT).show();
        }


    }

    void deleteAllData(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL(" DELETE FROM "+ "library");
    }

    public ArrayList<Booking> readCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + "booking", null);

        // on below line we are creating a new array list.
        ArrayList<Booking> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new Booking(cursorCourses.getInt(0),
                        cursorCourses.getInt(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getString(5),
                        cursorCourses.getString(6),
                        cursorCourses.getInt(7)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }


    public ArrayList<Booking> readCoursesUser(String username) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + "booking"+ " where username = ?", new String[]{username} );

        // on below line we are creating a new array list.
        ArrayList<Booking> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new Booking(cursorCourses.getInt(0),
                        cursorCourses.getInt(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getString(5),
                        cursorCourses.getString(6),
                        cursorCourses.getInt(7)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }


    public void deleteCourse(String id) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete("booking", "bookingid=?", new String[]{id});
        db.close();
    }


    public void acceptbook(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("bookingcheck",1);


        long result = db.update("booking",cv, "bookingid=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Succesfully Updated", Toast.LENGTH_SHORT).show();
        }
    }
    public void addNewBooking( int serviceID, String name,String notes,String username, String phone, String email, int bookingcheck) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.

        values.put("serviceID", serviceID);
        values.put("username",username);
        values.put("name", name);
        values.put("notes", notes);
        values.put("phone", phone);
        values.put("email", email);
        values.put( "bookingcheck", bookingcheck);

        // after adding all values we are passing
        // content values to our table.
        db.insert("booking", null, values);

        // at last we are closing our
        // database after adding database.

    }


}
