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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainLayout = view.findViewById(R.id.main_layout);
        titleTextView = view.findViewById(R.id.title_textView);

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

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, hotelsListFragment);
                fragmentTransaction.remove(HotelSearchFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

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

}
