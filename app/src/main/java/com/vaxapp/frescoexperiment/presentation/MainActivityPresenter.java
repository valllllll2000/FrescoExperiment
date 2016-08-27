package com.vaxapp.frescoexperiment.presentation;

import android.util.Log;
import com.vaxapp.domain.entity.FlickrPhoto;
import com.vaxapp.domain.interactor.UseCase;
import com.vaxapp.frescoexperiment.injector.PerActivity;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import rx.Subscriber;

@PerActivity
public class MainActivityPresenter implements Presenter {

    private final UseCase getPhoto;

    private MainActivityView view;

    @Inject
    public MainActivityPresenter(@Named("getPhotos") UseCase getPhoto) {
        this.getPhoto = getPhoto;
    }

    public void setView(MainActivityView view) {
        this.view = view;
    }

    public void onViewReady() {
        loadData();
    }

    private void loadData() {
        view.showRefreshing();
        getPhoto.execute(new Subscriber<List<FlickrPhoto>>() {
            @Override
            public void onCompleted() {
                Log.d("MainActivity", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("MainActivity", "onError", e);
                view.hideRefreshing();
            }

            @Override
            public void onNext(List<FlickrPhoto> photos) {
                Log.d("MainActivity", "onNext");
                view.hideRefreshing();
                view.showImages(photos);
            }
        }, null);
    }

    public void onRefreshView() {
        loadData();
    }

    @Override
    public void destroy() {
        getPhoto.unsubscribe();
    }
}
