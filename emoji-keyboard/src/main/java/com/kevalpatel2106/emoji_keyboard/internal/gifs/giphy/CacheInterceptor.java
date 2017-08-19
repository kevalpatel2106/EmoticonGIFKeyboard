package com.kevalpatel2106.emoji_keyboard.internal.gifs.giphy;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by Keval on 16-Jun-17.
 *
 * @author {@link 'https://github.com/kevalpatel2106'}
 */

class CacheInterceptor implements Interceptor {
    static final int CACHE_SIZE = 5242880;          //5 MB //Cache size.
    private final int mCacheTimeMins;

    /**
     * Constructor.
     *
     * @param cacheTimeMins Caching time in seconds.
     */
    CacheInterceptor(int cacheTimeMins) {
        mCacheTimeMins = cacheTimeMins;
    }

    @Override
    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();

        CacheControl cacheControl = new CacheControl.Builder()
                .maxStale(mCacheTimeMins, TimeUnit.MINUTES)
                .maxAge(mCacheTimeMins, TimeUnit.MINUTES)
                .build();
        request = request.newBuilder()
                .header("Cache-Control", cacheControl.toString())   //Enable cache
                .build();
        return chain.proceed(request);
    }
}
