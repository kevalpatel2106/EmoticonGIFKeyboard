package com.kevalpatel2106.gifpack.giphy;

import com.kevalpatel2106.emoticongifkeyboard.gifs.Gif;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Keval on 19-Aug-17.
 * Giphy endpoint listings.
 *
 * @author <a href='https://github.com/kevalpatel2106'>Kevalpatel2106</a>
 * @see <a href='https://developers.giphy.com/docs/'>Giphy API Docs</a>
 */

interface GiphyApiService {
    /* Base url for Giphy.com apis */
    String GIPHY_BASE_URL = "http://api.giphy.com/";

    /**
     * Load the trending GIFs list.
     *
     * @param limit Number of GIFs.
     * @return List of all the {@link Gif} or null of the error occurs.
     */
    @GET("v1/gifs/trending")
    Call<ResponseBody> getTrending(@Query("api_key") String apiKey,
                                   @Query("limit") int limit,
                                   @Query("fmt") String format);

    /**
     * Search GIFs.
     *
     * @param limit Number of GIFs.
     * @param query Search query string.
     * @return List of all the {@link Gif} or null of the error occurs.
     */
    @GET("/v1/gifs/search")
    Call<ResponseBody> searchGif(@Query("api_key") String apiKey,
                                 @Query("q") String query,
                                 @Query("limit") int limit,
                                 @Query("fmt") String format);
}
