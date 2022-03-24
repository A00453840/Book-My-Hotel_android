package com.example.inclassapplication;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HotelListFragment extends Fragment {

    View view;
    TextView headingTextView;
    ProgressBar progressBar;

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
        view = inflater.inflate(R.layout.hotel_list_test_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //heading text view
        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);

        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        String numberOfGuests = getArguments().getString("number of guests");

        //Set up the header
        headingTextView.setText("Welcome user, displaying hotel for " + numberOfGuests + " guests staying from " + checkInDate +
                " to " + checkOutDate);

        // Set up the RecyclerView
        ArrayList<HotelListData> hotelListData = initHotelListData();
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), hotelListData);
        recyclerView.setAdapter(hotelListAdapter);

    }

}
