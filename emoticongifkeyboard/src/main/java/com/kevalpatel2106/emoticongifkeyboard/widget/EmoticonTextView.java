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

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.internal.EmoticonUtils;

/**
 * Created by Keval Patel on 20/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public class EmoticonTextView extends AppCompatTextView {
    @NonNull
    private final Context mContext;
    private int mEmoticonSize;
    @Nullable
    private EmoticonProvider mEmoticonProvider;

    public EmoticonTextView(@NonNull Context context) {
        super(context);
        mContext = context;
        mEmoticonSize = (int) getTextSize();
        init(null);
    }

    public EmoticonTextView(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    public EmoticonTextView(@NonNull Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init(attrs);
    }

    private void init(@Nullable final AttributeSet attrs) {
        if (attrs == null) {
            mEmoticonSize = (int) getTextSize();
        } else {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Emoticon);
            mEmoticonSize = (int) a.getDimension(R.styleable.Emoticon_emojiconSize, getTextSize());
            a.recycle();
        }
        setText(getText());
    }

    @Override
    public void setText(CharSequence text, TextView.BufferType type) {
        if (mEmoticonProvider != null && !TextUtils.isEmpty(text)) {
            text = EmoticonUtils.replaceWithImages(mContext,
                    new SpannableString(text),
                    mEmoticonProvider,
                    mEmoticonSize);


//            int icon = mEmoticonProvider.getIcon(text.toString()).getIcon();
//            if (icon > 0) {
//                Spannable spanable = new SpannableString(text);
//                spanable.setSpan(new EmoticonSpan(mContext, mEmoticonProvider.getIcon(text.toString()).getIcon(), mEmoticonSize),
//                        0,
//                        text.length() - 1,
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//                text = spanable;
//            }
        }
        super.setText(text, type);
    }

    /**
     * Set the size of emojicon in pixels.
     */
    public void setEmoticonSize(final int pixels) {
        mEmoticonSize = pixels;
        super.setText(getText());
    }

    public void setEmoticonProvider(@Nullable final EmoticonProvider emoticonProvider) {
        mEmoticonProvider = emoticonProvider;
    }
}