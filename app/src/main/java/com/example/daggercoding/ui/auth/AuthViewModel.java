package com.example.daggercoding.ui.auth;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    @Inject
    public AuthViewModel(){
        //right here we do not have any dependencies injected through this constructor as there's no parameter
    }
}
