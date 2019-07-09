package com.example.daggercoding.ui.main.post;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daggercoding.R;
import com.example.daggercoding.adapters.PostsRecyclerViewAdapter;
import com.example.daggercoding.models.Post;
import com.example.daggercoding.ui.main.Resource;
import com.example.daggercoding.viewModels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends DaggerFragment {
    private static final String TAG = "PostFragment";
    private PostViewModel viewModel;
    //widget
    private RecyclerView recyclerView;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    PostsRecyclerViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(PostViewModel.class);

        initRecyclerView();
        subscribeObserver();
    }

    private void subscribeObserver(){
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                Log.d(TAG, "onChanged: " + (listResource == null) );
                if(listResource != null){
                    switch (listResource.status){
                        case LOADING:
                            Log.d(TAG, "onChanged: Data is loading");
                            break;
                        case ERROR:
                            Log.e(TAG, "onChanged: Data is error");
                            break;
                        case SUCCESS:
                            Log.d(TAG, "onChanged: Data is success");
                            adapter.setPosts(listResource.data);
                            break;
                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
