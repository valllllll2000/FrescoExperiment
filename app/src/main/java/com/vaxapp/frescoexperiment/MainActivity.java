package com.vaxapp.frescoexperiment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private FlickrAdapter flickrAdapter;
    private FlickerApiService service = new FlickerApiService();
    Subscriber subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initUi();
        loadData();
    }

    private void loadData() {
        Observable<ApiPhotos> observable = service.loadRepoRx();

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ApiPhotos>() {
                    @Override
                    public void onCompleted() {
                        Log.d("MainActivity", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MainActivity", "onError", e);
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(ApiPhotos response) {
                        Log.d("MainActivity", "onNext");
                        swipeRefreshLayout.setRefreshing(false);
                        flickrAdapter.addAll(response.getApiPhoto().getPhoto());
                    }
                });
    }

    private void initUi() {
        flickrAdapter = new FlickrAdapter(new ArrayList<FlickrPhoto>());
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(flickrAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                flickrAdapter.clear();
                loadData();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }
}
