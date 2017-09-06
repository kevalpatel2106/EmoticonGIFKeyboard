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

package com.kevalpatel2106.emoticongifkeyboard.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.kevalpatel2106.emoticongifkeyboard.R;

/**
 * Created by Keval on 19-Aug-17.
 * Custom {@link AppCompatImageView} to change the tint for the buttons when selected or click.
 * This class is for internal use only.
 *
 * @author <a href='https://github.com/kevalpatel2106'>Kevalpatel2106</a>
 */

public final class EmoticonGifImageView extends AppCompatImageView {

    @ColorInt
    private int mAccentColor;

    @ColorInt
    private int mAccentDarkColor;

    public EmoticonGifImageView(@NonNull Context context,
                                @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Get the dark color.
     *
     * @param color Original color.
     * @return Dark color.
     */
    @ColorInt
    private static int getDarkColor(final int color) {
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

    @SuppressLint("ClickableViewAccessibility")
    private void init(@NonNull Context context) {
        mAccentColor = getAccentColor(context);
        mAccentDarkColor = getDarkColor(mAccentColor);

        //Set on touch listener to change the tint color when image is pressed.
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                EmoticonGifImageView.this.setColorFilter(motionEvent.getAction() == MotionEvent.ACTION_DOWN ?
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

        //If the image is selected, display dark tint.
        setColorFilter(selected ? mAccentDarkColor : mAccentColor, PorterDuff.Mode.SRC_ATOP);
    }

    /**
     * Extract the accent color from the application theme.
     *
     * @param context {@link Context}
     * @return Accent color.
     */
    @ColorInt
    public int getAccentColor(@NonNull final Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
        return value.data;
    }
}
