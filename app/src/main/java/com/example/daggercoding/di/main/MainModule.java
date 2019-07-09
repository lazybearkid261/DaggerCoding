package com.example.daggercoding.di.main;

import com.example.daggercoding.adapters.PostsRecyclerViewAdapter;
import com.example.daggercoding.network.main.MainApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {
    @Provides
    static PostsRecyclerViewAdapter providePostsRecyclerViewAdapter(){
        return new PostsRecyclerViewAdapter();
    }

    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }
}
