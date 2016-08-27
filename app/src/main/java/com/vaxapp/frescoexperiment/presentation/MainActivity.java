package com.vaxapp.frescoexperiment.presentation;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.vaxapp.domain.entity.FlickrPhoto;
import com.vaxapp.frescoexperiment.R;
import com.vaxapp.frescoexperiment.injector.component.DaggerPhotoComponent;
import com.vaxapp.frescoexperiment.injector.component.PhotoComponent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainActivityView {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private FlickrAdapter flickrAdapter;

    @Inject
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUi();
        initializeInjector();
        presenter.setView(this);
        presenter.onViewReady();
    }

    private void initializeInjector() {
        PhotoComponent userComponent = DaggerPhotoComponent.builder()
                                                           .applicationComponent(getApplicationComponent())
                                                           .activityModule(getActivityModule())
                                                           .build();
        userComponent.inject(this);
    }

    @Override
    public void showImages(List<FlickrPhoto> photos) {
        flickrAdapter.clear();
        flickrAdapter.addAll(photos);
    }

    @Override
    public void showRefreshing() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
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
                presenter.onRefreshView();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
