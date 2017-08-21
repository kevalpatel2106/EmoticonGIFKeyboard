package com.kevalpatel2106.emoticongifkeyboard.internal;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.kevalpatel2106.emoticongifkeyboard.R;

/**
 * Created by Keval on 19-Aug-17.
 *
 * @author <a href='https://github.com/kevalpatel2106'>Kevalpatel2106</a>
 */

public class EmoticonGifImageView extends AppCompatImageView {
    private Context mContext;

    public EmoticonGifImageView(Context context) {
        super(context);
        init(context);
    }

    public EmoticonGifImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EmoticonGifImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@NonNull Context context) {
        mContext = context;

        //Set on touch listener to change the tint color when image is pressed.
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setColorFilter(motionEvent.getAction() == MotionEvent.ACTION_DOWN ?
                                ContextCompat.getColor(mContext, R.color.icon_selected)
                                : ContextCompat.getColor(mContext, R.color.icon),
                        PorterDuff.Mode.SRC_ATOP);
                return false;
            }
        });
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        setColorFilter(selected ?
                        ContextCompat.getColor(mContext, R.color.icon_selected)
                        : ContextCompat.getColor(mContext, R.color.icon),
                PorterDuff.Mode.SRC_ATOP);
    }
}
