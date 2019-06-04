package com.example.daggercoding.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.daggercoding.viewModels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelFactoryModule {
    //here is responsible for generating dependency injection of the factory class

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
    //adding ViewModelProviderFactory to injection dependency graphs. In other words, we provide an instance of ViewModelProviderFactory

    //the code above is similar to the code below
    // however, we dont have anything to do inside the method body so we're better use the code above as a better way
//    @Provides
//    public static ViewModelProvider.Factory provideViewModelFactory(ViewModelProviderFactory viewModelProviderFactory){
//        return viewModelProviderFactory;
//    }
}
