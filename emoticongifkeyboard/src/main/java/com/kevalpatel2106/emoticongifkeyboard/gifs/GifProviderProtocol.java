package com.kevalpatel2106.emoticongifkeyboard.gifs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import java.util.List;

/**
 * Created by Keval on 19-Aug-17.
 * Protocol to implement GIFs provider. Create an adapter for GIF service to integrate.
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
