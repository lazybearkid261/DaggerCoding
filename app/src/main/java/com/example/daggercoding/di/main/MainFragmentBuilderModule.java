package com.example.daggercoding.di.main;

import com.example.daggercoding.ui.main.MainActivity;
import com.example.daggercoding.ui.main.post.PostFragment;
import com.example.daggercoding.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostFragment contributePostFragment();
}
