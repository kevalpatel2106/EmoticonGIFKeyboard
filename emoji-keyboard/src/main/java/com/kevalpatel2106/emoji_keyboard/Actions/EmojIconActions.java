/*
 * Copyright 2016 Hani Al Momani
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
package com.kevalpatel2106.emoji_keyboard.Actions;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.kevalpatel2106.emoji_keyboard.Helper.EmojiconGridView;
import com.kevalpatel2106.emoji_keyboard.views.EmojiconTextView;
import com.kevalpatel2106.emoji_keyboard.Helper.EmojiconsPopup;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Emojicon;


/**
 * @author Hani Al Momani (hani.momanii@gmail.com)
 */
public class EmojIconActions implements View.OnFocusChangeListener {

    private boolean useSystemEmoji = false;
    private EmojiconsPopup popup;
    private Context context;
    private KeyboardListener keyboardListener;
    private EmojiconTextView mEmojiconTextView;


    /**
     * Constructor
     *
     * @param ctx              The context of current activity.
     * @param rootView         The top most layout in your view hierarchy. The difference of this
     *                         view and the screen height will be used to calculate the keyboard
     *                         height.
     * @param emojiconTextView The Id of EditText.
     */
    public EmojIconActions(Context ctx, View rootView,
                           EmojiconTextView emojiconTextView) {
        this.context = ctx;
        mEmojiconTextView = emojiconTextView;
        this.popup = new EmojiconsPopup(rootView, ctx, useSystemEmoji);
    }

    public void setUseSystemEmoji(boolean useSystemEmoji) {
        this.useSystemEmoji = useSystemEmoji;
        mEmojiconTextView.setUseSystemDefault(useSystemEmoji);
        refresh();
    }


    private void refresh() {
        popup.updateUseSystemDefault(useSystemEmoji);
    }

    public void ShowEmojIcon() {
        //Will automatically set size according to the soft keyboard size
        popup.setSizeForSoftKeyboard();

        //If the text keyboard closes, also dismiss the emoji popup
        popup.setOnSoftKeyboardOpenCloseListener(new EmojiconsPopup
                .OnSoftKeyboardOpenCloseListener() {

            @Override
            public void onKeyboardOpen(int keyBoardHeight) {
                if (keyboardListener != null)
                    keyboardListener.onKeyboardOpen();
            }

            @Override
            public void onKeyboardClose() {
                if (keyboardListener != null)
                    keyboardListener.onKeyboardClose();
                if (popup.isShowing())
                    popup.dismiss();
            }
        });

        //On emoji clicked, add it to edittext
        popup.setOnEmojiconClickedListener(new EmojiconGridView.OnEmojiconClickedListener() {

            @Override
            public void onEmojiconClicked(Emojicon emojicon) {
                if (emojicon == null) {
                    return;
                }

                int start = mEmojiconTextView.getSelectionStart();
                int end = mEmojiconTextView.getSelectionEnd();
                if (start < 0) {
                    mEmojiconTextView.append(emojicon.getEmoji());
                }
//                else {
//                    mEmojiconTextView.getText().replace(Math.min(start, end),
//                            Math.max(start, end), emojicon.getEmoji(), 0,
//                            emojicon.getEmoji().length());
//                }
            }
        });

        //On backspace clicked, emulate the KEYCODE_DEL key event
        popup.setOnEmojiconBackspaceClickedListener(new EmojiconsPopup
                .OnEmojiconBackspaceClickedListener() {

            @Override
            public void onEmojiconBackspaceClicked(View v) {
                KeyEvent event = new KeyEvent(
                        0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
                mEmojiconTextView.dispatchKeyEvent(event);
            }
        });


        // To toggle between text keyboard and emoji keyboard keyboard(Popup)
        showForEditText();
    }

    private void showForEditText() {
        if (!popup.isShowing()) {

            //If keyboard is visible, simply show the emoji popup
            if (popup.isKeyBoardOpen()) {
                popup.showAtBottom();
            }

            //else, open the text keyboard first and immediately after that show the
            // emoji popup
            else {
                mEmojiconTextView.setFocusableInTouchMode(true);
                mEmojiconTextView.requestFocus();
                final InputMethodManager inputMethodManager = (InputMethodManager)
                        context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(mEmojiconTextView, InputMethodManager
                        .SHOW_IMPLICIT);
                popup.showAtBottomPending();
            }
        }
    }


    public void closeEmojIcon() {
        if (popup != null && popup.isShowing())
            popup.dismiss();

    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            if (view instanceof EmojiconTextView) {
                mEmojiconTextView = (EmojiconTextView) view;
            }
        }
    }


    public interface KeyboardListener {
        void onKeyboardOpen();

        void onKeyboardClose();
    }

    public void setKeyboardListener(KeyboardListener listener) {
        this.keyboardListener = listener;
    }

}
