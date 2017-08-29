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
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.KeyEvent;

import com.kevalpatel2106.emoticongifkeyboard.EmoticonGIFKeyboardFragment;
import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;
import com.kevalpatel2106.emoticongifkeyboard.internal.emoticon.EmoticonUtils;

/**
 * Created by Keval Patel on 20/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public class EmoticonEditText extends AppCompatEditText {
    private static final String TAG = "EmoticonEditText";
    private int mEmoticonSize;
    @Nullable
    private EmoticonProvider mEmoticonProvider;

    public EmoticonEditText(final Context context) {
        super(context);
        mEmoticonSize = (int) getTextSize();
        setText(getText());
    }

    public EmoticonEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EmoticonEditText(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        @SuppressLint("CustomViewStyleable")
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Emoticon);
        mEmoticonSize = (int) a.getDimension(R.styleable.Emoticon_emojiconSize, getTextSize());
        a.recycle();

        setText(getText());
    }

    @Override
    @CallSuper
    public void setText(CharSequence rawText, BufferType type) {
        final CharSequence text = rawText == null ? "" : rawText;
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        if (mEmoticonProvider != null)
            EmoticonUtils.replaceWithImages(getContext(), spannableStringBuilder, mEmoticonProvider, mEmoticonSize);
        super.setText(text, type);
    }

    @Override
    @CallSuper
    public void append(CharSequence rawText, int start, int end) {
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getText().insert(start, rawText));
        if (mEmoticonProvider != null)
            EmoticonUtils.replaceWithImages(getContext(), spannableStringBuilder, mEmoticonProvider, mEmoticonSize);
        super.setText(spannableStringBuilder);
        setSelection(length());
    }

    /**
     * Remove the last character manually from the text. {@link EmoticonGIFKeyboardFragment}
     * is going to handle backspace manually by itself if the {@link EmoticonEditText} is in focus.
     * You should be implementing this method if you {@link EmoticonEditText} is not in focus and
     * backspace event occurs.
     */
    @CallSuper
    public void backspace() {
        final KeyEvent event = new KeyEvent(0, 0, 0,
                KeyEvent.KEYCODE_DEL, 0, 0, 0, 0,
                KeyEvent.KEYCODE_ENDCALL);
        dispatchKeyEvent(event);
    }

    /**
     * Set the size of emojicon in pixels.
     */
    @CallSuper
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
    @CallSuper
    public void setEmoticonProvider(@Nullable final EmoticonProvider emoticonProvider) {
        mEmoticonProvider = emoticonProvider;

        //Refresh the emoticon icons
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getText());
        if (mEmoticonProvider != null)
            EmoticonUtils.replaceWithImages(getContext(), spannableStringBuilder, mEmoticonProvider, mEmoticonSize);
    }
}
