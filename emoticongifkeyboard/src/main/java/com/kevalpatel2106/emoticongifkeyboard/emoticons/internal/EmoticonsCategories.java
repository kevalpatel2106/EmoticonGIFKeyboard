package com.kevalpatel2106.emoticongifkeyboard.emoticons.internal;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Keval on 18-Aug-17.
 * This class has the list of all the emoticons categories.
 *
 * @author <a href='https://github.com/kevalpatel2106'>Kevalpatel2106</a>
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
    static final int FLAGS = 8;

    @EmoticonsCategory
    static int getCategoryFromCategoryName(String name) {
        switch (name) {
            case "People":
            case "Faces":
            case "Gestures":
                return PEOPLE;
            case "Nature":
            case "Cosmos":
                return NATURE;
            case "Foods":
                return FOOD;
            case "Flags":
                return FLAGS;
            case "Symbols":
                return SYMBOLS;
            case "Objects":
                return ELECTRIC;
            case "Activity":
                return SPORT;
            case "Places":
            case "Transportation":
                return CARS;
            default:
                return SYMBOLS;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({RECENT, PEOPLE, NATURE, FOOD, SPORT, CARS, ELECTRIC, SYMBOLS, FLAGS})
    @interface EmoticonsCategory {
    }
}
