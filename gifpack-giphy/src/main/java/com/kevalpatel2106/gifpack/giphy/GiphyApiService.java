package com.kevalpatel2106.gifpack.giphy;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Keval on 19-Aug-17.
 *
 * @author <a href='https://github.com/kevalpatel2106'>Kevalpatel2106</a>
 */

interface GiphyApiService {
    String GIPHY_BASE_URL = "http://api.giphy.com/";

    @GET("v1/gifs/trending")
    Call<ResponseBody> getTrending(@Query("api_key") String apiKey,
                                   @Query("limit") int limit,
                                   @Query("fmt") String format);
}
