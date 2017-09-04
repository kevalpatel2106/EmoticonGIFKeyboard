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

package com.kevalpatel2106.emojiscrapper;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Keval Patel on 25/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

class SQLiteJDBC {
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
            String sql = "CREATE TABLE " + EmoticonColumns.TABLE + " ("
                    + EmoticonColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + EmoticonColumns.UNICODE + " VARCHAR,"
                    + EmoticonColumns.NAME + " VARCHAR,"
                    + EmoticonColumns.CATEGORY + " INTEGER);";
            stmt.executeUpdate(sql);


            //Create variant emoticon table
            sql = "CREATE TABLE " + EmoticonVariantColumns.TABLE + " ("
                    + EmoticonVariantColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + EmoticonVariantColumns.UNICODE + " VARCHAR,"
                    + EmoticonVariantColumns.NAME + " VARCHAR,"
                    + EmoticonVariantColumns.ROOT_UNICODE + " VARCHAR,"
                    + EmoticonVariantColumns.CATEGORY + " INTEGER);";
            stmt.executeUpdate(sql);


            //Create tags table
            sql = "CREATE TABLE " + EmoticonTagsColumns.TABLE + " ("
                    + EmoticonTagsColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + EmoticonTagsColumns.UNICODE + " VARCHAR,"
                    + EmoticonTagsColumns.TAG + " VARCHAR);";
            stmt.executeUpdate(sql);

            //Create index on category and unicode
            sql = "CREATE INDEX unicode_category ON " + EmoticonColumns.TABLE + "("
                    + EmoticonColumns.CATEGORY + ", "
                    + EmoticonColumns.UNICODE + ");";
            stmt.executeUpdate(sql);

            //Create index on category and unicode
            sql = "CREATE INDEX unicode_variant ON " + EmoticonVariantColumns.TABLE + "("
                    + EmoticonVariantColumns.ROOT_UNICODE + ", "
                    + EmoticonVariantColumns.UNICODE + ");";
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
            String sql = "INSERT INTO " + EmoticonColumns.TABLE + " ("
                    + EmoticonColumns.UNICODE + ","
                    + EmoticonColumns.CATEGORY + ","
                    + EmoticonColumns.NAME + ") " +
                    "VALUES (\"" + emoji.unicode + "\", "
                    + Utils.getCategoryFromCategoryName(emoji.category) + ", \""
                    + emoji.name + "\");";
            stmt.executeUpdate(sql);

            for (String tag : emoji.tags) {
                sql = "INSERT INTO " + EmoticonTagsColumns.TABLE + " ("
                        + EmoticonTagsColumns.UNICODE + ","
                        + EmoticonTagsColumns.TAG + ") " +
                        "VALUES (\"" + emoji.unicode + "\", \""
                        + tag + "\");";
                stmt.executeUpdate(sql);
            }
            stmt.close();

            //Add variant
            if (!emoji.variants.isEmpty()) {
                for (Emoji variant : emoji.variants) {
                    insertVariantEmoji(c, variant, emoji.unicode);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private static void insertVariantEmoji(Connection c, Emoji emoji, String rootUnicode) {
        try {
            Statement stmt = c.createStatement();
            String sql = "INSERT INTO " + EmoticonVariantColumns.TABLE + " ("
                    + EmoticonVariantColumns.UNICODE + ","
                    + EmoticonVariantColumns.CATEGORY + ","
                    + EmoticonVariantColumns.ROOT_UNICODE + ","
                    + EmoticonVariantColumns.NAME + ") " +
                    "VALUES (\"" + emoji.unicode + "\", "
                    + Utils.getCategoryFromCategoryName(emoji.category) + ", "
                    + "\"" + rootUnicode + "\", "
                    + "\"" + emoji.name + "\");";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private static class EmoticonColumns {
        private static final String TABLE = "emoticon";
        private static final String ID = "_id";
        private static final String UNICODE = "unicode";
        private static final String CATEGORY = "category";
        private static final String NAME = "name";
    }

    private static class EmoticonVariantColumns {
        private static final String TABLE = "emoticon_variant";
        private static final String ID = "variant_id";
        private static final String UNICODE = "variant_unicode";
        private static final String CATEGORY = "variant_category";
        private static final String ROOT_UNICODE = "variant_root_unicode";
        private static final String NAME = "variant_name";
    }

    private static class EmoticonTagsColumns {
        private static final String TABLE = "emoticon_tags";
        private static final String ID = "tags_id";
        private static final String UNICODE = "tags_unicode";
        private static final String TAG = "tags_tags";
    }
}
