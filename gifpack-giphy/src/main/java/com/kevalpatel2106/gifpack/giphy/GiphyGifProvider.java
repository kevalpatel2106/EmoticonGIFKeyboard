package com.kevalpatel2106.gifpack.giphy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kevalpatel2106.emoticongifkeyboard.gifs.Gif;
import com.kevalpatel2106.emoticongifkeyboard.gifs.GifProviderProtocol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Keval on 19-Aug-17.
 *
 * @author <a href='https://github.com/kevalpatel2106'>Kevalpatel2106</a>
 */

public class GiphyGifProvider implements GifProviderProtocol {
    private final String mApiKey;
    private final Cache mCache;

    @SuppressWarnings("ConstantConditions")
    private GiphyGifProvider(@NonNull Context context, @NonNull String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) throw new RuntimeException("Invalid GIPHY key.");

        mApiKey = apiKey;

        //Define mCache
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        mCache = new Cache(httpCacheDirectory, CacheInterceptor.CACHE_SIZE);
    }

    public static GiphyGifProvider create(@NonNull Context context, @NonNull String apiKey) {
        return new GiphyGifProvider(context, apiKey);
    }

    /**
     * Get the string from the input stream.
     *
     * @param inputStream {@link InputStream} to read.
     * @return String.
     * @throws IOException - If unable to read
     */
    @Nullable
    private static String getStringFromStream(@NonNull InputStream inputStream) throws IOException {
        //noinspection ConstantConditions
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) total.append(line).append('\n');
        return total.toString();
    }

    /**
     * Load the trending GIFs list.
     *
     * @param limit Number of GIFs.
     * @return List of all the {@link Gif} or null of the error occurs.
     */
    @Nullable
    @Override
    public List<Gif> getTrendingGifs(int limit) {
        try {
            Response<ResponseBody> responseBody = new Retrofit.Builder()
                    .baseUrl(GiphyApiService.GIPHY_BASE_URL)
                    .client(new OkHttpClient.Builder()
                            .addNetworkInterceptor(new CacheInterceptor(5))
                            .cache(mCache)
                            .build())
                    .build()
                    .create(GiphyApiService.class)
                    .getTrending(mApiKey, limit, "json")
                    .execute();


            //Check if the response okay?
            if (responseBody.code() == 200 && responseBody.body() != null) {
                //noinspection ConstantConditions
                String response = getStringFromStream(responseBody.body().byteStream());
                if (response == null) return null;

                JSONArray data = new JSONObject(response).getJSONArray("data");
                ArrayList<Gif> gifs = new ArrayList<>();
                for (int i = 0; i < data.length(); i++) {
                    JSONObject images = data.getJSONObject(i).getJSONObject("images");
                    Gif gif = new Gif(images.getJSONObject("original").getString("url"),
                            images.has("preview_gif") ? images.getJSONObject("preview_gif").getString("url") : null,
                            images.has("original_mp4") ? images.getJSONObject("original_mp4").getString("mp4") : null);
                    gifs.add(gif);
                }
                return gifs;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
