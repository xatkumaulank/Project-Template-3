package com.example.customnavigationdrawler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.customnavigationdrawler.databinding.ActivityMainBinding;
import com.example.customnavigationdrawler.fragments.FavoriteFragment;
import com.example.customnavigationdrawler.fragments.HistoryFragment;
import com.example.customnavigationdrawler.fragments.HomeFragment;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_FAVORITE = 1;
    public static final int FRAGMENT_HISTORY = 2;

    private int currentFragment = FRAGMENT_HOME;
    private ViewPagerAdapter viewPagerAdapter;

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        viewPagerAdapter = new ViewPagerAdapter(this);
        //binding.viewPager2.setAdapter(viewPagerAdapter);


//        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                switch (position){
//                    case 0:
//                        tab.setText(getString(R.string.nav_home));
//                        break;
//
//                    case 1:
//                        tab.setText(getString(R.string.nav_favorite));
//                        break;
//
//                    case 2:
//                        tab.setText(getString(R.string.nav_history));
//                        break;
//                }
//            }
//        }).attach();

        setSupportActionBar(binding.toolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolBar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        binding.navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        binding.bottomNavigation.getMenu().findItem(R.id.bottom_home).setChecked(true);


        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_home){
                    replaceHomeFragment();
                    binding.navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                }else  if (id == R.id.bottom_favorite){
                    replaceFavoriteFragment();
                    binding.navigationView.getMenu().findItem(R.id.nav_favorite).setChecked(true);
                }else  if (id == R.id.bottom_history){
                    replaceHistoryFragment();
                    binding.navigationView.getMenu().findItem(R.id.nav_history).setChecked(true);
                }
                return true;
            }
        });
//        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                switch (position){
//                    case 0:
//                        currentFragment = FRAGMENT_HOME;
//                        binding.navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
//                        break;
//
//                    case 1:
//                        currentFragment = FRAGMENT_FAVORITE;
//                        binding.navigationView.getMenu().findItem(R.id.nav_favorite).setChecked(true);
//                        break;
//
//                    case 2:
//                        currentFragment = FRAGMENT_HISTORY;
//                        binding.navigationView.getMenu().findItem(R.id.nav_history).setChecked(true);
//                        break;
//                }
//            }
//        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home){
            replaceHomeFragment();
            binding.bottomNavigation.getMenu().findItem(R.id.bottom_home).setChecked(true);
        }else if (id == R.id.nav_favorite){
            replaceFavoriteFragment();
            binding.bottomNavigation.getMenu().findItem(R.id.bottom_favorite).setChecked(true);
        }else if (id == R.id.nav_history){
           replaceHistoryFragment();
            binding.bottomNavigation.getMenu().findItem(R.id.bottom_history).setChecked(true);
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void replaceHomeFragment(){
        if (currentFragment != FRAGMENT_HOME){
            replaceFragment(new HomeFragment());
            //binding.viewPager2.setCurrentItem(0);
            currentFragment = FRAGMENT_HOME;
        }
    }
    private void replaceFavoriteFragment(){
        if (currentFragment != FRAGMENT_FAVORITE){
            replaceFragment(new FavoriteFragment());
            //binding.viewPager2.setCurrentItem(1);
            currentFragment = FRAGMENT_FAVORITE;
        }
    }
    private void replaceHistoryFragment(){
        if (currentFragment != FRAGMENT_HISTORY){
            replaceFragment(new HistoryFragment());
            //binding.viewPager2.setCurrentItem(2);
            currentFragment = FRAGMENT_HISTORY;
        }
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