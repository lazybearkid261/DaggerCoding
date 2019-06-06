package com.example.daggercoding.ui.main;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.daggercoding.BaseActivity;
import com.example.daggercoding.R;
import com.example.daggercoding.ui.main.profile.ProfileFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addProfileFragmentToActivity();
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

    public void addProfileFragmentToActivity(){
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity_frameLayout, new ProfileFragment()).commit();
    }
}
