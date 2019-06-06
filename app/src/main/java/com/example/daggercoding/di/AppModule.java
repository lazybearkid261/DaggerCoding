package com.example.daggercoding.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.daggercoding.R;
import com.example.daggercoding.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideRequestManager(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    //Provide test image
    @Singleton
    @Provides
    static Drawable provideDrawable(Application application){
        return ContextCompat.getDrawable(application, R.drawable.test);
    }

    //Provide Retrofit instance
    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

//    example adding dependency
//    @Provides
//    static String someString() {
//        return "this is a test string";
//    }

//    example adding dependency
//    @Provides
//    static boolean getApp(Application application) {
//        return application == null;
//    }
}
