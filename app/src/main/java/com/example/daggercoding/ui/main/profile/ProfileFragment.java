package com.example.daggercoding.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.daggercoding.R;
import com.example.daggercoding.models.User;
import com.example.daggercoding.ui.auth.AuthResource;
import com.example.daggercoding.viewModels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private static final String TAG = "ProfileFragment";
    private ProfileViewModel profileViewModel;

    //widget
    private TextView username, email, website;

    @Inject
    public ViewModelProviderFactory viewModelProviderFactory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        username = (TextView) view.findViewById(R.id.username);
        email = (TextView) view.findViewById(R.id.email);
        website = (TextView) view.findViewById(R.id.website);

        profileViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(ProfileViewModel.class);

        subscribeObservers();
    }

    private void subscribeObservers(){
        profileViewModel.getAuthUser().removeObservers(getViewLifecycleOwner());
        profileViewModel.getAuthUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if ( userAuthResource != null ){
                    switch (userAuthResource.status){
                        case AUTHENTICATED:
                            setDataAuthenticated(userAuthResource.data);
                            break;
                        case NOT_AUTHENTICATED:
                            setDataNotAuthenticated(userAuthResource.message);
                            break;
                    }
                }
            }
        });
    }

    private void setDataNotAuthenticated(String message) {
        Log.d(TAG, "setDataNotAuthenticated:");
        email.setText(message);
        username.setText(message);
        website.setText(message);
    }

    private void setDataAuthenticated(User data) {
        Log.d(TAG, "setDataAuthenticated: ");
        email.setText(data.getEmail());
        username.setText(data.getUsername());
        website.setText(data.getWebsite());
    }
}
