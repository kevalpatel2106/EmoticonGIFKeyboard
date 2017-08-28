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

package com.kevalpatel2106.emoticongifkeyboard.gifs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import java.util.List;

/**
 * Created by Keval on 19-Aug-17.
 * Protocol to implement GIFs provider. Create an adapter to integrate different GIF services
 * like GIPHY or Tenor.
 *
 * @author <a href='https://github.com/kevalpatel2106'>Kevalpatel2106</a>
 */

public interface GifProviderProtocol {

    /**
     * Load the trending GIFs list.
     *
     * @param limit Number of GIFs.
     * @return List of all the {@link Gif} or null of the error occurs.
     */
    @Nullable
    @WorkerThread
    List<Gif> getTrendingGifs(int limit);


    /**
     * Search GIFs list.
     *
     * @param limit Number of GIFs.
     * @param query Search query.
     * @return List of all the {@link Gif} or null of the error occurs.
     */
    @Nullable
    @WorkerThread
    List<Gif> searchGifs(int limit, @NonNull String query);


}
