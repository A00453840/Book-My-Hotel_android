package com.example.inclassapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HotelSearchFragment extends Fragment {

    TextView titleTextView;
    View view;
    ConstraintLayout mainLayout;
    DatePicker fromDatePicker;
    DatePicker toDatePicker;
    Button searchButton;
    EditText guestsEditText;

    String numberOfGuests, checkInDate, checkOutDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.hotel_search_layout, container, false);
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainLayout = view.findViewById(R.id.main_layout);
        titleTextView = view.findViewById(R.id.title_textView);
//        searchtextConfirmationTextView = view.findViewById(R.id.serch_confirm_text-view)

        fromDatePicker = view.findViewById(R.id.from_date_picker);
        toDatePicker = view.findViewById(R.id.to_date_picker);
        searchButton = view.findViewById(R.id.search_button);
        guestsEditText = view.findViewById(R.id.edit_text);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkInDate = getDateFromCalender(fromDatePicker);
                checkOutDate = getDateFromCalender(toDatePicker);
                //Get input of guests count
                numberOfGuests = guestsEditText.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("check in date", checkInDate);
                bundle.putString("check out date", checkOutDate);
                bundle.putString("number of guests", numberOfGuests);


                // set Fragment class Arguments
                HotelListFragment hotelsListFragment = new HotelListFragment();
                hotelsListFragment.setArguments(bundle);

//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, hotelsListFragment);
                fragmentTransaction.remove(HotelSearchFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

//        numberOfGuests = guestsEditText.getText().toString();

        //Set up the text of confirm text box
//        confirmSearchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkInDate = getDateFromCalendar(checkInDatePicker);
//                checkOutDate = getDateFromCalendar(checkOutDatePicker);
//                //Get input of guests count
//                numberOfGuests = guestsCountEditText.getText().toString();
//                guestName = nameEditText.getText().toString();
//
//
//                // Saving into shared preferences
//                sharedPreferences = getActivity().getSharedPreferences(myPreference, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString(name, guestName);
//                editor.putString(guestsCount, numberOfGuests);
//                editor.commit();
//
//
//
//                searchTextConfirmationTextView.setText("Dear Customer, Your check in date is " + checkInDate + ", " +
//                        "your checkout date is " + checkOutDate + ".The number of guests are " + numberOfGuests);
//            }
//        });


    }

    private String getDateFromCalender(DatePicker datePicker)
    {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calender = Calendar.getInstance();
        calender.set(year,month,day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyy");
        return simpleDateFormat.format(calender.getTime());

    }

    /* retrieveButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sharedPreferences = getActivity().getSharedPreferences(myPreference, Context.MODE_PRIVATE);

            if (sharedPreferences.contains(name)) {
                nameEditText.setText(sharedPreferences.getString(name, ""));
            }
            if (sharedPreferences.contains(guestsCount)) {
                guestsCountEditText.setText(sharedPreferences.getString(guestsCount, ""));

            }
        }
    });

    //Clear Button Click Listener
        clearButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            guestsCountEditText.setText("");
            nameEditText.setText("");
        }
    });
}*/
}
