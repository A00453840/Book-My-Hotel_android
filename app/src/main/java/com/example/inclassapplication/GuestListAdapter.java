package com.example.inclassapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.ViewHolder>{

    private int numOfGuests;
    private LayoutInflater layoutInflater;

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
        //String guestName = guestListData.get(position).getGuest_name();
        //String gender = guestListData.get(position).getGender();
//
//        // set up the text
//        holder.hotelName.setText(hotelName);
//        holder.hotelPrice.setText(hotelPrice);
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

        TextView guestName, genderM, genderF;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guestName = itemView.findViewById(R.id.guest_name_edit_text);
            genderM = itemView.findViewById(R.id.male_radio_button);
            genderF = itemView.findViewById(R.id.female_radio_button);

            itemView.setOnClickListener(this);

        }

        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAbsoluteAdapterPosition());
        }

    }

}
