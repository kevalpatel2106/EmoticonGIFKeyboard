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

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kevalpatel2106.emoticongifkeyboard.emoticons.Emoticon;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by Keval on 21-Aug-17.
 * This class implements {@link SQLiteAssetHelper} to load the emoticons list database from assets/database
 * folder. This database is pre-loaded in the library with three tables:
 * <li>All emoticons list with unicode and name of emoticon.</li>
 * <li>List of all emoticons variant.</li>
 * <li>List of all search tags with the respective unicode.</li>
 *
 * @author 'https://github.com/kevalpatel2106'
 * @see SQLiteAssetHelper
 */

final class EmoticonDbHelper extends SQLiteAssetHelper {

    /**
     * Name of the database.
     */
    private static final String DB_NAME = "emoticon.db";

    /**
     * SQL database version.
     */
    private static final int DB_VERSION = 1;

    /**
     * Public constructor.
     *
     * @param context Instance of caller.
     */
    EmoticonDbHelper(@NonNull Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        //Force update the whole database whenever the new db version is shipped in assets folder.
        setForcedUpgrade();
    }

    /**
     * Get all the emoticons from the database. If the icon for the emoticon is supported by the emoticon
     * provider then, that emoticon will be added to list.
     *
     * @param category         Category for which emoticon is to load.
     * @param emoticonProvider {@link EmoticonProvider} or null for system emoticons.
     * @return List of all the supported emoticons for given emoticon icon pack.
     * @see com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider#hasEmoticonIcon(String)
     */
    @NonNull
    ArrayList<Emoticon> getEmoticons(@EmoticonsCategories.EmoticonsCategory final int category,
                                     @Nullable EmoticonProvider emoticonProvider) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(EmoticonColumns.TABLE,
                new String[]{EmoticonColumns.UNICODE, EmoticonColumns.CATEGORY},
                EmoticonColumns.CATEGORY + "=?", new String[]{category + ""},
                null, null,
                EmoticonColumns.ID + " ASC");

        ArrayList<Emoticon> emoticons = new ArrayList<>();
        while (cursor.moveToNext()) {
            String unicode = cursor.getString(cursor.getColumnIndex(EmoticonColumns.UNICODE));

            //Check if there is icon available to display for custom emoticon page.
            if (emoticonProvider == null || emoticonProvider.hasEmoticonIcon(unicode))
                emoticons.add(new Emoticon(unicode));
        }
        cursor.close();
        sqLiteDatabase.close();
        return emoticons;
    }

    /**
     * Get all the emoticons that matches has the search tag starting from search query.
     * If the icon for the emoticon is supported by the emoticon provider then, that emoticon will
     * be added to list.
     *
     * @param searchQuery      Search string.
     * @param emoticonProvider {@link EmoticonProvider} or null for system emoticons.
     * @return List of all the supported emoticons for given emoticon icon pack.
     * @see com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider#hasEmoticonIcon(String)
     */
    @NonNull
    ArrayList<Emoticon> searchEmoticons(@NonNull final String searchQuery,
                                        @Nullable EmoticonProvider emoticonProvider) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(EmoticonTagsColumns.TABLE,
                new String[]{EmoticonTagsColumns.UNICODE},      //Unicode.
                EmoticonTagsColumns.TAG + " LIKE ?", new String[]{searchQuery.trim() + "%"}, //Search for the tag
                null, null, null);

        ArrayList<Emoticon> emoticons = new ArrayList<>();
        while (cursor.moveToNext()) {
            String unicode = cursor.getString(cursor.getColumnIndex(EmoticonTagsColumns.UNICODE));

            //Check if there is icon available to display for custom emoticon page.
            if (emoticonProvider == null || emoticonProvider.hasEmoticonIcon(unicode)) {
                Emoticon emoticon = new Emoticon(unicode);
                if (!emoticons.contains(emoticon))  //Prevent duplicates.
                    emoticons.add(new Emoticon(unicode));
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return emoticons;
    }

    /**
     * Emoticons table column list.
     */
    @SuppressWarnings("unused")
    private static class EmoticonColumns {
        /**
         * Table name
         */
        private static final String TABLE = "emoticon";

        /**
         * Primary integer key.
         */
        private static final String ID = "_id";

        /**
         * Unicode value of {@link Emoticon}.
         *
         * @see Emoticon#unicode
         */
        private static final String UNICODE = "unicode";

        /**
         * Category of the {@link Emoticon}. The field will have integer from supported categories.
         * List of supported categories: {@link EmoticonsCategories}.
         *
         * @see EmoticonsCategories
         */
        private static final String CATEGORY = "category";

        /**
         * Name of the {@link Emoticon}. This field will be removed in future releases.
         */
        @Deprecated
        private static final String NAME = "name";
    }

    /**
     * Emoticon variants table column list.
     */
    @SuppressWarnings("unused")
    private static class EmoticonVariantColumns {
        /**
         * Table name
         */
        private static final String TABLE = "emoticon_variant";

        /**
         * Primary integer key.
         */
        private static final String ID = "variant_id";

        /**
         * Unicode value of {@link Emoticon}.
         *
         * @see Emoticon#unicode
         */
        private static final String UNICODE = "variant_unicode";
        /**
         * Category of the {@link Emoticon}. The field will have integer from supported categories.
         * List of supported categories: {@link EmoticonsCategories}.
         *
         * @see EmoticonsCategories
         */
        private static final String CATEGORY = "variant_category";

        /**
         * Unicode of the root/main emoticon.
         */
        private static final String ROOT_UNICODE = "variant_root_unicode";

        /**
         * Name of the {@link Emoticon}. This field will be removed in future releases.
         */
        @Deprecated
        private static final String NAME = "variant_name";
    }

    /**
     * Emoticons tags table column list.
     */
    @SuppressWarnings("unused")
    private static class EmoticonTagsColumns {
        /**
         * Table name
         */
        private static final String TABLE = "emoticon_tags";

        /**
         * Primary integer key.
         */
        private static final String ID = "tags_id";

        /**
         * Unicode value of {@link Emoticon}.
         *
         * @see Emoticon#unicode
         */
        private static final String UNICODE = "tags_unicode";

        /**
         * Search tag to search the emoticon based on search query.
         */
        private static final String TAG = "tags_tags";
    }
}
