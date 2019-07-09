package com.example.daggercoding.network.main;

import com.example.daggercoding.models.Post;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {
    @GET("posts")
    Flowable<List<Post>> getPostFromUser(@Query("userId") int id);
}
