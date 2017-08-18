/*
 * Copyright 2014 Hieu Rocker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kevalpatel2106.emoji_keyboard.Helper;

import android.content.Context;
import android.widget.GridView;
import com.kevalpatel2106.emoji_keyboard.R;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Emojicon;


/**
 * @author Daniele Ricci
 * @author Hani Al Momani (hani.momanii@gmail.com)
 */
public class EmojiconRecentsGridView  extends EmojiconGridView implements EmojiconRecents {
    EmojiAdapter mAdapter;
    private boolean mUseSystemDefault = false;



    public EmojiconRecentsGridView(Context context, Emojicon[] emojicons,
                                   EmojiconRecents recents,EmojiconsPopup emojiconsPopup,boolean useSystemDefault) {
        super(context, emojicons, recents, emojiconsPopup,useSystemDefault);
        this.mUseSystemDefault=useSystemDefault;
        EmojiconRecentManager recents1 = EmojiconRecentManager
                .getInstance(rootView.getContext());
        mAdapter = new EmojiAdapter(rootView.getContext(),  recents1,mUseSystemDefault);
        mAdapter.setEmojiClickListener(new OnEmojiconClickedListener() {

            @Override
            public void onEmojiconClicked(Emojicon emojicon) {
                if (mEmojiconPopup.onEmojiconClickedListener != null) {
                    mEmojiconPopup.onEmojiconClickedListener.onEmojiconClicked(emojicon);
                }
            }
        });
        GridView gridView = (GridView) rootView.findViewById(R.id.Emoji_GridView);
        gridView.setAdapter(mAdapter);
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addRecentEmoji(Context context, Emojicon emojicon) {
        EmojiconRecentManager recents = EmojiconRecentManager
                .getInstance(context);
        recents.push(emojicon);

        // notify dataset changed
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

}