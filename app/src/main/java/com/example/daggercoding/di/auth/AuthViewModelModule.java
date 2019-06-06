package com.example.daggercoding.di.auth;

import androidx.lifecycle.ViewModel;

import com.example.daggercoding.di.ViewModelKey;
import com.example.daggercoding.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);

    //If there was another viewModel
//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel.class)
//    public abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);
}
