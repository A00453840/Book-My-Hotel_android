package com.example.inclassapplication;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class ReservationData {

    @SerializedName("hotel_name")
    String hotel_name;
    @SerializedName("checkin")
    String checkIn;
    @SerializedName("checkout")
    String checkOut;
    @SerializedName("guests_list")
    ArrayList<GuestListData> guestList;

    public ReservationData(String hotel_name, String checkIn, String checkOut, ArrayList<GuestListData> guestList) {
        this.hotel_name = hotel_name;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guestList = guestList;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getCheckIn() { return checkIn;}

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() { return checkOut;}

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public ArrayList<GuestListData> getGuestList() { return guestList; }

    public void setGuestList(ArrayList<GuestListData> guestList) {
        this.guestList = guestList;
    }

}
