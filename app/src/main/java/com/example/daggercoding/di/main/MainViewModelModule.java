package com.example.daggercoding.di.main;

import androidx.lifecycle.ViewModel;

import com.example.daggercoding.di.ViewModelKey;
import com.example.daggercoding.ui.main.post.PostViewModel;
import com.example.daggercoding.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    public abstract ViewModel bindPostViewModel(PostViewModel postViewModel);
}
