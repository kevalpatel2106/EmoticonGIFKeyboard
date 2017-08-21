package com.kevalpatel2106.emoticongifkeyboard.internal.emoticons;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevalpatel2106.emoticongifkeyboard.R;

import java.util.List;

/**
 * Created by Keval on 18-Aug-17.
 */

final class EmoticonAdapter extends RecyclerView.Adapter<EmoticonAdapter.ViewHolder> {
    private final Context mContext;
    private final List<Emoticon> mData;

    @NonNull
    private final ItemSelectListener mListener;

    EmoticonAdapter(@NonNull Context context,
                    @NonNull List<Emoticon> data,
                    @NonNull ItemSelectListener listener) {
        mContext = context;
        mData = data;
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
                    mListener.OnEmoticonSelected(emoji);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    interface ItemSelectListener {
        void OnEmoticonSelected(@NonNull Emoticon emoticon);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView icon;

        ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.emojicon_icon);
        }
    }
}
