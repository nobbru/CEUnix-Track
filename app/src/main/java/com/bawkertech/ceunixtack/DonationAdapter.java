package com.bawkertech.ceunixtack;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;


public class DonationAdapter extends ArrayAdapter<DonationOption> {

    public DonationAdapter(Context context, List<DonationOption> donationOptions) {
        super(context, 0, donationOptions);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_donate, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else vh = (ViewHolder) convertView.getTag();

        DonationOption option = getItem(position);
        if (option != null) {
            vh.description.setText(option.description);
            vh.paltform.setText(option.paltform);
        }

        return convertView;
    }

    private static final class ViewHolder {
        TextView description;
        TextView paltform;

        public ViewHolder(View v) {
            description = (TextView) v.findViewById(R.id.description);
            paltform = (TextView) v.findViewById(R.id.paltform);
        }
    }
}