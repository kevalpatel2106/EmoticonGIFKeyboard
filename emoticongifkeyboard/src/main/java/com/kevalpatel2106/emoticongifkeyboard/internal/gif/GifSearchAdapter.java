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

package com.kevalpatel2106.emoticongifkeyboard.internal.gif;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.gifs.Gif;

import java.util.List;

/**
 * Created by Keval Patel on 22/08/17.
 * Adapter to display GIFs in the Recycler view.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

final class GifSearchAdapter extends RecyclerView.Adapter<GifSearchAdapter.GifViewHolder> {
    @NonNull
    private final Context mContext;
    @NonNull
    private final List<Gif> mData;
    @NonNull
    private final ItemSelectListener mListener;

    /**
     * @param context  Instance
     * @param data     List of {@link Gif}
     * @param listener {@link GifGridAdapter.ItemSelectListener} to get callback.
     */
    GifSearchAdapter(@NonNull final Context context,
                     @NonNull final List<Gif> data,
                     @NonNull final ItemSelectListener listener) {
        //noinspection ConstantConditions
        if (context == null || data == null || listener == null)
            throw new IllegalArgumentException("Null parameters not allowed.");

        mContext = context;
        mData = data;
        mListener = listener;
    }

    @Override
    public GifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GifViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_gif_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GifViewHolder holder, int position) {
        final Gif gif = mData.get(position);
        if (gif != null) {
            Glide.with(mContext)
                    .load(gif.getPreviewGifUrl())
                    .apply(new RequestOptions().centerCrop())
                    .into(holder.gifIv);

            holder.gifIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.OnListItemSelected(gif);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * Callback listener to get notify when item is clicked.
     */
    interface ItemSelectListener {
        void OnListItemSelected(@NonNull Gif gif);
    }

    /**
     * Recycler view holder.
     */
    class GifViewHolder extends RecyclerView.ViewHolder {
        /**
         * Image view to display GIFs.
         */
        ImageView gifIv;

        GifViewHolder(View itemView) {
            super(itemView);
            gifIv = itemView.findViewById(R.id.gif_iv);
        }
    }
}
