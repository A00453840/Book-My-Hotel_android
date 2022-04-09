package com.example.inclassapplication;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

     //API's endpoints
    @GET("hotels")
    Call<List<HotelListData>> getHotelsLists();
//    public void getHotelsLists(Callback<List<HotelListData>> callback);

    @POST("reserve/")
    Call <ReserveConfirmationData> createReservation(@Body ReservationData reservationData);

}
