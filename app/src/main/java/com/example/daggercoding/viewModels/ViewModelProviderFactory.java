package com.example.daggercoding.viewModels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;


public class ViewModelProviderFactory implements ViewModelProvider.Factory {
    private static final String TAG = "ViewModelProviderFactor";

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelProviderFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends  ViewModel> creator = creators.get(modelClass);
        if (creator == null) { // if the viewModel ahas not been created
            //Loop thorugh the alloable keys ( aka allowed classes with the @ViewModelKey )
            for(Map.Entry<Class<? extends  ViewModel>, Provider<ViewModel>> entry: creators.entrySet()){
                //if it's all allowed, set the Provider<ViewModel>
                if(modelClass.isAssignableFrom(entry.getKey())){
                    creator = entry.getValue();
                    break;
                }
            }
        }

        // if this is not one of the allowed keys, throws exception
        if( creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creator.get();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
