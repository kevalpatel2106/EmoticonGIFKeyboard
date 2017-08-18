package com.kevalpatel2106.emoji_keyboard.internal.emoticons;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Keval on 18-Aug-17.
 */

final class EmoticonsCategories {
    static final int RECENT = 0;
    static final int PEOPLE = 1;
    static final int NATURE = 2;
    static final int FOOD = 3;
    static final int SPORT = 4;
    static final int CARS = 5;
    static final int ELECTRIC = 6;
    static final int SYMBOLS = 7;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({RECENT, PEOPLE, NATURE, FOOD, SPORT, CARS, ELECTRIC, SYMBOLS})
    @interface EmoticonsCategory {
    }
}
