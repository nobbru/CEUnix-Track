package com.bawkertech.ceunixtack.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawkertech.ceunixtack.AutoSpaceItem;
import com.bawkertech.ceunixtack.DonationAdapter;
import com.bawkertech.ceunixtack.DonationOption;
import com.bawkertech.ceunixtack.DrawerAdapter;
import com.bawkertech.ceunixtack.DrawerItem;
import com.bawkertech.ceunixtack.R;
import com.bawkertech.ceunixtack.SimpleItem;
import com.bawkertech.ceunixtack.SpaceItem;
import com.bawkertech.ceunixtack.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yarolegovich.lovelydialog.LovelyChoiceDialog;
import com.yarolegovich.lovelydialog.LovelySaveStateHandler;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

public class Home extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener{

    ActivityHomeBinding binding;
    private static  final int POS_CLOSE = 0;
    private static  final int POS_DASHBOARD = 1;
    private static  final int POS_PROFILE = 2;
    private static  final int POS_DONATE = 4;

    private static  final int POS_SETTINGS = 3;

    private static  final int POS_LOGOUT = 6;


    private String[] screenTitles;

    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    private LovelySaveStateHandler saveStateHandler;

//    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());


        saveStateHandler = new LovelySaveStateHandler();

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(binding.toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();
        
        screenIcons = loadCreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_DASHBOARD),
                createItemFor(POS_PROFILE),
                createItemFor(POS_DONATE),
                createItemFor(POS_SETTINGS),
                new SpaceItem(260),
                createItemFor(POS_LOGOUT)
        ));

        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        adapter.setSelected(POS_DASHBOARD);

        // Add initial fragment transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentHome fragmentHome = new FragmentHome();
        transaction.replace(binding.container.getId(), fragmentHome);
        transaction.commit();


    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.purple_500))
                .withTextTint(color(R.color.black))
                .withSelectedIconTint(color(R.color.purple_500))
                .withSelectedTextTint(color(R.color.purple_500));


    }

    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(this, res);
    }
    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitles);
    }

    private Drawable[] loadCreenIcons() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.id_activityScreenIcons);
        Drawable[] icons = new Drawable[typedArray.length()];

        for (int i = 0; i < typedArray.length(); i++){
            int id = typedArray.getResourceId(i, 0);
            if (id != 0){
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        typedArray.recycle();
        return icons;
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Confirmation")
                .setMessage("Are you sure you want to exit? Afraid to go on")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Exit the app or perform any necessary cleanup
                        finish();
                    }
                })
                .setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void onItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);
        switch (position) {
            case POS_DASHBOARD:
//                FragmentHome fragmentHome = new FragmentHome();
                transaction.replace(R.id.container, FragmentHome.class, null);
                break;
            case POS_PROFILE:
//                FragmentProfile fragmentProfile = new FragmentProfile();
                transaction.replace(binding.container.getId(), FragmentProfile.class, null);
                break;
            case  POS_DONATE:

                ArrayAdapter<DonationOption> adapter = new DonationAdapter(this, loadDonationOptions());
                new LovelyChoiceDialog(this)
                        .setTopColorRes(R.color.purple_500)
                        .setTitle(R.string.donate_title)
                        .setInstanceStateHandler(position, saveStateHandler)
                        .setIcon(R.drawable.baseline_monetization_on_24)
                        .setMessage(R.string.donate_message)
                        .setItems(adapter, (position1, item) ->
                                Toast.makeText(Home.this, item.paltform,
                                                Toast.LENGTH_SHORT)
                                        .show())
                        .setSavedInstanceState(getIntent().getBundleExtra("savedInstanceState"))
                        .show();

                return;


            case POS_SETTINGS:
                 //fragmentSettings = new FragmentSettings();
                transaction.replace(binding.container.getId(), FragmentSettings.class, null);
                break;
                case POS_LOGOUT:
                    onBackPressed();
                    return;

        }

        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private List<DonationOption> loadDonationOptions() {
        List<DonationOption> result = new ArrayList<>();
        String[] raw = getResources().getStringArray(R.array.donations);
        for (String op : raw) {
            String[] info = op.split("%");
            result.add(new DonationOption(info[1], info[0]));
        }
        return result;
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "Ohh not come back! You don't want to find your relative?", Toast.LENGTH_SHORT).show();
        super.onStop();
    }
}