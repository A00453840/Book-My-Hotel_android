package com.example.inclassapplication;

import com.google.gson.annotations.SerializedName;

public class ReserveConfirmationData {

    @SerializedName("confirmation_number")
    String reservationNumber;

    public ReserveConfirmationData(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

}
