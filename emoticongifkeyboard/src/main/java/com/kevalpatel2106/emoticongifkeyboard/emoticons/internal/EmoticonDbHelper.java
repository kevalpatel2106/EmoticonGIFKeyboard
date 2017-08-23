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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kevalpatel2106.emoticongifkeyboard.emoticons.Emoticon;

import java.util.ArrayList;

/**
 * Created by Keval on 21-Aug-17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

final class EmoticonDbHelper extends SQLiteOpenHelper {
    @SuppressWarnings("unused")
    private static final String TAG = "EmoticonDbHelper";
    private static final String DB_NAME = "emoticon.db";
    private static final int DB_VERSION = 1;


    EmoticonDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + EmoticonDbColumns.TABLE + " ("
                + EmoticonDbColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EmoticonDbColumns.UNICODE + " VARCHAR,"
                + EmoticonDbColumns.DESCRIPTION + " VARCHAR,"
                + EmoticonDbColumns.CATEGORY + " INTEGER,"
                + EmoticonDbColumns.TAGS + " VARCHAR);");

        sqLiteDatabase.execSQL("CREATE TABLE " + EmoticonTagsColumns.TABLE + " ("
                + EmoticonTagsColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EmoticonTagsColumns.UNICODE + " VARCHAR,"
                + EmoticonTagsColumns.TAG + " VARCHAR);");

        sqLiteDatabase.execSQL("CREATE INDEX unicode_category ON " + EmoticonDbColumns.TABLE + "("
                + EmoticonDbColumns.CATEGORY + ", "
                + EmoticonDbColumns.UNICODE + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TODO Drop table
    }

    synchronized void insertInDb(@NonNull final SQLiteDatabase sqLiteDatabase,
                                 @NonNull final String unicode,
                                 @EmoticonsCategories.EmoticonsCategory final int category,
                                 @NonNull final String description,
                                 @Nullable final String[] tags) {

        //Insert emoticon
        ContentValues values = new ContentValues();
        values.put(EmoticonDbColumns.UNICODE, unicode);
        values.put(EmoticonDbColumns.CATEGORY, category);
        values.put(EmoticonDbColumns.DESCRIPTION, description);
        sqLiteDatabase.insert(EmoticonDbColumns.TABLE, null, values);
        values.clear();

        //Insert tags to tags table
        if (tags == null)return;
        for (String tag: tags) {
            if (tag ==null)continue;
            values = new ContentValues();
            values.put(EmoticonTagsColumns.UNICODE, unicode);
            values.put(EmoticonTagsColumns.TAG, tag.trim());
            sqLiteDatabase.insert(EmoticonTagsColumns.TABLE, null, values);
            values.clear();
        }

        Log.d(TAG, "insertInDb: " + description);
    }

    int getCount() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT (*) FROM " + EmoticonDbColumns.TABLE, null);
        int count = 0;
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        sqLiteDatabase.close();
        return count;
    }

    @NonNull
    ArrayList<Emoticon> getEmoticons(@EmoticonsCategories.EmoticonsCategory final int category) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(EmoticonDbColumns.TABLE,
                new String[]{EmoticonDbColumns.UNICODE, EmoticonDbColumns.CATEGORY},
                EmoticonDbColumns.CATEGORY + "=?", new String[]{category + ""}, null, null, null);

        ArrayList<Emoticon> emoticons = new ArrayList<>();
        while (cursor.moveToNext()) {
            emoticons.add(new Emoticon(cursor.getString(cursor.getColumnIndex(EmoticonDbColumns.UNICODE))));
        }
        cursor.close();
        sqLiteDatabase.close();
        return emoticons;
    }

    @NonNull
    ArrayList<Emoticon> searchEmoticons(@NonNull final String searchQuery) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(EmoticonTagsColumns.TABLE,
                new String[]{EmoticonTagsColumns.UNICODE},
                EmoticonTagsColumns.TAG + " LIKE ?", new String[]{searchQuery.trim() + "%"}, null, null, null);

        ArrayList<Emoticon> emoticons = new ArrayList<>();
        while (cursor.moveToNext()) {
            emoticons.add(new Emoticon(cursor.getString(cursor.getColumnIndex(EmoticonDbColumns.UNICODE))));
        }
        cursor.close();
        sqLiteDatabase.close();
        return emoticons;
    }

    private static class EmoticonDbColumns {
        private static final String TABLE = "emoticon";
        private static final String ID = "_id";
        private static final String UNICODE = "unicode";
        private static final String TAGS = "tags";
        private static final String CATEGORY = "category";
        private static final String DESCRIPTION = "description";
    }

    private static class EmoticonTagsColumns {
        private static final String TABLE = "emoticontags";
        private static final String ID = "_id";
        private static final String UNICODE = "unicode";
        private static final String TAG = "tags";
    }
}
