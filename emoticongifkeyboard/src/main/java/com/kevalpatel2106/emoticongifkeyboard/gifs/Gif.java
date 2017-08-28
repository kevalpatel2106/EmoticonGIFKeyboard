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

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Keval Patel on 18/08/17.
 * GIF POJO.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

@SuppressWarnings("WeakerAccess")
public final class Gif implements Parcelable {

    public static final Parcelable.Creator<Gif> CREATOR = new Parcelable.Creator<Gif>() {
        @Override
        public Gif createFromParcel(Parcel in) {
            return new Gif(in);
        }

        @Override
        public Gif[] newArray(int size) {
            return new Gif[size];
        }
    };

    /**
     * Original GIF image url.
     */
    @NonNull
    private final String gifUrl;

    /**
     * Preview GIF url to use as thumbnail.
     */
    @Nullable
    private final String previewGifUrl;

    /**
     * MP4 video url for the image.
     */
    @Nullable
    private final String mp4Url;

    /**
     * Public construction.
     *
     * @param gifUrl        Full scale GIF URL.
     * @param previewGifUrl Preview scale GIF URL.
     * @param mp4Url MP4 video url for the image.
     */
    @SuppressWarnings("ConstantConditions")
    public Gif(@NonNull String gifUrl, @Nullable String previewGifUrl, @Nullable String mp4Url) {
        if (gifUrl == null) throw new IllegalArgumentException("GIF url cannot be null.");

        this.gifUrl = gifUrl;
        this.previewGifUrl = previewGifUrl;
        this.mp4Url = mp4Url;
    }

    /**
     * Public construction.
     *
     * @param gifUrl Full scale GIF URL.
     */
    @SuppressWarnings("ConstantConditions")
    public Gif(@NonNull String gifUrl) {
        if (gifUrl == null) throw new IllegalArgumentException("GIF url cannot be null.");

        this.gifUrl = gifUrl;
        this.previewGifUrl = null;
        this.mp4Url = null;
    }

    /**
     * Constructor for parcelable object.
     */
    public Gif(Parcel in) {
        this.previewGifUrl = in.readString();
        this.gifUrl = in.readString();
        this.mp4Url = in.readString();

        //noinspection ConstantConditions
        if (gifUrl == null) throw new IllegalArgumentException("GIF url cannot be null.");
    }

    /**
     * Get the Url of the preview GIF. If there is no preview url for the GIF, this will return
     * full scale GIF url.
     *
     * @return URL of the preview scale GIF.
     */
    @NonNull
    public String getPreviewGifUrl() {
        return previewGifUrl == null ? gifUrl : previewGifUrl;
    }

    /**
     * @return Full scale GIF URL.
     */
    @NonNull
    public String getGifUrl() {
        return gifUrl;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof Gif && ((Gif) obj).gifUrl.equals(gifUrl));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(previewGifUrl);
        dest.writeString(gifUrl);
        dest.writeString(mp4Url);
    }

    @Override
    public int hashCode() {
        return gifUrl.hashCode();
    }
}
