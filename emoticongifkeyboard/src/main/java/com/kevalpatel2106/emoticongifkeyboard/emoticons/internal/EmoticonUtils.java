package com.kevalpatel2106.emoticongifkeyboard.emoticons.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.TextUtils;

import com.kevalpatel2106.emoticongifkeyboard.emoticons.Emoticon;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Keval Patel on 20/08/17.
 *
 * @author 'https://github.com/kevalpatel2106'
 */

public final class EmoticonUtils {
    private static final String EMOTICON_REGEX_KEY = "emoticon_regex_key";
    /**
     * Name of the preference file to store all the emoticons data.
     */
    private static final String PREFERENCE_NAME = "emojicon";
    private static final Comparator<String> STRING_LENGTH_COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(final String first, final String second) {
            final int firstLength = first.length();
            final int secondLength = second.length();
            return firstLength < secondLength ? 1 : firstLength == secondLength ? 0 : -1;
        }
    };
    private static Pattern sRegexPattern;

    private EmoticonUtils() {
        //Do nothing
    }

    /**
     * Create the regex to find emoticons and save it to the shared preference. This will generate
     * regex by adding unicode of all emoticons separated by '|'.
     *
     * @param context            Instance
     * @param unicodesForPattern List of all the supported unicodes from the database.
     * @see EmoticonLoader#doInBackground(Void...)
     */
    static void createAndSaveEmoticonRegex(@NonNull final Context context,
                                           @NonNull final ArrayList<String> unicodesForPattern) {
        // We need to sort the unicodes by length so the longest one gets matched first.
        Collections.sort(unicodesForPattern, STRING_LENGTH_COMPARATOR);

        //Save the regex
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(EMOTICON_REGEX_KEY, TextUtils.join("|", unicodesForPattern))
                .apply();
    }

    /**
     * Replace the system emoticons with the provided custom emoticons drawable. This will find the
     * unicodes with supported emoticons in the provided text and  will replace the emoticons with
     * appropriate images.
     *
     * @param context          instance of caller.
     * @param text             Text to replace
     * @param emoticonProvider {@link EmoticonProvider} for emoticons images
     * @param emoticonSize     Size of the emoticons in dp
     * @return Modified text.
     */
    public static Spannable replaceWithImages(@NonNull final Context context,
                                              @NonNull final Spannable text,
                                              @NonNull final EmoticonProvider emoticonProvider,
                                              final int emoticonSize) {

        final EmoticonSpan[] existingSpans = text.getSpans(0, text.length(), EmoticonSpan.class);
        final ArrayList<Integer> existingSpanPositions = new ArrayList<>(existingSpans.length);
        for (EmoticonSpan existingSpan : existingSpans)
            existingSpanPositions.add(text.getSpanStart(existingSpan));

        //Get location and unicode of all emoticons.
        final List<EmoticonRange> findAllEmojis = findAllEmoticons(context, text, emoticonProvider);

        //Replace all the emoticons with their relatives.
        for (int i = 0; i < findAllEmojis.size(); i++) {
            final EmoticonRange location = findAllEmojis.get(i);
            if (!existingSpanPositions.contains(location.start)) {
                text.setSpan(new EmoticonSpan(context, location.mEmoticon.getIcon(), emoticonSize),
                        location.start,
                        location.end,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return text;
    }

    /**
     * Find all the unicodes that represents emoticons and location of starting and ending point of that emoticon.
     *
     * @param context          Instance of caller.
     * @param text             Text to replace
     * @param emoticonProvider {@link EmoticonProvider} for emoticons images
     * @return List of {@link EmoticonRange}.
     * @see EmoticonRange
     */
    @NonNull
    private static List<EmoticonRange> findAllEmoticons(@NonNull final Context context,
                                                        @Nullable final CharSequence text,
                                                        @NonNull final EmoticonProvider emoticonProvider) {
        final List<EmoticonRange> result = new ArrayList<>();

        if (!TextUtils.isEmpty(text)) {
            final Matcher matcher = getRegex(context).matcher(text);
            while (matcher.find()) {
                final Emoticon found = emoticonProvider.getEmoticon(text.subSequence(matcher.start(),
                        matcher.end()).toString());
                if (found != null && found.getIcon() > 0) { //Check if the the emoticon has icon?

                    //Add this emoticon to change list.
                    result.add(new EmoticonRange(matcher.start(), matcher.end(), found));
                }
            }
        }

        return result;
    }

    private static Pattern getRegex(@NonNull final Context context) {
        if (sRegexPattern == null) {
            String regex = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                    .getString(EMOTICON_REGEX_KEY, null);
            if (regex == null) throw new RuntimeException("Database is not loaded yet.");
            sRegexPattern = Pattern.compile(regex);
        }
        return sRegexPattern;
    }

    /**
     * Range of the emoticons unicode.
     */
    private static final class EmoticonRange {
        final int start;
        final int end;
        final Emoticon mEmoticon;

        private EmoticonRange(final int start, final int end, @NonNull final Emoticon emoticon) {
            this.start = start;
            this.end = end;
            this.mEmoticon = emoticon;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final EmoticonRange that = (EmoticonRange) o;
            return start == that.start && end == that.end && mEmoticon.equals(that.mEmoticon);
        }

        @Override
        public int hashCode() {
            int result = start;
            result = 31 * result + end;
            result = 31 * result + mEmoticon.hashCode();
            return result;
        }
    }
}
