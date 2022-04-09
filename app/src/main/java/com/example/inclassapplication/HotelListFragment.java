package com.example.inclassapplication;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//import retrofit2.client.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//import retrofit2.RetrofitError;

public class HotelListFragment extends Fragment implements ItemClickListener{

    View view;
    TextView headingTextView;
    ProgressBar progressBar;
    String numberOfGuests, checkOutDate, checkInDate;

    public ArrayList<HotelListData> initHotelListData() {
        ArrayList<HotelListData> list = new ArrayList<>();

        list.add(new HotelListData("Hotel 1", "400$", "true"));
        list.add(new HotelListData("Hotel 2", "300$", "true"));
        list.add(new HotelListData("Hotel 3", "450$", "false"));
        list.add(new HotelListData("Hotel 4", "350$", "true"));
        list.add(new HotelListData("Hotel 5", "200$", "false"));
        list.add(new HotelListData("Hotel 6", "500$", "false"));

        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.hotel_list_test_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //heading text view
        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);

        checkInDate = getArguments().getString("check in date");
        checkOutDate = getArguments().getString("check out date");
        numberOfGuests = getArguments().getString("number of guests");

        //Set up the header
        headingTextView.setText("Welcome user, displaying hotel for " + numberOfGuests + " guests staying from " + checkInDate +
                " to " + checkOutDate);

        // Set up the RecyclerView
//        ArrayList<HotelListData> hotelListData = initHotelListData();
//        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), hotelListData);
//        recyclerView.setAdapter(hotelListAdapter);

        getHotelsListsData();

    }

    List<HotelListData> userListResponseData;

    private void getHotelsListsData() {
        progressBar.setVisibility(View.VISIBLE);
        Call<List<HotelListData>> call = Api.getClient().getHotelsLists();
        call.enqueue(new Callback<List<HotelListData>>() {
            @Override
            public void onResponse(Call<List<HotelListData>> call1, Response<List<HotelListData>> response) {
                userListResponseData = response.body();
                setupRecyclerView();
            }

            @Override
            public void onFailure(Call<List<HotelListData>> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
            }

//            @Override
//            public void success(List<HotelListData> userListResponses, Response response) {
//                // in this method we will get the response from API
//                userListResponseData = userListResponses;
//
//
//                // Set up the RecyclerView
//                setupRecyclerView();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                // if error occurs in network transaction then we can get the error in this method.
//                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
//
//            }
        });
    }

    private void setupRecyclerView() {
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), userListResponseData);
        recyclerView.setAdapter(hotelListAdapter);

        //Bind the click listener
        hotelListAdapter.setClickListener(this);
    }


    @Override
    public void onClick(View view, int position) {
        HotelListData hotelListData = userListResponseData.get(position);

        String hotelName = hotelListData.getHotel_name();
        String price = hotelListData.getPrice();
        String availability = hotelListData.getAvailability();

        Bundle bundle = new Bundle();
        bundle.putString("hotel name", hotelName);
        bundle.putString("hotel price", price);
        bundle.putString("hotel availability", availability);
        bundle.putString("number of guests", numberOfGuests);
        bundle.putString("CheckIn date", checkInDate);
        bundle.putString("CheckOut date", checkOutDate);

//        HotelGuestDetailsFragment hotelGuestDetailsFragment = new HotelGuestDetailsFragment();
//        hotelGuestDetailsFragment.setArguments(bundle);

        GuestListFragment guestListFragment = new GuestListFragment();
        guestListFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.remove(HotelListFragment.this);
        fragmentTransaction.replace(R.id.main_layout, guestListFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();

    }

}
