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

package com.kevalpatel2106.emoticongifkeyboard.gifs.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.gifs.Gif;

import java.util.List;

/**
 * Created by Keval on 18-Aug-17.
 * Adapter to display Trending GIFs in the GridView.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

final class GifGridAdapter extends ArrayAdapter<Gif> {
    @NonNull
    private final Context mContext;
    @NonNull
    private final ItemSelectListener mListener;
    @NonNull
    private final List<Gif> mData;

    /**
     * @param context  Instance
     * @param data     List of {@link Gif}
     * @param listener {@link ItemSelectListener} to get callback.
     */
    GifGridAdapter(@NonNull final Context context,
                   @NonNull final List<Gif> data,
                   @NonNull final ItemSelectListener listener) {
        super(context, R.layout.item_emojicon, data);
        //noinspection ConstantConditions
        if (context == null || data == null || listener == null)
            throw new IllegalArgumentException("Null parameters not allowed.");

        mContext = context;
        mData = data;
        mListener = listener;
    }

    @Override
    public Gif getItem(int position) {
        return mData.get(position);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(R.layout.item_gif, parent, false);

            holder = new ViewHolder();
            holder.gifIv = v.findViewById(R.id.gif_iv);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        final Gif gif = getItem(position);
        if (gif != null) {
            Glide.with(mContext)
                    .load(gif.getPreviewGifUrl())
                    .asGif()
                    .crossFade()
                    .centerCrop()
                    .into(holder.gifIv);

            holder.gifIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.OnListItemSelected(gif);
                }
            });
        }
        return v;
    }

    /**
     * Callback listener to get notify when item is clicked.
     */
    interface ItemSelectListener {
        void OnListItemSelected(@NonNull Gif gif);
    }

    /**
     * View holder class to cache views.
     */
    private class ViewHolder {

        /**
         * Image view to display GIFs.
         */
        private ImageView gifIv;
    }
}
