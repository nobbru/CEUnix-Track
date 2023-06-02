package com.bawkertech.ceunixtack.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bawkertech.ceunixtack.R;

public class FragmentSettings extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root  = (ViewGroup) inflater.inflate(R.layout.fragment_settings, container, false);
        return root;
    }
}
