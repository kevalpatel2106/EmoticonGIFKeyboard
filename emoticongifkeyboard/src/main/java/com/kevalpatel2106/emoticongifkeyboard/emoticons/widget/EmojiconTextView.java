package com.kevalpatel2106.emoticongifkeyboard.emoticons.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
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

public class EmojiconTextView extends AppCompatTextView {
    private int mEmojiconSize;
    private EmoticonProvider mEmoticonProvider;
    private Context mContext;

    public EmojiconTextView(Context context) {
        super(context);
        mContext = context;
        mEmojiconSize = (int) getTextSize();
        init(null);
    }

    public EmojiconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    public EmojiconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) {
            mEmojiconSize = (int) getTextSize();
        } else {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Emojicon);
            mEmojiconSize = (int) a.getDimension(R.styleable.Emojicon_emojiconSize, getTextSize());
            a.recycle();
        }
        setText(getText());
    }

    @Override
    public void setText(CharSequence text, TextView.BufferType type) {
        if (mEmoticonProvider != null && !TextUtils.isEmpty(text))
            text = EmoticonUtils.replaceWithImages(mContext,
                    new SpannableString(text),
                    mEmoticonProvider,
                    mEmojiconSize);
        super.setText(text, type);
    }

    /**
     * Set the size of emojicon in pixels.
     */
    public void setEmojiconSize(int pixels) {
        mEmojiconSize = pixels;
        super.setText(getText());
    }

    public void setEmoticonProvider(@NonNull EmoticonProvider emoticonProvider) {
        mEmoticonProvider = emoticonProvider;
    }
}