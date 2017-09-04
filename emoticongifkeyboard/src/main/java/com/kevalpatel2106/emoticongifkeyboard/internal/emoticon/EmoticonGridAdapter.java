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

package com.kevalpatel2106.emoticongifkeyboard.internal.emoticon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.Emoticon;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;

import java.util.List;

/**
 * Created by Keval on 01-Sep-17.
 * An adapter to bind the {@link Emoticon} list with the grid view.
 *
 * @author <a href='https://github.com/kevalpatel2106'>Kevalpatel2106</a>
 */

final class EmoticonGridAdapter extends BaseAdapter {
    /**
     * Instance of caller.
     */
    @NonNull
    private final Context mContext;

    /**
     * {@link EmoticonProvider} to provide the custom icon. If the this field is null system
     * emoticon icons will render.
     */
    @Nullable
    private final EmoticonProvider mEmoticonProvider;

    /**
     * List of the {@link Emoticon} to display.
     */
    @NonNull
    private final List<Emoticon> mEmoticons;

    /**
     * Package private constructor.
     *
     * @param context          Instance of the caller.
     * @param emoticonProvider {@link EmoticonProvider} to provide the custom icon. If the this field is null system
     *                         emoticon icons will render.
     * @param emoticons        List of {@link Emoticon} to display.
     */
    EmoticonGridAdapter(@NonNull Context context,
                        @Nullable EmoticonProvider emoticonProvider,
                        @NonNull List<Emoticon> emoticons) {
        mContext = context;
        mEmoticonProvider = emoticonProvider;
        mEmoticons = emoticons;
    }

    @Override
    public int getCount() {
        return mEmoticons.size();
    }

    @Override
    public Emoticon getItem(int index) {
        return mEmoticons.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_emojicon,
                    viewGroup,
                    false);

        Emoticon emoticon = getItem(position);
        if (emoticon != null) {
            if (mEmoticonProvider != null
                    && mEmoticonProvider.hasEmoticonIcon(emoticon.getUnicode())) { //Check if the icon for this emoticon is available?

                //Convert to spannable.
                // Replace the emoticon image with provided by custom icon pack.
                Spannable spannable = new SpannableString(emoticon.getUnicode());
                spannable.setSpan(new EmoticonSpan(mContext,
                                mEmoticonProvider.getIcon(emoticon.getUnicode()),
                                mContext.getResources().getDimension(R.dimen.emoticon_grid_text_size)),
                        0,
                        spannable.length() - 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                ((TextView) convertView).setText(spannable);
            } else {
                ((TextView) convertView).setText(emoticon.getUnicode());
            }
        }
        return convertView;
    }
}
