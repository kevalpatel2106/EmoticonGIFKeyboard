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

package com.kevalpatel2106.emojiparser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Keval Patel on 25/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public class SQLiteJDBC {
    static Connection connect() {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + Utils.CURRENT_DIR_PATH + "/emoticon.db");
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return c;
    }

    static void createTable(Connection c) {
        try {
            Statement stmt;
            stmt = c.createStatement();

            //Create emoticon table
            String sql = "CREATE TABLE " + EmoticonDbColumns.TABLE + " ("
                    + EmoticonDbColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + EmoticonDbColumns.UNICODE + " VARCHAR,"
                    + EmoticonDbColumns.NAME + " VARCHAR,"
                    + EmoticonDbColumns.CATEGORY + " INTEGER);";
            stmt.executeUpdate(sql);


            //Create tags table
            sql = "CREATE TABLE " + EmoticonTagsColumns.TABLE + " ("
                    + EmoticonTagsColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + EmoticonTagsColumns.UNICODE + " VARCHAR,"
                    + EmoticonTagsColumns.TAG + " VARCHAR);";
            stmt.executeUpdate(sql);

            //Create index on category and unicode
            sql = "CREATE INDEX unicode_category ON " + EmoticonDbColumns.TABLE + "("
                    + EmoticonDbColumns.CATEGORY + ", "
                    + EmoticonDbColumns.UNICODE + ");";
            stmt.executeUpdate(sql);


            stmt.close();
            System.out.println("Table created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    static void insertEmoji(Connection c, Emoji emoji) {
        try {
            Statement stmt = c.createStatement();
            String sql = "INSERT INTO " + EmoticonDbColumns.TABLE + " ("
                    + EmoticonDbColumns.UNICODE + ","
                    + EmoticonDbColumns.CATEGORY + ","
                    + EmoticonDbColumns.NAME + ") " +
                    "VALUES ('" + emoji.unicode + "', "
                    + Utils.getCategoryFromCategoryName(emoji.category) + ", '"
                    + emoji.name + "');";
            stmt.executeUpdate(sql);

            for (String tag : emoji.tags) {
                sql = "INSERT INTO " + EmoticonTagsColumns.TABLE + " ("
                        + EmoticonTagsColumns.UNICODE + ","
                        + EmoticonTagsColumns.TAG + ") " +
                        "VALUES ('" + emoji.unicode + "', '"
                        + tag + "');";
                stmt.executeUpdate(sql);
            }
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private static class EmoticonDbColumns {
        private static final String TABLE = "emoticon";
        private static final String ID = "_id";
        private static final String UNICODE = "unicode";
        private static final String CATEGORY = "category";
        private static final String NAME = "name";
    }

    private static class EmoticonTagsColumns {
        private static final String TABLE = "emoticontags";
        private static final String ID = "_id";
        private static final String UNICODE = "unicode";
        private static final String TAG = "tags";
    }
}
