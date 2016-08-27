package com.vaxapp.frescoexperiment.presentation;

import android.util.Log;
import com.vaxapp.frescoexperiment.ApiPhotos;
import com.vaxapp.frescoexperiment.FlickerApiService;
import com.vaxapp.frescoexperiment.injector.PerActivity;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@PerActivity
public class MainActivityPresenter implements Presenter {

    private FlickerApiService service = new FlickerApiService();

    private MainActivityView view;

    private Subscription apiSubscription;

    @Inject
    public MainActivityPresenter() {
    }

    public void setView(MainActivityView view) {
        this.view = view;
    }

    public void onViewReady() {
        loadData();
    }

    private void loadData() {
        view.showRefreshing();
        Observable<ApiPhotos> observable = service.loadRepoRx();
        apiSubscription = observable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(new Subscriber<ApiPhotos>() {
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
                public void onNext(ApiPhotos response) {
                    Log.d("MainActivity", "onNext");
                    view.hideRefreshing();
                    view.showImages(response);
                }
            });
    }

    public void onRefreshView() {
        loadData();
    }

    @Override
    public void destroy() {
        if (apiSubscription != null && !apiSubscription.isUnsubscribed()) {
            apiSubscription.unsubscribe();
        }
    }
}
