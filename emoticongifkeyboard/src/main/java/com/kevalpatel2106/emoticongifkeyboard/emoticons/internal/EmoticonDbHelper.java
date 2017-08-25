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
 *
 * @author 'https://github.com/kevalpatel2106'
 */

final class EmoticonDbHelper extends SQLiteAssetHelper {
    @SuppressWarnings("unused")
    private static final String TAG = "EmoticonDbHelper";
    private static final String DB_NAME = "emoticon.db";
    private static final int DB_VERSION = 1;


    EmoticonDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        //Force update
        setForcedUpgrade();
    }

    @NonNull
    ArrayList<Emoticon> getEmoticons(@EmoticonsCategories.EmoticonsCategory final int category,
                                     @Nullable EmoticonProvider emoticonProvider) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(EmoticonColumns.TABLE,
                new String[]{EmoticonColumns.UNICODE, EmoticonColumns.CATEGORY},
                EmoticonColumns.CATEGORY + "=?", new String[]{category + ""}, null, null, null);

        ArrayList<Emoticon> emoticons = new ArrayList<>();
        while (cursor.moveToNext()) {
            String unicode = cursor.getString(cursor.getColumnIndex(EmoticonColumns.UNICODE));
            if (emoticonProvider == null || emoticonProvider.hasEmoticon(unicode))
                emoticons.add(new Emoticon(unicode));
        }
        cursor.close();
        sqLiteDatabase.close();
        return emoticons;
    }

    @NonNull
    ArrayList<Emoticon> searchEmoticons(@NonNull final String searchQuery,
                                        @Nullable EmoticonProvider emoticonProvider) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(EmoticonTagsColumns.TABLE,
                new String[]{EmoticonTagsColumns.UNICODE},
                EmoticonTagsColumns.TAG + " LIKE ?", new String[]{searchQuery.trim() + "%"},
                null, null, null);

        ArrayList<Emoticon> emoticons = new ArrayList<>();
        while (cursor.moveToNext()) {
            String unicode = cursor.getString(cursor.getColumnIndex(EmoticonTagsColumns.UNICODE));
            if (emoticonProvider == null || emoticonProvider.hasEmoticon(unicode))
                emoticons.add(new Emoticon(unicode));
        }
        cursor.close();
        sqLiteDatabase.close();
        return emoticons;
    }

    @SuppressWarnings("unused")
    private static class EmoticonColumns {
        private static final String TABLE = "emoticon";
        private static final String ID = "_id";
        private static final String UNICODE = "unicode";
        private static final String CATEGORY = "category";
        private static final String NAME = "name";
    }

    @SuppressWarnings("unused")
    private static class EmoticonVariantColumns {
        private static final String TABLE = "emoticon_variant";
        private static final String ID = "variant_id";
        private static final String UNICODE = "variant_unicode";
        private static final String CATEGORY = "variant_category";
        private static final String ROOT_UNICODE = "variant_root_unicode";
        private static final String NAME = "variant_name";
    }

    @SuppressWarnings("unused")
    private static class EmoticonTagsColumns {
        private static final String TABLE = "emoticon_tags";
        private static final String ID = "tags_id";
        private static final String UNICODE = "tags_unicode";
        private static final String TAG = "tags_tags";
    }
}
