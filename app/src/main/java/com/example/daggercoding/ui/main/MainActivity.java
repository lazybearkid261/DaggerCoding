package com.example.daggercoding.ui.main;


import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.daggercoding.BaseActivity;
import com.example.daggercoding.R;
import com.example.daggercoding.ui.main.post.PostFragment;
import com.example.daggercoding.ui.main.profile.ProfileFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        initNavigation();
//        addProfileFragmentToActivity();
    }

    private void initNavigation(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                sessionManager.logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    public void addProfileFragmentToActivity(){
//        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity_frameLayout, new PostFragment()).commit();
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_profile:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.profileFragment);
                break;
            case R.id.nav_post:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.postFragment);
                break;
        }
        menuItem.setChecked(true); //set the checked options is highlighted
        drawerLayout.closeDrawer(GravityCompat.START); //after clicking options, the navigation drawer is closed to the left
        return true;
    }
}
