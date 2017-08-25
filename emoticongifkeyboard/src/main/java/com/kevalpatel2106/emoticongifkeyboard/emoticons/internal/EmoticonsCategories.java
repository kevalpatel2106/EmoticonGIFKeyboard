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

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Keval on 18-Aug-17.
 * This class has the list of all the emoticons categories.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

final class EmoticonsCategories {
    static final int RECENT = 0;
    static final int PEOPLE = 1;
    static final int NATURE = 2;
    static final int FOOD = 3;
    static final int ACTIVITY = 4;
    static final int TRAVEL = 5;
    static final int OBJECTS = 6;
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
                return OBJECTS;
            case "Activity":
                return ACTIVITY;
            case "Places":
            case "Transportation":
                return TRAVEL;
            default:
                return SYMBOLS;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({RECENT, PEOPLE, NATURE, FOOD, ACTIVITY, TRAVEL, OBJECTS, SYMBOLS, FLAGS})
    @interface EmoticonsCategory {
    }
}
