package com.mobcomm.ekgmonitoring;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.akshay.library.CurveBottomBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mobcomm.ekgmonitoring.Fragment.ActivityFragment;
import com.mobcomm.ekgmonitoring.Fragment.MeasureFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    CurveBottomBar cbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        loadFragment(new MeasureFragment());

        cbb = findViewById(R.id.customBottomBar);
        cbb.inflateMenu(R.menu.bottom_menu);
        cbb.setItemIconTintList(null);
        cbb.setOnNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.action_measure:
                fragment = new MeasureFragment();
                break;
            case R.id.action_activity:
                fragment = new ActivityFragment();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}