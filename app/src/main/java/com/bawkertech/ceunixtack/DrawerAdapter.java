package com.bawkertech.ceunixtack;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    private List<DrawerItem> drawerItems;

    private Map<Class<? extends DrawerItem>, Integer> viewTypes;

    private SparseArray<DrawerItem> holderFactories;

    private OnItemSelectedListener listener;

    public DrawerAdapter(List<DrawerItem> drawerItems){

    	this.drawerItems = drawerItems;
        this.viewTypes = new HashMap<>();
        this.holderFactories = new SparseArray<>();
        processViewTypes();
    }

    private void processViewTypes() {
        int type = 0;
        for (DrawerItem item: drawerItems) {
            if (!viewTypes.containsKey(drawerItems.getClass())) {
                viewTypes.put(item.getClass(), type);
                holderFactories.put(type, item);
                type++;
            }
        }

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = holderFactories.get(viewType).createViewHolder(parent);
        holder.drawerAdapter = this;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        drawerItems.get(position).bindViewHolder(holder);

    }

    @Override
    public int getItemViewType(int position) {
        return viewTypes.get(drawerItems.get(position).getClass());
    }

    @Override
    public int getItemCount() {
        return drawerItems.size();
    }

    public  void setSelected(int position) {
        DrawerItem newItem = drawerItems.get(position);

        if(!newItem.isSelectable()) {
            return;
        }


        for (int i = 0; i < drawerItems.size(); i++) {
            DrawerItem item = drawerItems.get(i);
            if (item.isChecked()) {
                item.setChecked(false);
                notifyItemChanged(i);
                break;
            }
        }

        newItem.setChecked(true);
        notifyItemChanged(position);

        if (listener != null) {
            listener.onItemSelected(position);
        }

    }

    public void setListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }

    public  interface OnItemSelectedListener {
        void onItemSelected(int position);
    }

    static  abstract class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private  DrawerAdapter drawerAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            drawerAdapter.setSelected(getAdapterPosition());
        }


    }

}
