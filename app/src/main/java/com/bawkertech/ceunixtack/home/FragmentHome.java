package com.bawkertech.ceunixtack.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bawkertech.ceunixtack.R;
import com.bawkertech.ceunixtack.databinding.FragmentHomeBinding;
import com.bawkertech.ceunixtack.home.missing.MissingFragment;
import com.bawkertech.ceunixtack.home.post.PostFragment;
import com.bawkertech.ceunixtack.home.feed.FeedFragment;

public class FragmentHome extends Fragment {

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        replaceHomeFragment(new FeedFragment());
        binding.bottomNavigationView.setSelectedItemId(R.id.feed);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.feed:
                    replaceHomeFragment(new FeedFragment());
                    break;
                case R.id.post:
                    replaceHomeFragment(new PostFragment());
                    break;
                case R.id.missing:
                    replaceHomeFragment(new MissingFragment());
                    break;

            }
            return true;
        });


        return root;

    }

    private void replaceHomeFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_frame_lay, fragment);
        fragmentTransaction.commit();
    }
}
