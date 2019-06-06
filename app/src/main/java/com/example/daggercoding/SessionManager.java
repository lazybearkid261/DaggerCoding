package com.example.daggercoding;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.daggercoding.models.User;
import com.example.daggercoding.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
    private static final String TAG = "SessionManager";

    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    //inject this SessionManager because we will inject it in many other places
    @Inject
    public SessionManager(){

    }

    public void authenticateWithId(LiveData<AuthResource<User>> source){
        if( cachedUser != null){
            cachedUser.setValue(AuthResource.loading((User) null));
            Log.d(TAG, "authenticateWithId: " + cachedUser.getValue());
            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });
        }
    }

    public void logOut(){
        Log.d(TAG, "logOut: logging out");
        cachedUser.setValue(AuthResource.logout());
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        Log.d(TAG, "getAuthenticatedUser: " + (cachedUser == null));
        return cachedUser;
    }
}
