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

package com.kevalpatel2106.emoji_keyboard.Helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.kevalpatel2106.emoji_keyboard.R;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Cars;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Electr;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Emojicon;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Food;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Nature;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.People;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Sport;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Symbols;

import java.util.Arrays;
import java.util.List;


/**
 * @author Hani Al Momani (hani.momanii@gmail.com)
 */

public class EmojiconsPopup extends PopupWindow implements ViewPager.OnPageChangeListener, EmojiconRecents {
    private int mEmojiTabLastSelectedIndex = -1;
    private View[] mEmojiTabs;
    private PagerAdapter mEmojisAdapter;
    private EmojiconRecentManager mRecentsManager;
    private int keyBoardHeight = 0;
    private Boolean pendingOpen = false;
    private Boolean isOpened = false;
    public EmojiconGridView.OnEmojiconClickedListener onEmojiconClickedListener;
    private OnEmojiconBackspaceClickedListener onEmojiconBackspaceClickedListener;
    private OnSoftKeyboardOpenCloseListener onSoftKeyboardOpenCloseListener;
    private View rootView;
    private Context mContext;
    private boolean mUseSystemDefault = false;
    private View view;
    private String iconPressedColor = "#495C66";
    private String tabsColor = "#DCE1E2";
    private String backgroundColor = "#E6EBEF";

    private ViewPager emojisPager;

    /**
     * Constructor
     *
     * @param rootView         The top most layout in your view hierarchy. The difference of this view and the screen height will be used to calculate the keyboard height.
     * @param mContext         The context of current activity.
     * @param useSystemDefault .
     * @param iconPressedColor .
     * @param tabsColor        .
     * @param backgroundColor  .
     */
    public EmojiconsPopup(View rootView, Context mContext, boolean useSystemDefault, String iconPressedColor, String tabsColor, String backgroundColor) {
        super(mContext);
        this.backgroundColor = backgroundColor;
        this.iconPressedColor = iconPressedColor;
        this.tabsColor = tabsColor;
        this.mUseSystemDefault = useSystemDefault;
        this.mContext = mContext;
        this.rootView = rootView;
        View customView = createCustomView();
        setContentView(customView);
        setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        setSize(LayoutParams.MATCH_PARENT, 255);
        setBackgroundDrawable(null);


    }

    /**
     * Constructor
     *
     * @param rootView         The top most layout in your view hierarchy. The difference of this view and the screen height will be used to calculate the keyboard height.
     * @param mContext         The context of current activity.
     * @param useSystemDefault .
     */
    public EmojiconsPopup(View rootView, Context mContext, boolean useSystemDefault) {
        super(mContext);
        this.mUseSystemDefault = useSystemDefault;
        this.mContext = mContext;
        this.rootView = rootView;
        View customView = createCustomView();
        setContentView(customView);
        setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        setSize(LayoutParams.MATCH_PARENT, 255);
        setBackgroundDrawable(null);

    }

    /**
     * Set the listener for the event of keyboard opening or closing.
     */
    public void setOnSoftKeyboardOpenCloseListener(OnSoftKeyboardOpenCloseListener listener) {
        this.onSoftKeyboardOpenCloseListener = listener;
    }

    /**
     * Set the listener for the event when any of the emojicon is clicked
     */
    public void setOnEmojiconClickedListener(EmojiconGridView.OnEmojiconClickedListener listener) {
        this.onEmojiconClickedListener = listener;
    }

    /**
     * Set the listener for the event when backspace on emojicon popup is clicked
     */
    public void setOnEmojiconBackspaceClickedListener(OnEmojiconBackspaceClickedListener listener) {
        this.onEmojiconBackspaceClickedListener = listener;
    }

