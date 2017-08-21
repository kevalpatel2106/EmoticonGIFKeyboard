package com.kevalpatel2106.emoticongifkeyboard.gifs.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.gifs.Gif;

import java.util.List;

/**
 * Created by Keval on 18-Aug-17.
 */

final class GifGridAdapter extends ArrayAdapter<Gif> {
    private final Context mContext;
    private List<Gif> mData;

    GifGridAdapter(@NonNull final Context context,
                   @NonNull final List<Gif> data) {
        super(context, R.layout.item_emojicon, data);
        mContext = context;
        mData = data;
    }

    @Nullable
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

        Gif gif = getItem(position);
        if (gif != null) {
            Glide.with(mContext)
                    .load(gif.getPreviewGifUrl())
                    .asGif()
                    .crossFade()
                    .centerCrop()
                    .into(holder.gifIv);
        }
        return v;
    }

    private class ViewHolder {
        private AppCompatImageView gifIv;
    }
}
