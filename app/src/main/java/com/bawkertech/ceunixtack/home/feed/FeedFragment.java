package com.bawkertech.ceunixtack.home.feed;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bawkertech.ceunixtack.R;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bawkertech.ceunixtack.DiscreteScrollViewOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;

public class FeedFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener<FeedAdapter.ViewHolder>,
        View.OnClickListener {

    private List<Item> data;
    private Feed feed;

    private TextView currentItemName;
    private TextView currentItemPrice;
    private DiscreteScrollView itemPicker;
    private InfiniteScrollAdapter<?> infiniteAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);

        currentItemName = rootView.findViewById(R.id.person_name);
        currentItemPrice = rootView.findViewById(R.id.missing_date);

        feed = Feed.get();
        data = feed.getData();
        itemPicker = rootView.findViewById(R.id.person_pictures);
        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);
        infiniteAdapter = InfiniteScrollAdapter.wrap(new FeedAdapter(data));
        itemPicker.setAdapter(infiniteAdapter);
        itemPicker.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

//        onItemChanged(data.get(0));


            FloatingActionButton fab = rootView.findViewById(R.id.person_locate);
            int color = getResources().getColor(R.color.orange);
            ColorStateList colorStateList = ColorStateList.valueOf(color);
             fab.setBackgroundTintList(colorStateList);

        rootView.findViewById(R.id.person_locate).setOnClickListener(this);
        rootView.findViewById(R.id.item_btn_comment).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                showUnsupportedSnackBar();
                break;
        }
    }

    private void onItemChanged(Item item) {
        currentItemName.setText(item.getName());
        currentItemPrice.setText(item.getMissingDate());
    }

    @Override
    public void onCurrentItemChanged(@Nullable FeedAdapter.ViewHolder viewHolder, int adapterPosition) {
        int positionInDataSet = infiniteAdapter.getRealPosition(adapterPosition);
        onItemChanged(data.get(positionInDataSet));
    }

    private void showUnsupportedSnackBar() {
        Snackbar.make(itemPicker, R.string.msg_unsupported_op, Snackbar.LENGTH_SHORT).show();
    }
}
