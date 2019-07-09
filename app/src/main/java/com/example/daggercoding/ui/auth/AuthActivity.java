package com.example.daggercoding.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.daggercoding.R;
import com.example.daggercoding.models.User;
import com.example.daggercoding.ui.main.MainActivity;
import com.example.daggercoding.viewModels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";

    private AuthViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
    Drawable drawable;
    @Inject
    RequestManager glideManager;

    private ImageView imageView;
    private EditText editTextId;
    private Button btnOk;
    private ProgressBar progressBar;
//    @Inject
//    String someString;
//
//    @Inject
//    boolean isAppNull;


    @Override
    public ComponentName startService(Intent service) {
        return super.startService(service);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

//        Log.d(TAG, "onCreate: " + someString);
//        Log.d(TAG, "onCreate: is app null? " + isAppNull);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        //anh xa view
        imageView = (ImageView) findViewById(R.id.imageView_logo);
        editTextId = (EditText) findViewById(R.id.editText_userId);
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        setImageView();

        subscribeObserver();
    }

    private void setImageView() {
        glideManager.load(drawable).into(imageView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                attemptLogin();
                break;
        }
    }

    private void attemptLogin() {
        if (TextUtils.isEmpty(editTextId.getText().toString())) {
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(editTextId.getText().toString()));
    }

    private void subscribeObserver() {
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                switch (userAuthResource.status) {
                    case LOADING: {
                        showProgressBar(true);
                        break;
                    }
                    case AUTHENTICATED: {
                        showProgressBar(false);
                        onLoginSuccess();
                        Log.d(TAG, "onChanged: authenticated success " + userAuthResource.data.getEmail());
                        break;
                    }
                    case ERROR: {
                        showProgressBar(false);
                        Toast.makeText(AuthActivity.this, "Error authentication", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case NOT_AUTHENTICATED: {
                        showProgressBar(false);
                        break;
                    }
                }
            }
        });
    }

    private void onLoginSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProgressBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
