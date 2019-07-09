package com.example.daggercoding.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggercoding.SessionManager;
import com.example.daggercoding.models.User;
import com.example.daggercoding.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";

    private final SessionManager sessionManager;
    @Inject
    public ProfileViewModel(SessionManager sessionManager){
        this.sessionManager = sessionManager;
        Log.d(TAG, "ProfileViewModel: is ready");
    }

    public LiveData<AuthResource<User>> getAuthUser(){
        return sessionManager.getAuthenticatedUser();
    }
}
