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

package com.kevalpatel2106.emoticongifkeyboard.internal.emoticon;

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
    /**
     * Category of all the recently used emoticons.
     *
     * @see EmoticonRecentManager
     */
    static final int RECENT = 0;

    /**
     * Category for all face, hands and pearson emoticons.
     */
    static final int PEOPLE = 1;

    /**
     * Category for nature and animal emoticons.
     */
    static final int NATURE = 2;

    /**
     * Category for food emoticons.
     */
    static final int FOOD = 3;

    /**
     * Category for human activities and sport emoticons.
     */
    static final int ACTIVITY = 4;

    /**
     * Category for travel, places and vehicle emoticons.
     */
    static final int TRAVEL = 5;

    /**
     * Category for different object emoticons.
     */
    static final int OBJECTS = 6;

    /**
     * Category for symbols, arrow and other symbol.
     */
    static final int SYMBOLS = 7;

    /**
     * Category for flags.
     */
    static final int FLAGS = 8;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({RECENT, PEOPLE, NATURE, FOOD, ACTIVITY, TRAVEL, OBJECTS, SYMBOLS, FLAGS})
    @interface EmoticonsCategory {
    }
}
