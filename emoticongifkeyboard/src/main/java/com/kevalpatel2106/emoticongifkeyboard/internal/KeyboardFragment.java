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

package com.kevalpatel2106.emoticongifkeyboard.internal;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonSelectListener;
import com.kevalpatel2106.emoticongifkeyboard.gifs.GifProviderProtocol;
import com.kevalpatel2106.emoticongifkeyboard.gifs.GifSelectListener;
import com.kevalpatel2106.emoticongifkeyboard.internal.emoticon.EmoticonFragment;
import com.kevalpatel2106.emoticongifkeyboard.internal.emoticon.EmoticonSearchFragment;
import com.kevalpatel2106.emoticongifkeyboard.internal.gif.GifFragment;
import com.kevalpatel2106.emoticongifkeyboard.internal.gif.GifSearchFragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * This {@link Fragment} will host the smiles and gifs.
 * Add this fragment into your view of activity or fragment.
 *
 * @author 'https://github.com/kevalpatel2106'
 */
public final class KeyboardFragment extends Fragment implements FragmentManager.OnBackStackChangedListener {
    /**
     * Tags for the fragment back stack
     */
    public static final String TAG_EMOTICON_FRAGMENT = "tag_emoticon_fragment";
    public static final String TAG_GIF_FRAGMENT = "tag_gif_fragment";
    private static final String TAG_EMOTICON_SEARCH_FRAGMENT = "tag_emoticon_search_fragment";
    private static final String TAG_GIF_SEARCH_FRAGMENT = "tag_gif_search_fragment";

    /**
     * Key for saved instance bundle.
     */
    private static final String KEY_CURRENT_FRAGMENT = "current_fragment";

    /**
     * Fragments to load.
     */
    @NonNull
    private final EmoticonFragment mEmoticonFragment;
    @NonNull
    private final GifFragment mGifFragment;
    @NonNull
    private final GifSearchFragment mGifSearchFragment;
    @NonNull
    private final EmoticonSearchFragment mEmoticonSearchFragment;

    /**
     * Listener to notify when emoticons selected.
     */
    @Nullable
    private EmoticonSelectListener mEmoticonSelectListener;

    /**
     * View container that hosts search, backspace and tabs buttons.
     */
    private View mBottomViewContainer;
    private View mGifTabBtn;
    private View mEmoticonTabBtn;
    private View mBackSpaceBtn;
    private View mRootView;

    private boolean mIsEmoticonsEnable = true;
    private boolean mIsGIFsEnable = true;

    /**
     * Public constructor. Don't call constructor to create new instance. Use {@link #getNewInstance()}
     * instead.
     *
     * @see #getNewInstance()
     */
    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    public KeyboardFragment() {
        mEmoticonFragment = EmoticonFragment.getNewInstance();
        mEmoticonSearchFragment = EmoticonSearchFragment.getNewInstance();

        mGifFragment = GifFragment.getNewInstance();
        mGifSearchFragment = GifSearchFragment.getNewInstance();
    }

