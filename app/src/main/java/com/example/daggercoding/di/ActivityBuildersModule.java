package com.example.daggercoding.di;

import com.example.daggercoding.di.auth.AuthModule;
import com.example.daggercoding.di.auth.AuthViewModelModule;
import com.example.daggercoding.di.main.MainFragmentBuilderModule;
import com.example.daggercoding.di.main.MainModule;
import com.example.daggercoding.di.main.MainViewModelModule;
import com.example.daggercoding.ui.auth.AuthActivity;
import com.example.daggercoding.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
    // here is where sub component is to be found
    // serve as a subcomponent, only AuthActivity ( auth subcomponent access AuthViewModelModule)
    //moi dong contributesAndroidInjector la 1 subcomponent cua AppComponent, va cac Module khai bao nam trong cac subcomponent
    @ContributesAndroidInjector(
            modules = {AuthViewModelModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = { MainFragmentBuilderModule.class,
                    MainViewModelModule.class,
                    MainModule.class }
    )
    abstract MainActivity contributeMainActivity();
}

