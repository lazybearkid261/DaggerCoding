package com.example.daggercoding.di;


import android.app.Application;

import com.example.daggercoding.BaseApplication;
import com.example.daggercoding.SessionManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                AppModule.class,
                ViewModelFactoryModule.class,
        })
public interface AppComponent extends AndroidInjector<BaseApplication> { // we are going to inject this appComponent into BaseApplication, BaseApplication will be the client of AppComponent services

    SessionManager sessionManager();

    @Component.Builder
    interface Builder{

        @BindsInstance // used if you want to bind a particular instance of object to the component at the time of its construction
        Builder application(Application application); //bind this instance of application by then time appComponent is build --> có thể

        AppComponent build();
    }
}
