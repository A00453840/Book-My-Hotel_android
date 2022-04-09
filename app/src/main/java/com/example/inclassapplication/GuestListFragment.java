package com.example.inclassapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;

public class GuestListFragment extends Fragment implements ItemClickListener{

    View view;
    TextView headingTextView,reNum;
    ProgressBar progressBar;
    String numberOfGuests, checkIn, hotelName, checkOut,reservationNumber;
    Button reserveButton;
    ReserveConfirmationData reserveConfirmation = new ReserveConfirmationData("null");

//    public ArrayList<GuestListData> initGuestListData() {
//        ArrayList<GuestListData> list = new ArrayList<>();
//
//        list.add(new GuestListData("G 1",  "Male"));
//        list.add(new GuestListData("G 2",  "Female"));
//        list.add(new GuestListData("G 3",  "Male"));
//        list.add(new GuestListData("G 4",  "Female"));
//
//        return list;
//    }

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
//        headingTextView = view.findViewById(R.id.hotel_recap_text_view);
        progressBar = view.findViewById(R.id.progress_bar);
        reserveButton = view.findViewById(R.id.reserve_button);
        reNum = view.findViewById(R.id.reservation_number_text_view);

        TextView hotelRecapTextView = view.findViewById(R.id.hotel_recap_text_view);

        hotelName = getArguments().getString("hotel name");
        String hotelPrice = getArguments().getString("hotel price");
        String hotelAvailability = getArguments().getString("hotel availability");
        numberOfGuests = getArguments().getString("number of guests");
        checkIn = getArguments().getString("CheckIn date");
        checkOut = getArguments().getString("CheckOut date");

        hotelRecapTextView.setText("You have selected " +hotelName+ " with check-in date "+checkIn+" and check-out date "+checkOut+". The cost will be "+hotelPrice+ "/night and is " +hotelAvailability);


        progressBar.setVisibility(View.VISIBLE);
        setupRecyclerView();

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                postGuestsListsData();
                progressBar.setVisibility(View.GONE);

            }
        });

    }


//    ArrayList<GuestListData> guestListData = initGuestListData();

    private void postGuestsListsData() {
        ArrayList<GuestListData> guestListData = GuestListAdapter.getGuestList();
        System.out.println(guestListData);
        ReservationData modal = new ReservationData(hotelName,checkIn,checkOut, guestListData);

        Call<ReserveConfirmationData> call = Api.getClient().createReservation(modal);

        call.enqueue(new Callback<ReserveConfirmationData>() {
            @Override
            public void onResponse(Call<ReserveConfirmationData> call1, Response<ReserveConfirmationData> response) {
                reserveConfirmation =response.body();
                reservationNumber=reserveConfirmation.getReservationNumber();
//                System.out.println("--------- R.no: "+reservationNumber+" ----------");

                Bundle bundle = new Bundle();
                bundle.putString("hotel name", hotelName);
                bundle.putString("check in date", checkIn);
                bundle.putString("check out date", checkOut);
                bundle.putString("number of guests", numberOfGuests);
                bundle.putString("reservation number", reservationNumber);

                // set Fragment class Arguments
                ReserveConfirmationFragment reserveConfirmationFragment = new ReserveConfirmationFragment();
                reserveConfirmationFragment.setArguments(bundle);

               FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, reserveConfirmationFragment);
                fragmentTransaction.remove(GuestListFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }

            @Override
            public void onFailure(Call<ReserveConfirmationData> call1, Throwable error) {
                Toast.makeText(getActivity(), "Error found is : " + error.getMessage(), Toast.LENGTH_LONG).show();
//                System.out.println("Error found is : " + error.getMessage());
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
        //guestListAdapter.setClickListener(this);
    }



    @Override
    public void onClick(View view, int position) {

    }

}