    /**
     * Use this function to show the emoji popup.
     * NOTE: Since, the soft keyboard sizes are variable on different android devices, the
     * library needs you to open the soft keyboard atleast once before calling this function.
     * If that is not possible see showAtBottomPending() function.
     */
    public void showAtBottom() {
        showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

    /**
     * Use this function when the soft keyboard has not been opened yet. This
     * will show the emoji popup after the keyboard is up next time.
     * Generally, you will be calling InputMethodManager.showSoftInput function after
     * calling this function.
     */
    public void showAtBottomPending() {
        if (isKeyBoardOpen())
            showAtBottom();
        else
            pendingOpen = true;
    }

    /**
     * @return Returns true if the soft keyboard is open, false otherwise.
     */
    public Boolean isKeyBoardOpen() {
        return isOpened;
    }

    /**
     * Dismiss the popup
     */
    @Override
    public void dismiss() {
        super.dismiss();
        EmojiconRecentManager.getInstance(mContext).saveRecent();
    }

    /**
     * Call this function to resize the emoji popup according to your soft keyboard size
     */
    public void setSizeForSoftKeyboard() {
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);

                int screenHeight = getUsableScreenHeight();
                int heightDifference = screenHeight
                        - (r.bottom - r.top);
                int resourceId = mContext.getResources()
                        .getIdentifier("status_bar_height",
                                "dimen", "android");
                if (resourceId > 0) {
                    heightDifference -= mContext.getResources()
                            .getDimensionPixelSize(resourceId);
                }
                if (heightDifference > 100) {
                    keyBoardHeight = heightDifference;
                    setSize(LayoutParams.MATCH_PARENT, keyBoardHeight);
                    if (!isOpened) {
                        if (onSoftKeyboardOpenCloseListener != null)
                            onSoftKeyboardOpenCloseListener.onKeyboardOpen(keyBoardHeight);
                    }
                    isOpened = true;
                    if (pendingOpen) {
                        showAtBottom();
                        pendingOpen = false;
                    }
                } else {
                    isOpened = false;
                    if (onSoftKeyboardOpenCloseListener != null)
                        onSoftKeyboardOpenCloseListener.onKeyboardClose();
                }
            }
        });
    }

    private int getUsableScreenHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();

            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);

            return metrics.heightPixels;

        } else {
            return rootView.getRootView().getHeight();
        }
    }

    /**
     * Manually set the popup window size
     *
     * @param width  Width of the popup
     * @param height Height of the popup
     */
    private void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void updateUseSystemDefault(boolean mUseSystemDefault) {
        if (view != null) {
            mEmojisAdapter = null;
            int positionPager = emojisPager.getCurrentItem();
            dismiss();

            this.mUseSystemDefault = mUseSystemDefault;
            setContentView(createCustomView());
            //mEmojisAdapter.notifyDataSetChanged();
            mEmojiTabs[positionPager].setSelected(true);
            emojisPager.setCurrentItem(positionPager);
            onPageSelected(positionPager);
            if (!isShowing()) {

                //If keyboard is visible, simply show the emoji popup
                if (isKeyBoardOpen()) {
                    showAtBottom();
                    // changeEmojiKeyboardIcon(emojiButton, R.drawable.ic_action_keyboard);
                }

                //else, open the text keyboard first and immediately after that show the emoji popup
                else {
                    showAtBottomPending();
                    // changeEmojiKeyboardIcon(emojiButton, R.drawable.ic_action_keyboard);
                }
            }


        }
    }


    private View createCustomView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.tab_emoticons, null, false);
        LinearLayout tabs = (LinearLayout) view.findViewById(R.id.emojis_tab);

        emojisPager.setOnPageChangeListener(this);
        EmojiconRecents recents = this;
        mEmojisAdapter = new EmojisPagerAdapter(
                Arrays.asList(
                        new EmojiconRecentsGridView(mContext, null, null, this, mUseSystemDefault),
                        new EmojiconGridView(mContext, People.DATA, recents, this, mUseSystemDefault),
                        new EmojiconGridView(mContext, Nature.DATA, recents, this, mUseSystemDefault),
                        new EmojiconGridView(mContext, Food.DATA, recents, this, mUseSystemDefault),
                        new EmojiconGridView(mContext, Sport.DATA, recents, this, mUseSystemDefault),
                        new EmojiconGridView(mContext, Cars.DATA, recents, this, mUseSystemDefault),
                        new EmojiconGridView(mContext, Electr.DATA, recents, this, mUseSystemDefault),
                        new EmojiconGridView(mContext, Symbols.DATA, recents, this, mUseSystemDefault)

                )
        );
        emojisPager.setAdapter(mEmojisAdapter);
        mEmojiTabs = new View[8];

        mEmojiTabs[0] = view.findViewById(R.id.emojis_tab_0_recents);
        mEmojiTabs[1] = view.findViewById(R.id.emojis_tab_1_people);
        mEmojiTabs[2] = view.findViewById(R.id.emojis_tab_2_nature);
        mEmojiTabs[3] = view.findViewById(R.id.emojis_tab_3_food);
        mEmojiTabs[4] = view.findViewById(R.id.emojis_tab_4_sport);
        mEmojiTabs[5] = view.findViewById(R.id.emojis_tab_5_cars);
        mEmojiTabs[6] = view.findViewById(R.id.emojis_tab_6_elec);
        mEmojiTabs[7] = view.findViewById(R.id.emojis_tab_7_sym);
        for (int i = 0; i < mEmojiTabs.length; i++) {
            final int position = i;
            mEmojiTabs[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    emojisPager.setCurrentItem(position);
                }
            });
        }


        emojisPager.setBackgroundColor(Color.parseColor(backgroundColor));
        tabs.setBackgroundColor(Color.parseColor(tabsColor));
        for (int x = 0; x < mEmojiTabs.length; x++) {
            ImageButton btn = (ImageButton) mEmojiTabs[x];
            btn.setColorFilter(Color.parseColor(iconPressedColor));
        }

        ImageButton imgBtn = (ImageButton) view.findViewById(R.id.emojis_backspace);
        imgBtn.setColorFilter(Color.parseColor(iconPressedColor));
        imgBtn.setBackgroundColor(Color.parseColor(backgroundColor));


        view.findViewById(R.id.emojis_backspace).setOnTouchListener(new RepeatListener(500, 50, new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (onEmojiconBackspaceClickedListener != null)
                    onEmojiconBackspaceClickedListener.onEmojiconBackspaceClicked(v);
            }
        }));

        // get last selected page
        mRecentsManager = EmojiconRecentManager.getInstance(view.getContext());
        int page = mRecentsManager.getRecentPage();
        // last page was recents, check if there are recents to use
        // if none was found, go to page 1
        if (page == 0 && mRecentsManager.size() == 0) {
            page = 1;
        }

        if (page == 0) {
            onPageSelected(page);
        } else {
            emojisPager.setCurrentItem(page, false);
        }
        return view;
    }

    @Override
    public void addRecentEmoji(Context context, Emojicon emojicon) {
        EmojiconRecentsGridView fragment = ((EmojisPagerAdapter) emojisPager.getAdapter()).getRecentFragment();
        fragment.addRecentEmoji(context, emojicon);
    }


    @Override
    public void onPageScrolled(int i, float v, int i2) {
    }

    @Override
    public void onPageSelected(int i) {
        if (mEmojiTabLastSelectedIndex == i) {
            return;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:

                if (mEmojiTabLastSelectedIndex >= 0 && mEmojiTabLastSelectedIndex < mEmojiTabs.length) {
                    mEmojiTabs[mEmojiTabLastSelectedIndex].setSelected(false);
                }
                mEmojiTabs[i].setSelected(true);
                mEmojiTabLastSelectedIndex = i;
                mRecentsManager.setRecentPage(i);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    private static class EmojisPagerAdapter extends PagerAdapter {
        private List<EmojiconGridView> views;

        public EmojiconRecentsGridView getRecentFragment() {
            for (EmojiconGridView it : views) {
                if (it instanceof EmojiconRecentsGridView)
                    return (EmojiconRecentsGridView) it;
            }
            return null;
        }

        public EmojisPagerAdapter(List<EmojiconGridView> views) {
            super();
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = views.get(position).rootView;
            ((ViewPager) container).addView(v, 0);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object view) {
            ((ViewPager) container).removeView((View) view);
        }

        @Override
        public boolean isViewFromObject(View view, Object key) {
            return key == view;
        }
    }

    /**
     * A class, that can be used as a TouchListener on any view (e.g. a Button).
     * It cyclically runs a clickListener, emulating keyboard-like behaviour. First
     * click is fired immediately, next before initialInterval, and subsequent before
     * normalInterval.
     * <p/>
     * <p>Interval is scheduled before the onClick completes, so it has to run fast.
     * If it runs slow, it does not generate skipped onClicks.
     */
    public static class RepeatListener implements View.OnTouchListener {

        private Handler handler = new Handler();

        private int initialInterval;
        private final int normalInterval;
        private final OnClickListener clickListener;

        private Runnable handlerRunnable = new Runnable() {
            @Override
            public void run() {
                if (downView == null) {
                    return;
                }
                handler.removeCallbacksAndMessages(downView);
                handler.postAtTime(this, downView, SystemClock.uptimeMillis() + normalInterval);
                clickListener.onClick(downView);
            }
        };

        private View downView;

        /**
         * @param initialInterval The interval before first click event
         * @param normalInterval  The interval before second and subsequent click
         *                        events
         * @param clickListener   The OnClickListener, that will be called
         *                        periodically
         */
        public RepeatListener(int initialInterval, int normalInterval, OnClickListener clickListener) {
            if (clickListener == null)
                throw new IllegalArgumentException("null runnable");
            if (initialInterval < 0 || normalInterval < 0)
                throw new IllegalArgumentException("negative interval");

            this.initialInterval = initialInterval;
            this.normalInterval = normalInterval;
            this.clickListener = clickListener;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downView = view;
                    handler.removeCallbacks(handlerRunnable);
                    handler.postAtTime(handlerRunnable, downView, SystemClock.uptimeMillis() + initialInterval);
                    clickListener.onClick(view);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_OUTSIDE:
                    handler.removeCallbacksAndMessages(downView);
                    downView = null;
                    return true;
            }
            return false;
        }
    }

    public interface OnEmojiconBackspaceClickedListener {
        void onEmojiconBackspaceClicked(View v);
    }

    public interface OnSoftKeyboardOpenCloseListener {
        void onKeyboardOpen(int keyBoardHeight);

        void onKeyboardClose();
    }


}