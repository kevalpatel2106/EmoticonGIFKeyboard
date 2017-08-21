package com.kevalpatel2106.emoticongifkeyboard.internal.gifs;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import java.util.List;

/**
 * Created by Keval on 19-Aug-17.
 *
 * @author <a href='https://github.com/kevalpatel2106'>Kevalpatel2106</a>
 */

public abstract class GifLoaderProtocol {

    /**
     * Load the trending GIFs list.
     *
     * @param limit Number of GIFs.
     * @return List of all the {@link Gif} or null of the error occurs.
     */
    @Nullable
    @WorkerThread
    public abstract List<Gif> getTrendingGifs(int limit);
}
