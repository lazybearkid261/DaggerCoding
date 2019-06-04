package com.example.daggercoding.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.example.daggercoding.R;
import com.example.daggercoding.viewModels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";

    private AuthViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable drawable;

    @Inject
    RequestManager glideManager;

    private ImageView imageView;
//    @Inject
//    String someString;
//
//    @Inject
//    boolean isAppNull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

//        Log.d(TAG, "onCreate: " + someString);
//        Log.d(TAG, "onCreate: is app null? " + isAppNull);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        imageView = (ImageView) findViewById(R.id.imageView_logo);
        setImageView();
    }

    private void setImageView(){
        glideManager.load(drawable).into(imageView);
    }
}
