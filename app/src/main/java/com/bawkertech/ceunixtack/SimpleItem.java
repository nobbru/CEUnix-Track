package com.bawkertech.ceunixtack;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class SimpleItem extends DrawerItem<SimpleItem.ViewHolder>{

    private int selectedIconTint;
    private int selectedTextTint;

    private int unselectedTextTint;
    private int unselectedIconTint;

    private Drawable icon;
    private String title;


    public SimpleItem(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }


    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_option, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void bindViewHolder(ViewHolder holder) {
        holder.title.setText(title);
        holder.icon.setImageDrawable(icon);

        holder.title.setTextColor(isChecked ? selectedTextTint : unselectedTextTint);
        holder.icon.setColorFilter(isChecked ? selectedIconTint : unselectedIconTint);
    }

    public SimpleItem withSelectedIconTint(int selectedIconTint) {
        this.selectedIconTint = selectedIconTint;
        return this;
    }

    public SimpleItem withSelectedTextTint(int selectedTextTint) {
        this.selectedTextTint = selectedTextTint;
        return this;
    }

    public SimpleItem withIconTint(int unselectedIconTint) {
        this.unselectedIconTint = unselectedIconTint;
        return this;
    }

    public SimpleItem withTextTint(int unselectedTextTint) {
        this.unselectedTextTint = unselectedTextTint;
        return this;
    }

    static class ViewHolder extends DrawerAdapter.ViewHolder {

        private ImageView icon;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
        }
    }

}
