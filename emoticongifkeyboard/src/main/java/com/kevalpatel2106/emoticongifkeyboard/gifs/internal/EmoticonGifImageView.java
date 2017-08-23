package com.kevalpatel2106.emoticongifkeyboard.gifs.internal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.kevalpatel2106.emoticongifkeyboard.R;

/**
 * Created by Keval on 19-Aug-17.
 *
 * @author <a href='https://github.com/kevalpatel2106'>Kevalpatel2106</a>
 */

public final class EmoticonGifImageView extends AppCompatImageView {

    @ColorInt
    private int mAccentColor;

    @ColorInt
    private int mAccentDarkColor;

    public EmoticonGifImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @ColorInt
    public static int getDarkAccentColor(final int color) {
        final float factor = 0.6f;

        final int a = Color.alpha(color);
        final int r = Math.round(Color.red(color) * factor);
        final int g = Math.round(Color.green(color) * factor);
        final int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a,
                Math.min(r, 255),
                Math.min(g, 255),
                Math.min(b, 255));
    }

    private void init(@NonNull Context context) {
        mAccentColor = getAccentColor(context);
        mAccentDarkColor = getDarkAccentColor(mAccentColor);

        //Set on touch listener to change the tint color when image is pressed.
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setColorFilter(motionEvent.getAction() == MotionEvent.ACTION_DOWN ?
                        mAccentDarkColor : mAccentColor, PorterDuff.Mode.SRC_ATOP);
                return false;
            }
        });

        //Set the icon color to accent color
        setColorFilter(mAccentColor, PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        setColorFilter(selected ? mAccentDarkColor : mAccentColor, PorterDuff.Mode.SRC_ATOP);
    }

    @ColorInt
    public int getAccentColor(final Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
        return value.data;
    }

}
