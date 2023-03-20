package com.example.myapplication.recyclerview;



import java.io.Serializable;

//Booking recyclerview
public class Booking implements Serializable {


    String name;
    int serviceID;
    String notes;
    String phone;
    String email;
    int bookingCheck;


    int bookingid;




    public Booking(int bookingid, int serviceID, String name ,String notes, String phone, String email,String username, int bookingCheck) {
        this.name = name;
        this.serviceID = serviceID;
        this.notes = notes;
        this.phone = phone;
        this.email = email;
        this.bookingCheck = bookingCheck;


        this.bookingid = bookingid;

    }
    public String getuserName() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServiceID() {
        return serviceID;
    }
    public String getServiceIDString() {

        int serid = serviceID;
        String sid = Integer.toString(serid);

        return sid;
    }
    public String getcategory() {

        serviceID=getServiceID();

        String result="";
        switch(serviceID){
            case 0: result= "Surface Clean (laptop)"; break;
            case 1: result= "Deep Cleaning (Laptop)";break;
            case 2: result= "Deep Cleaning (Extension keyboard)"; break;
            case 3: result=  "Lube Switches (Extension Keyboard)";break;
            case 4: result=  "Full Clean ( Laptop+  Extension Keyboard)";break;
        }

     return result;

    }


    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBookingCheck() {
        if (bookingCheck == 0 ){
        return "pending";}
        else {
            return " approve and paid" ;
        }
    }

    public void setBookingCheck(int bookingCheck) {
        this.bookingCheck = bookingCheck;
    }



    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }


}
