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

package com.kevalpatel2106.emoticongifkeyboard.emoticons.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.text.style.ImageSpan;

/**
 * Created by Keval Patel on 20/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 * @see <a href='https://github.com/rockerhieu/emojicon/blob/master/library/src/main/java/io/github/rockerhieu/emojicon/EmojiconSpan.java>EmojiconSpan.java</a>
 */

final class EmoticonSpan extends ImageSpan {
    private final float size;

    EmoticonSpan(final Context context,
                 @DrawableRes final int drawableRes,
                 final float size) {
        super(context, drawableRes);

        this.size = size;
    }

    @Override
    public Drawable getDrawable() {
        final Drawable result = super.getDrawable();

        result.setBounds(0, 0, (int) size, (int) size);

        return result;
    }

    @Override
    public int getSize(final Paint paint, final CharSequence text, final int start,
                       final int end, final Paint.FontMetricsInt fontMetrics) {
        if (fontMetrics != null) {
            final Paint.FontMetrics paintFontMetrics = paint.getFontMetrics();
            final float fontHeight = paintFontMetrics.descent - paintFontMetrics.ascent;
            final float centerY = paintFontMetrics.ascent + fontHeight / 2;

            fontMetrics.ascent = (int) (centerY - size / 2);
            fontMetrics.top = fontMetrics.ascent;
            fontMetrics.bottom = (int) (centerY + size / 2);
            fontMetrics.descent = fontMetrics.bottom;
        }

        return (int) size;
    }

    @Override
    public void draw(final Canvas canvas, final CharSequence text, final int start,
                     final int end, final float x, final int top, final int y,
                     final int bottom, final Paint paint) {
        final Drawable drawable = getDrawable();
        final Paint.FontMetrics paintFontMetrics = paint.getFontMetrics();
        final float fontHeight = paintFontMetrics.descent - paintFontMetrics.ascent;
        final float centerY = y + paintFontMetrics.descent - fontHeight / 2;
        final float transitionY = centerY - size / 2;

        canvas.save();
        canvas.translate(x, transitionY);
        drawable.draw(canvas);
        canvas.restore();
    }
}
