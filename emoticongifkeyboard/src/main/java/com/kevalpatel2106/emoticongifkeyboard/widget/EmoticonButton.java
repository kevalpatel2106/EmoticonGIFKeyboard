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
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.TextView;

import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;
import com.kevalpatel2106.emoticongifkeyboard.internal.emoticon.EmoticonUtils;

/**
 * Created by Keval Patel on 20/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public class EmoticonButton extends AppCompatButton {
    @NonNull
    private final Context mContext;
    private int mEmoticonSize;
    @Nullable
    private EmoticonProvider mEmoticonProvider;

    public EmoticonButton(@NonNull Context context) {
        super(context);
        mContext = context;
        mEmoticonSize = (int) getTextSize();
        init(null);
    }

    public EmoticonButton(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    public EmoticonButton(@NonNull Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init(attrs);
    }

    private void init(@Nullable final AttributeSet attrs) {
        if (attrs == null) {
            mEmoticonSize = (int) getTextSize();
        } else {
            @SuppressLint("CustomViewStyleable")
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Emoticon);
            mEmoticonSize = (int) a.getDimension(R.styleable.Emoticon_emojiconSize, getTextSize());
            a.recycle();
        }
        setText(getText());
    }

    @Override
    @CallSuper
    public void setText(CharSequence rawText, TextView.BufferType type) {
        final CharSequence text = rawText == null ? "" : rawText;
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        if (mEmoticonProvider != null)
            EmoticonUtils.replaceWithImages(getContext(), spannableStringBuilder, mEmoticonProvider, mEmoticonSize);
        super.setText(spannableStringBuilder, type);
    }

    @Override
    @Deprecated
    @CallSuper
    public void append(CharSequence rawText, int start, int end) {
        final String text = getText() + (rawText == null ? "" : rawText).toString();
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        if (mEmoticonProvider != null)
            EmoticonUtils.replaceWithImages(getContext(), spannableStringBuilder, mEmoticonProvider, mEmoticonSize);
        super.setText(spannableStringBuilder);
    }

    /**
     * Set the size of emojicon in pixels.
     */
    @CallSuper
    public void setEmoticonSize(final int pixels) {
        mEmoticonSize = pixels;
        super.setText(getText());
    }

    /**
     * Set {@link EmoticonProvider} to display custom emoticon icons.
     *
     * @param emoticonProvider {@link EmoticonProvider} of custom icon packs or null to display
     *                         system icons.
     */
    @CallSuper
    public void setEmoticonProvider(@Nullable final EmoticonProvider emoticonProvider) {
        mEmoticonProvider = emoticonProvider;

        //Refresh the emoticon icons
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getText());
        if (mEmoticonProvider != null)
            EmoticonUtils.replaceWithImages(getContext(), spannableStringBuilder, mEmoticonProvider, mEmoticonSize);
    }
}