    /**
     * Get the new instance of {@link KeyboardFragment}. Call this method to get repairable instance
     * of fragment instead of directly calling constructor.
     *
     * @return {@link KeyboardFragment}
     */
    @SuppressWarnings("deprecation")
    public static KeyboardFragment getNewInstance() {
        KeyboardFragment keyboardFragment = new KeyboardFragment();
        keyboardFragment.setRetainInstance(true);
        return keyboardFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        //Add back stack change listener to maintain views state according to fragment
        getChildFragmentManager().addOnBackStackChangedListener(this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keyboard, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view.findViewById(R.id.root_view);
        mBottomViewContainer = view.findViewById(R.id.bottom_container);

        //Set backspace button
        mBackSpaceBtn = view.findViewById(R.id.emojis_backspace);
        mBackSpaceBtn.setOnClickListener(view1 -> {
            if (mEmoticonSelectListener != null) mEmoticonSelectListener.onBackSpace();
        });

        //Set the tabs
        mEmoticonTabBtn = view.findViewById(R.id.btn_emoji_tab);
        mGifTabBtn = view.findViewById(R.id.btn_gif_tab);
        mEmoticonTabBtn.setOnClickListener(view12 -> replaceFragment(mEmoticonFragment, TAG_EMOTICON_FRAGMENT));
        mGifTabBtn.setOnClickListener(view13 -> replaceFragment(mGifFragment, TAG_GIF_FRAGMENT));

        //Setup the search button.
        View searchBtn = view.findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(view14 -> {
            if (mEmoticonTabBtn.isSelected()) {
                replaceFragment(mEmoticonSearchFragment, TAG_EMOTICON_SEARCH_FRAGMENT);
            } else {
                replaceFragment(mGifSearchFragment, TAG_GIF_SEARCH_FRAGMENT);
            }
        });

        if (savedInstanceState != null) {   //Fragment reloaded from config changes,
            //noinspection ConstantConditions
            switch (savedInstanceState.getString(KEY_CURRENT_FRAGMENT)) {
                case TAG_EMOTICON_FRAGMENT:
                    replaceFragment(mGifFragment, TAG_GIF_FRAGMENT);
                    break;
                case TAG_GIF_FRAGMENT:
                    replaceFragment(mEmoticonFragment, TAG_EMOTICON_FRAGMENT);
                    break;
                case TAG_EMOTICON_SEARCH_FRAGMENT:
                    replaceFragment(mEmoticonSearchFragment, TAG_EMOTICON_SEARCH_FRAGMENT);
                    break;
                case TAG_GIF_SEARCH_FRAGMENT:
                    replaceFragment(mGifSearchFragment, TAG_GIF_SEARCH_FRAGMENT);
                    break;
            }
        } else {
            //Display emoticon fragment by default.
            replaceFragment(mEmoticonFragment, TAG_EMOTICON_FRAGMENT);
        }
    }

    /**
     * Replace the fragment in the fragment container.
     *
     * @param fragment New {@link Fragment} to replace
     * @param tag      Tag for the back stack entry
     */
    private void replaceFragment(@NonNull Fragment fragment,
                                 @FragmentBackStackTags String tag) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.keyboard_fragment_container, fragment)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Save current fragment
        outState.putString(KEY_CURRENT_FRAGMENT, getChildFragmentManager()
                .getBackStackEntryAt(getChildFragmentManager().getBackStackEntryCount() - 1)
                .getName());
        super.onSaveInstanceState(outState);
    }

    /**
     * Set the {@link EmoticonSelectListener} to get notify whenever the emoticon is selected or deleted.
     *
     * @param emoticonSelectListener {@link EmoticonSelectListener}
     * @see EmoticonSelectListener
     */
    @SuppressWarnings("ConstantConditions")
    public void setEmoticonSelectListener(@NonNull EmoticonSelectListener emoticonSelectListener) {
        if (emoticonSelectListener == null)
            throw new IllegalArgumentException("EmoticonSelectListener cannot be null.");

        mEmoticonSelectListener = emoticonSelectListener;
        mEmoticonFragment.setEmoticonSelectListener(emoticonSelectListener);
    }

    /**
     * Set the GIF provider for for fetching the GIFs.
     *
     * @param gifProvider Loader class that extends {@link GifProviderProtocol}.
     * @see GifProviderProtocol
     */
    public void setGifProvider(@NonNull GifProviderProtocol gifProvider) {
        mGifFragment.setGifProvider(gifProvider);
        mGifSearchFragment.setGifProvider(gifProvider);
    }

    /**
     * Set the {@link EmoticonProvider} to render different images for unicode. If the value is null,
     * system emoticon images will render.
     *
     * @param emoticonProvider {@link EmoticonProvider} for custom emoticon packs or null to use system
     *                         emoticons.
     * @see EmoticonProvider
     */
    public void setEmoticonProvider(@Nullable EmoticonProvider emoticonProvider) {
        mEmoticonFragment.setEmoticonProvider(emoticonProvider);
        mEmoticonSearchFragment.setEmoticonProvider(emoticonProvider);
    }

    /**
     * Set the {@link EmoticonSelectListener} to get notify whenever the emoticon is selected or deleted.
     *
     * @param gifSelectListener {@link EmoticonSelectListener}
     * @see GifSelectListener
     */
    public void setGifSelectListener(@NonNull GifSelectListener gifSelectListener) {
        mGifFragment.setGifSelectListener(gifSelectListener);
        mGifSearchFragment.setGifSelectListener(gifSelectListener);
    }

    /**
     * Handle the back stack changes in the fragment container.
     */
    @Override
    public void onBackStackChanged() {
        int index = getChildFragmentManager().getBackStackEntryCount() - 1;
        if (index < 0) return;

        //noinspection WrongConstant
        changeLayoutFromTag(getChildFragmentManager().getBackStackEntryAt(index).getName());
    }

    /**
     * Make layout changes based on the fragment tag.
     *
     * @param tag Fragment tags.
     * @see FragmentBackStackTags
     */
    private void changeLayoutFromTag(@FragmentBackStackTags String tag) {
        switch (tag) {
            case TAG_EMOTICON_FRAGMENT:
                //Display bottom bar of not displayed.
                if (mBottomViewContainer != null) mBottomViewContainer.setVisibility(View.VISIBLE);

                mEmoticonTabBtn.setSelected(true);
                mGifTabBtn.setSelected(!mEmoticonTabBtn.isSelected());
                mBackSpaceBtn.setVisibility(View.VISIBLE);
                break;
            case TAG_GIF_FRAGMENT:
                //Display bottom bar of not displayed.
                if (mBottomViewContainer != null) mBottomViewContainer.setVisibility(View.VISIBLE);

                mEmoticonTabBtn.setSelected(false);
                mGifTabBtn.setSelected(!mEmoticonTabBtn.isSelected());
                mBackSpaceBtn.setVisibility(View.GONE);
                break;
            case TAG_EMOTICON_SEARCH_FRAGMENT:
                //Display bottom bar of not displayed.
                if (mBottomViewContainer != null) mBottomViewContainer.setVisibility(View.GONE);
                break;
            case TAG_GIF_SEARCH_FRAGMENT:
                //Display bottom bar of not displayed.
                if (mBottomViewContainer != null) mBottomViewContainer.setVisibility(View.GONE);
                break;
            default:
                //Do nothing
        }
    }

    public void enableEmoticons(@SuppressWarnings("SameParameterValue") boolean show) {
        mIsEmoticonsEnable = show;
        if (!mIsGIFsEnable && !mIsEmoticonsEnable)
            throw new RuntimeException("At least one of emoticon or GIF should be active.");

//        mEmoticonTabBtn.setVisibility(mIsEmoticonsEnable ? View.VISIBLE : View.GONE);
//        mGifTabBtn.setVisibility(mIsGIFsEnable ? View.VISIBLE : View.GONE);

        //Replace the fragment with emoticon fragment
        if (!mIsEmoticonsEnable) replaceFragment(mGifFragment, TAG_GIF_FRAGMENT);
    }

    /**
     * @return True if emoticons are enable for the keyboard.
     * @see #enableEmoticons(boolean)
     */
    public boolean isEmoticonsEnable() {
        return mIsEmoticonsEnable;
    }

    public void enableGIFs(@SuppressWarnings("SameParameterValue") boolean show) {
        mIsGIFsEnable = show;
        if (!mIsGIFsEnable && !mIsEmoticonsEnable)
            throw new RuntimeException("At least one of emoticon or GIF should be active.");

//        mEmoticonTabBtn.setVisibility(mIsEmoticonsEnable ? View.VISIBLE : View.GONE);
//        mGifTabBtn.setVisibility(mIsGIFsEnable ? View.VISIBLE : View.GONE);

        //Replace the fragment with emoticon fragment
        if (!mIsGIFsEnable) replaceFragment(mEmoticonFragment, TAG_EMOTICON_FRAGMENT);
    }

    /**
     * @return True if GIFs are enable for the keyboard.
     * @see #enableGIFs(boolean)
     */
    public boolean isGIFsEnable() {
        return mIsGIFsEnable;
    }

    /**
     * Show thw keyboard with resize animation.
     */
    public void open() {
        ResizeAnimation resizeAnimation = new ResizeAnimation(mRootView,
                (int) getResources().getDimension(R.dimen.emoticon_gif_fragments_height),
                0);
        resizeAnimation.setDuration(300);
        mRootView.startAnimation(resizeAnimation);
    }

    /**
     * Hide the keyboard with resize animation.
     */
    public void close() {
        ResizeAnimation resizeAnimation = new ResizeAnimation(mRootView,
                0,
                mRootView.getWidth());
        resizeAnimation.setDuration(300);
        mRootView.startAnimation(resizeAnimation);
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_EMOTICON_FRAGMENT, TAG_EMOTICON_SEARCH_FRAGMENT, TAG_GIF_FRAGMENT, TAG_GIF_SEARCH_FRAGMENT})
    @interface FragmentBackStackTags {
    }

    /**
     * Custom {@link Animation} class to resize the height of the view in given duration.
     *
     * @see <a href='https://gist.github.com/rafali/5146957'>ResizeAnimation.java</a>
     */
    private class ResizeAnimation extends Animation {
        private final int mStartHeight;     //Start height
        private final int mTargetHeight;
        private final View view;

        /**
         * Public constructor.
         *
         * @param view         View to resize.
         * @param targetHeight Desired height of the view at the end of the animation. The number
         *                     must be 0 or positive.
         * @param startHeight  Current height of view.  The number must be 0 or positive.
         */
        @SuppressWarnings("WeakerAccess")
        public ResizeAnimation(@NonNull View view, int targetHeight, int startHeight) {
            if (targetHeight < 0 || startHeight < 0) {
                throw new IllegalArgumentException("Height cannot be negative.");
            }
            this.view = view;
            this.mTargetHeight = targetHeight;
            mStartHeight = startHeight;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            view.getLayoutParams().height = (int) (mStartHeight + (mTargetHeight - mStartHeight) * interpolatedTime);
            view.requestLayout();
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }
}
