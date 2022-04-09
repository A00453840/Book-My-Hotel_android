package com.example.inclassapplication;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.ViewHolder>{

    private int numOfGuests;
    private LayoutInflater layoutInflater;
    static ArrayList<GuestListData> guestList = new ArrayList<>();

    //Data gets passed in the constructor
    GuestListAdapter(Context context, int numOfGuests) {
        this.layoutInflater = LayoutInflater.from(context);
        this.numOfGuests = numOfGuests;
    }

    @NonNull
    @Override
    public GuestListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.guest_list_layout, parent, false);
        return new GuestListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestListAdapter.ViewHolder holder, int position) {

        GuestListData guest = new GuestListData("","");

        holder.guestName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) { guest.setGuest_name(editable.toString());  }

        });
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(holder.genderF.isChecked()){
                    guest.setGender(holder.genderF.getText().toString());}
                else {guest.setGender(holder.genderM.getText().toString());}
            }
        });

        guestList.add(position,guest);
        System.out.println("guest list"+guestList.get(0).getGuest_name());
    }

    @Override
    public int getItemCount() {
            return numOfGuests;
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView guestName;
        RadioButton genderM, genderF;
        RadioGroup radioGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guestName = itemView.findViewById(R.id.guest_name_edit_text);
            genderM = itemView.findViewById(R.id.male_radio_button);
            genderF = itemView.findViewById(R.id.female_radio_button);
            radioGroup = itemView.findViewById(R.id.gender_radio_group);
            itemView.setOnClickListener(this);

        }

        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAbsoluteAdapterPosition());
        }

    }

    public static ArrayList<GuestListData> getGuestList(){ return guestList;}

}
