package com.integratedworlds.mtt.ui.main.pharmacies;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.integratedworlds.mtt.R;
import com.integratedworlds.mtt.model.Interval;
import com.integratedworlds.mtt.model.Pharmacy;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PharmaciesAdapter extends RecyclerView.Adapter<PharmaciesAdapter.PharmacyViewHolder> {

    private ArrayList<Pharmacy> pharmacies;
    private View.OnClickListener addressClickListener;

    public PharmaciesAdapter(ArrayList<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    @NonNull
    @Override
    public PharmacyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_pharmacy, parent, false);

        addressClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pharmacy p = (Pharmacy) view.getTag();
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:"+ p.getLat() +","+ p.getLng() +"?q="+ p.getLat() +","+ p.getLng()));
                parent.getContext().startActivity(intent);
            }
        };

        return new PharmacyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyViewHolder holder, int position) {

        Pharmacy pharmacy = pharmacies.get(position);
        String workingTime = "";

        holder.tvPharmacyName.setText(pharmacy.getName());
        holder.tvPharmacyAddress.setText(pharmacy.getAddress());
        holder.tvPharmacyAddress.setTag(pharmacy);
        holder.tvPharmacyAddress.setOnClickListener(addressClickListener);
        holder.tvPharmacyContact.setText(pharmacy.getPhone());
        for (Interval interval: pharmacy.getIntervals()) {
            workingTime = workingTime + interval.getFormattedStartDate() +" - "+ interval.getFormattedEndDate() +"\n";
        }
        holder.tvPharmacyIntervals.setText(workingTime);
    }

    @Override
    public int getItemCount() {
        return pharmacies.size();
    }

    public static class PharmacyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_pharmacy_name)
        TextView tvPharmacyName;
        @BindView(R.id.tv_pharmacy_address)
        TextView tvPharmacyAddress;
        @BindView(R.id.tv_pharmacy_contact)
        TextView tvPharmacyContact;
        @BindView(R.id.tv_pharmacy_intervals)
        TextView tvPharmacyIntervals;

        public PharmacyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
