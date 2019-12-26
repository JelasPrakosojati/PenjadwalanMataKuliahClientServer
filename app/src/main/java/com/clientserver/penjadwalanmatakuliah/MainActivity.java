package com.clientserver.penjadwalanmatakuliah;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = getSupportActionBar();

        loadFragment(new JadwalFragment());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Jadwal");

    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.nav_jadwal:
                    toolbar.setTitle("Jadwal");
                    fragment = new JadwalFragment();
                    break;
                case R.id.nav_matakuliah:
                    toolbar.setTitle("Mata Kuliah");
                    fragment = new MataKuliahFragment();
                    break;
                case R.id.nav_dosen:
                    toolbar.setTitle("Dosen");
                    fragment = new DosenFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };
}