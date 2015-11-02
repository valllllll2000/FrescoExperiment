package com.vaxapp.frescoexperiment;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by valeria on 2/11/15.
 */
public interface APIService {

    @GET("/services/rest/?&method=flickr.people.getPublicPhotos&api_key=4ef2fe2affcdd6e13218f5ddd0e2500d&user_id=29096781@N02&format=json&nojsoncallback=1")
    Call<ApiPhotos> loadRepo();

}
