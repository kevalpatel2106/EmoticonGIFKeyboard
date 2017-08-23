package com.kevalpatel2106.emoticongifkeyboard.gifs.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
    public GifViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GifViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_gif_search, parent, false));
    }

    @Override
    public void onBindViewHolder(GifViewHolder holder, int position) {
        final Gif gif = mData.get(position);
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
