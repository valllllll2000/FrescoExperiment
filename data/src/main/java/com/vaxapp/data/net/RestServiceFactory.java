package com.vaxapp.data.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class RestServiceFactory {

    public static final String BASE_URL = "https://api.flickr.com/";
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static <T> T createRetrofitService(final Class<T> clazz) {
        Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
        OkHttpClient client = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(interceptor);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                                                  .client(client)
                                                  .addConverterFactory(GsonConverterFactory.create(gson))
                                                  .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                                  .build();
        return retrofit.create(clazz);
    }
}
