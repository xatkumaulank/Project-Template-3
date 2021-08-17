package com.example.customnavigationdrawler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.customnavigationdrawler.databinding.ActivityMainBinding;
import com.example.customnavigationdrawler.fragments.FavoriteFragment;
import com.example.customnavigationdrawler.fragments.HistoryFragment;
import com.example.customnavigationdrawler.fragments.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_FAVORITE = 1;
    public static final int FRAGMENT_HISTORY = 2;

    private int currentFragment = FRAGMENT_HOME;

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        setSupportActionBar(binding.toolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolBar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        binding.navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home){
            if (currentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                currentFragment = FRAGMENT_HOME;
            }
        }else if (id == R.id.nav_favorite){
            if (currentFragment != FRAGMENT_FAVORITE){
                replaceFragment(new FavoriteFragment());
                currentFragment = FRAGMENT_FAVORITE;
            }
        }else if (id == R.id.nav_history){
            if (currentFragment != FRAGMENT_HISTORY){
                replaceFragment(new HistoryFragment());
                currentFragment = FRAGMENT_HISTORY;
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragment);
        fragmentTransaction.commit();
    }
}