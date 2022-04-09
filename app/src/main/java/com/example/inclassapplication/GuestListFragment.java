package com.example.inclassapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;
//import retrofit2.RetrofitError;
import retrofit2.Call;

public class GuestListFragment extends Fragment implements ItemClickListener{

    View view;
    TextView headingTextView;
    ProgressBar progressBar;
    String numberOfGuests;
    Button reserveButton;

    public ArrayList<GuestListData> initGuestListData() {
        ArrayList<GuestListData> list = new ArrayList<>();

        list.add(new GuestListData("G 1",  "true"));
        list.add(new GuestListData("G 2",  "true"));
        list.add(new GuestListData("G 3",  "false"));
        list.add(new GuestListData("G 4",  "true"));

        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.guest_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //heading text view
        headingTextView = view.findViewById(R.id.hotel_recap_text_view);
        progressBar = view.findViewById(R.id.progress_bar);
        reserveButton = view.findViewById(R.id.reserve_button);

        TextView hotelRecapTextView = view.findViewById(R.id.hotel_recap_text_view);

        String hotelName = getArguments().getString("hotel name");
        String hotelPrice = getArguments().getString("hotel price");
        String hotelAvailability = getArguments().getString("hotel availability");
        numberOfGuests = getArguments().getString("number of guests");

        hotelRecapTextView.setText("You have selected " +hotelName+ ". The cost will be "+hotelPrice+ " and is " +hotelAvailability);


//         Set up the RecyclerView
//        //ArrayList<GuestListData> guestListData = initGuestListData();
//        RecyclerView recyclerView = view.findViewById(R.id.guest_list_recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), hotelListData);
//        recyclerView.setAdapter(hotelListAdapter);

//        getGuestsListsData();

        progressBar.setVisibility(View.VISIBLE);
        setupRecyclerView();

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                postGuestsListsData();

                progressBar.setVisibility(View.GONE);

                Bundle bundle = new Bundle();
                bundle.putString("hotel name", hotelName);
//                bundle.putString("check in date", checkInDate);
//                bundle.putString("check out date", checkOutDate);
                bundle.putString("number of guests", numberOfGuests);


                // set Fragment class Arguments
                ReserveConfirmationFragment reserveConfirmationFragment = new ReserveConfirmationFragment();
                reserveConfirmationFragment.setArguments(bundle);

//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, reserveConfirmationFragment);
                fragmentTransaction.remove(GuestListFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    }


    ArrayList<GuestListData> guestListData = initGuestListData();
    ReservationData modal = new ReservationData("Hotel1","20-02-2022","22-02-2022", guestListData);
    String reserveNumber;
    private void postGuestsListsData() {
        progressBar.setVisibility(View.VISIBLE);

        Call<ReserveConfirmationData> call = Api.getClient().createReservation(modal);

        call.enqueue(new Callback<ReserveConfirmationData>() {
            @Override
            public void onResponse(Call<ReserveConfirmationData> call1, Response<ReserveConfirmationData> response) {
                ReserveConfirmationData reserveConfirmation =response.body();
                System.out.println("--------- R.no: "+reserveConfirmation.getReservationNumber()+" ----------");
                //headingTextView.setText("Conf : " + reserveNumber);
                setupRecyclerView();
            }

            @Override
            public void onFailure(Call<ReserveConfirmationData> call1, Throwable error) {
                headingTextView.setText("Error found is : " + error.getMessage());
            }

        });

    }

    private void setupRecyclerView() {
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.guest_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GuestListAdapter guestListAdapter = new GuestListAdapter(getActivity(), Integer.parseInt(numberOfGuests));
        recyclerView.setAdapter(guestListAdapter);

        //Bind the click listener
        guestListAdapter.setClickListener(this);
    }



    @Override
    public void onClick(View view, int position) {
//        GuestListData guestListData = userListResponseData.get(position);
//
//        String guestName = guestListData.getGuest_name();
//        String gender = guestListData.getGender();
//
//        Bundle bundle = new Bundle();
//        bundle.putString("guest name", guestName);
//        bundle.putString("gender", gender);
//
//        HotelGuestDetailsFragment hotelGuestDetailsFragment = new HotelGuestDetailsFragment();
//        hotelGuestDetailsFragment.setArguments(bundle);
//
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.remove(HotelListFragment.this);
//        fragmentTransaction.replace(R.id.main_layout, hotelGuestDetailsFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commitAllowingStateLoss();

    }

}
