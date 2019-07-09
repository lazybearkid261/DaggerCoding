package com.example.daggercoding.ui.main.post;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.daggercoding.SessionManager;
import com.example.daggercoding.models.Post;
import com.example.daggercoding.models.User;
import com.example.daggercoding.network.main.MainApi;
import com.example.daggercoding.ui.auth.AuthResource;
import com.example.daggercoding.ui.main.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {
    private static final String TAG = "PostViewModel";
    private final SessionManager sessionManager;
    private final MainApi mainApi;

    private MediatorLiveData<Resource<List<Post>>> posts;

    @Inject
    public PostViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostViewModel: is ready");
    }

    public LiveData<Resource<List<Post>>> observePosts(){
        if(posts == null){
            posts = new MediatorLiveData<>();
            posts.setValue(Resource.loading((List<Post>) null));

            Log.d(TAG, "observePosts: user id: " + sessionManager.getAuthenticatedUser().getValue().data.getId());

            final LiveData<Resource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPostFromUser(sessionManager.getAuthenticatedUser().getValue().data.getId())
                    .onErrorReturn(new Function<Throwable, List<Post>>() {
                        @Override
                        public List<Post> apply(Throwable throwable) throws Exception {
                            Log.e(TAG, "apply: ", throwable);
                            Post post = new Post();
                            post.setId(-1);
                            ArrayList<Post> postList = new ArrayList<>();
                            postList.add(post);
                            return postList;
                        }
                    })
                    .map(new Function<List<Post>, Resource<List<Post>>>() {
                        @Override
                        public Resource<List<Post>> apply(List<Post> posts) throws Exception {
                            if (posts.size() > 0){
                                if( posts.get(0).getId() == -1){
                                    return Resource.error("Something went wrong", null);
                                }
                            }
                            return Resource.success(posts);
                        }
                    })
                    .subscribeOn(Schedulers.io())
            );
            posts.addSource(source, new Observer<Resource<List<Post>>>() {
                @Override
                public void onChanged(Resource<List<Post>> listResource) {
                    posts.setValue(listResource);
                    posts.removeSource(source);
                }
            });
        }
        return posts;
    }
}
