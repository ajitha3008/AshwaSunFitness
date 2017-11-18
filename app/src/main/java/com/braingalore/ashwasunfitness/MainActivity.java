package com.braingalore.ashwasunfitness;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.braingalore.ashwasunfitness.fragments.DashBoardFragment;
import com.braingalore.ashwasunfitness.fragments.HomeFragment;
import com.braingalore.ashwasunfitness.fragments.ConnectFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager;
            FragmentTransaction fragmentTransaction;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    HomeFragment homeFragment = new HomeFragment();
                    fragmentTransaction.replace(R.id.fragment_container, homeFragment, "HELLO");
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    DashBoardFragment dashBoardFragment = new DashBoardFragment();
                    fragmentTransaction.replace(R.id.fragment_container, dashBoardFragment, "HELLO");
                    fragmentTransaction.commit();
                    return true;
                case R.id.connect:
                    fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    ConnectFragment notificationFragment = new ConnectFragment();
                    fragmentTransaction.replace(R.id.fragment_container, notificationFragment, "HELLO");
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }

    };

    public void enableBackInSupportActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void disableBackInSupportActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DashBoardFragment dashBoardFragment = new DashBoardFragment();
        fragmentTransaction.replace(R.id.fragment_container, dashBoardFragment, "dashboard");
        fragmentTransaction.commit();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

}
