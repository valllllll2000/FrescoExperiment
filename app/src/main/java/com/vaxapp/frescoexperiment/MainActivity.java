package com.vaxapp.frescoexperiment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        OkHttpClient client = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(interceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<ApiPhotos> call = service.loadRepo();
        call.enqueue(new Callback<ApiPhotos>() {

            @Override
            public void onResponse(Response<ApiPhotos> response, Retrofit retrofit) {
                setUpRecyclerView(response.body().getApiPhoto().getPhoto(), recyclerView);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("failure", "api call failed", t);
            }
        });
    }

    private void setUpRecyclerView(List<FlickrPhoto> myDataset, RecyclerView mRecyclerView) {
        RecyclerView.Adapter mAdapter = new MyAdapter(myDataset);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}
