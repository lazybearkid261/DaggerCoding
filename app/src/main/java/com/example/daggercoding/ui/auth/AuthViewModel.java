package com.example.daggercoding.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.example.daggercoding.SessionManager;
import com.example.daggercoding.network.auth.AuthApi;
import com.example.daggercoding.models.User;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthActivity";
    private final AuthApi authApi;
    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager){
        this.authApi = authApi;
        this.sessionManager = sessionManager;
        //right here we do not have any dependencies injected through this constructor as there's no parameter
        Log.d(TAG, "AuthViewModel: is working");
//        authApi.getUser(1)
//                .toObservable()
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<User>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(User user) {
//                        Log.d(TAG, "onNext: User's email: " + user.getEmail());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, "onError: error", e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    public void authenticateWithId(int userId){
        sessionManager.authenticateWithId(queryUserId(userId));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId){
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if(user.getId() == -1){
                                    return AuthResource.error("Could not authenticate", (User) null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io()));
    }

    public LiveData<AuthResource<User>> observeAuthState(){
        return sessionManager.getAuthenticatedUser();
    }
}
