package com.bawkertech.ceunixtack.home.missing;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bawkertech.ceunixtack.App;
import com.bawkertech.ceunixtack.R;
import com.bawkertech.ceunixtack.models.MissingPerson;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MissingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MissingFragment extends Fragment {

    ListView listViewMissingPersons;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MissingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MissingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MissingFragment newInstance(String param1, String param2) {
        MissingFragment fragment = new MissingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_missing, container, false);

//        for (MissingPerson missingPerson : App.list) {
//
//            listViewMissingPersons = rootView.findViewById(R.id.listViewMissingPersons);
//            View listItemView = inflater.inflate(R.layout.list_item_missing_person, null);
//            ImageView imageViewPerson = listItemView.findViewById(R.id.imageViewPerson);
//            TextView textViewName = listItemView.findViewById(R.id.textViewName);
//            TextView textViewLastLocation = listItemView.findViewById(R.id.textViewLastLocation);
//
//            //imageViewPerson.setImageResource(R.drawable._0230325_115755);
//            textViewName.setText(missingPerson.getName());
//            textViewLastLocation.setText(missingPerson.getLastKnownLocation());
//
//            listViewMissingPersons.addView(listItemView);
//        }

        return rootView;
    }
}