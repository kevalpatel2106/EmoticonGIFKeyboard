package com.kevalpatel2106.emoticongifkeyboard.emoticons.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;

/**
 * Created by Keval Patel on 22/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public final class EmoticonTextViewInternal extends AppCompatTextView {
    @NonNull
    private final Context mContext;
    private int mEmoticonSize;
    @Nullable
    private EmoticonProvider mEmoticonProvider;

    public EmoticonTextViewInternal(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
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

            //Check if the icon for this emoticon is available?
            int icon = mEmoticonProvider.getIcon(text.toString());
            if (icon > 0) {

                //Convert to spannable.
                Spannable spannable = new SpannableString(text);
                spannable.setSpan(new EmoticonSpan(mContext,
                                mEmoticonProvider.getIcon(text.toString()),
                                mEmoticonSize),
                        0,
                        spannable.length() - 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                text = spannable;
            }
        }
        super.setText(text, type);
    }

    public void setEmoticonProvider(@Nullable final EmoticonProvider emoticonProvider) {
        mEmoticonProvider = emoticonProvider;
    }
}