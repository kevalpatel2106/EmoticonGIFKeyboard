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

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;

/**
 * Created by Keval on 16-Jun-17.
 * {@link Interceptor} to handle caching of the retrofit request.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

final class CacheInterceptor implements Interceptor {
    private static final int CACHE_SIZE = 5242880;          //5 MB //Cache size.

    @NonNull
    private final Context mContext;
    private final int mCacheTimeMills;

    /**
     * Constructor.
     *
     * @param cacheTimeMins Caching time in seconds.
     */
    CacheInterceptor(@NonNull final Context context,
                     final int cacheTimeMins) {
        mContext = context;
        mCacheTimeMills = cacheTimeMins * 60000;
    }

    /**
     * Initialize the cache directory.
     *
     * @param context Instance of caller.
     * @return {@link Cache}.
     */
    static Cache getCache(@NonNull final Context context) {
        //Define mCache
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        return new Cache(httpCacheDirectory, CACHE_SIZE);
    }

    @Override
    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
        okhttp3.Response originalResponse = chain.proceed(chain.request());
        if (isNetworkAvailable(mContext)) {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + mCacheTimeMills)
                    .build();
        } else {
            int maxStale = 2419200; // tolerate 4-weeks stale
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }

    /**
     * Check if the internet is available?
     *
     * @param context instance.
     * @return True if the internet is available else false.
     */
    private boolean isNetworkAvailable(@NonNull final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
