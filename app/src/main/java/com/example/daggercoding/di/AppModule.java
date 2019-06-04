package com.example.daggercoding.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.daggercoding.R;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground);
    }

    @Provides
    static RequestManager provideGlideRequestManager(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    //Provide test image
    @Provides
    static Drawable provideDrawable(Application application){
        return ContextCompat.getDrawable(application, R.drawable.test);
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
