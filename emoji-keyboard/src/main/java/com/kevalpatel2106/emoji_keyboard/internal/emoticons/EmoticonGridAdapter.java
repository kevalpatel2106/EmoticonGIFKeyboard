package com.kevalpatel2106.emoji_keyboard.internal.emoticons;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.kevalpatel2106.emoji_keyboard.R;
import com.kevalpatel2106.emoji_keyboard.internal.emoticons.emoji.Emoticon;
import com.kevalpatel2106.emoji_keyboard.views.EmojiconTextView;

import java.util.List;

/**
 * Created by Keval on 18-Aug-17.
 */

class EmoticonGridAdapter extends ArrayAdapter<Emoticon> {
    private final Context mContext;
    private List<Emoticon> mData;
    private boolean mUseSystemDefault = false;

    EmoticonGridAdapter(@NonNull Context context,
                        @NonNull List<Emoticon> data,
                        boolean useSystemDefault) {
        super(context, R.layout.item_emojicon, data);
        mContext = context;
        mData = data;
        mUseSystemDefault = useSystemDefault;
    }

    @Nullable
    @Override
    public Emoticon getItem(int position) {
        return mData.get(position);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(R.layout.item_emojicon, parent, false);

            holder = new ViewHolder();
            holder.icon = v.findViewById(R.id.emojicon_icon);
            holder.icon.setUseSystemDefault(mUseSystemDefault);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        Emoticon emoji = getItem(position);
        if (emoji != null) holder.icon.setText(emoji.getUnicode());
        return v;
    }

    private class ViewHolder {
        private EmojiconTextView icon;
    }
}
