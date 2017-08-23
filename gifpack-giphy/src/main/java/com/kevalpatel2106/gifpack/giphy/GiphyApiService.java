/*
 * Copyright 2017 Keval Patel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
