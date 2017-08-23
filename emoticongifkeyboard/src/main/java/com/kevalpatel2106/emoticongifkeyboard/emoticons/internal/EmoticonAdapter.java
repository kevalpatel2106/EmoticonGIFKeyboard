package com.kevalpatel2106.emoticongifkeyboard.emoticons.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.Emoticon;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;

import java.util.List;

/**
 * Created by Keval on 18-Aug-17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

final class EmoticonAdapter extends RecyclerView.Adapter<EmoticonAdapter.ViewHolder> {

    @NonNull
    private final Context mContext;
    @NonNull
    private final List<Emoticon> mData;
    @Nullable
    private final EmoticonProvider mEmoticonProvider;
    @NonNull
    private final ItemSelectListener mListener;

    EmoticonAdapter(@NonNull Context context,
                    @NonNull List<Emoticon> data,
                    @Nullable EmoticonProvider emoticonProvider,
                    @NonNull ItemSelectListener listener) {
        //noinspection ConstantConditions
        if (context == null || data == null || listener == null)
            throw new IllegalArgumentException("Null parameters not allowed.");

        mContext = context;
        mData = data;
        mEmoticonProvider = emoticonProvider;
        mListener = listener;
    }

    @Override
    public EmoticonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_emojicon, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Emoticon emoji = mData.get(position);
        if (emoji != null) {
            holder.icon.setText(emoji.getUnicode());
            holder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.OnListItemSelected(emoji);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    interface ItemSelectListener {
        void OnListItemSelected(@NonNull Emoticon emoticon);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        EmoticonTextViewInternal icon;

        ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.emojicon_icon);
            icon.setEmoticonProvider(mEmoticonProvider);
        }
    }
}
