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

package com.kevalpatel2106.emoticongifkeyboard.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;
import com.kevalpatel2106.emoticongifkeyboard.internal.emoticon.EmoticonUtils;

/**
 * Created by Keval Patel on 20/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public class EmoticonEditText extends AppCompatEditText {
    private final Context mContext;
    private int mEmoticonSize;
    @Nullable
    private EmoticonProvider mEmoticonProvider;

    public EmoticonEditText(final Context context) {
        super(context);
        mContext = context;
        mEmoticonSize = (int) getTextSize();
    }

    public EmoticonEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    public EmoticonEditText(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        @SuppressLint("CustomViewStyleable")
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Emoticon);
        mEmoticonSize = (int) a.getDimension(R.styleable.Emoticon_emojiconSize, getTextSize());
        a.recycle();

        //Set text for the first time
        setText(getText());
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (mEmoticonProvider != null && !TextUtils.isEmpty(text)) {
            text = EmoticonUtils.replaceWithImages(mContext,
                    new SpannableString(text),
                    mEmoticonProvider,
                    mEmoticonSize);
        }
        super.setText(text);
    }

    /**
     * Set the size of emojicon in pixels.
     */
    public void setEmoticonSize(final int pixels) {
        mEmoticonSize = pixels;
        setText(getText());
    }

    /**
     * Set {@link EmoticonProvider} to display custom emoticon icons.
     *
     * @param emoticonProvider {@link EmoticonProvider} of custom icon packs or null to display
     *                         system icons.
     */
    public void setEmoticonProvider(@Nullable final EmoticonProvider emoticonProvider) {
        mEmoticonProvider = emoticonProvider;
    }
}
