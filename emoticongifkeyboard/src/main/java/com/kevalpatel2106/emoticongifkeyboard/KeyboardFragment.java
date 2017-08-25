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

package com.kevalpatel2106.emoticongifkeyboard;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonSelectListener;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.internal.EmoticonFragment;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.internal.EmoticonSearchFragment;
import com.kevalpatel2106.emoticongifkeyboard.gifs.GifProviderProtocol;
import com.kevalpatel2106.emoticongifkeyboard.gifs.GifSelectListener;
import com.kevalpatel2106.emoticongifkeyboard.gifs.internal.GifFragment;
import com.kevalpatel2106.emoticongifkeyboard.gifs.internal.GifSearchFragment;


/**
 * This {@link Fragment} will host the smiles and gifs.
 *
 * @author 'https://github.com/kevalpatel2106'
 */
public final class KeyboardFragment extends Fragment implements FragmentManager.OnBackStackChangedListener {
    /* Tags for the fragment back stack */
    public static final String TAG_EMOTICON_FRAGMENT = "tag_emoticon_fragment";
    public static final String TAG_GIF_FRAGMENT = "tag_gif_fragment";
    @SuppressWarnings("unused")
    private static final String TAG = "KeyboardFragment";
    //Keys for saved instance
    private static final String KEY_CURRENT_FRAGMENT = "current_fragment";
    private static final String TAG_EMOTICON_SEARCH_FRAGMENT = "tag_emoticon_search_fragment";
    private static final String TAG_GIF_SEARCH_FRAGMENT = "tag_gif_search_fragment";

    /* Fragments to load. */
    private final EmoticonFragment mEmoticonFragment;
    private final GifFragment mGifFragment;
    private final GifSearchFragment mGifSearchFragment;
    private final EmoticonSearchFragment mEmoticonSearchFragment;

    /* Listener to notify when emoticons selected. */
    private EmoticonSelectListener mEmoticonSelectListener;

    /* View container that hosts search, backspace and tabs buttons. */
    private View mBottomViewContainer;
    private View mGifTabBtn;
    private View mEmoticonTabBtn;
    private View mBackSpaceBtn;

    /**
     * Public constructor.
     */
    public KeyboardFragment() {
        mEmoticonFragment = EmoticonFragment.getNewInstance();
        mEmoticonSearchFragment = EmoticonSearchFragment.getNewInstance();

        mGifFragment = GifFragment.getNewInstance();
        mGifSearchFragment = GifSearchFragment.getNewInstance();
    }

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
        mBottomViewContainer = view.findViewById(R.id.bottom_container);

        //Set backspace button
        mBackSpaceBtn = view.findViewById(R.id.emojis_backspace);
        mBackSpaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEmoticonSelectListener != null) mEmoticonSelectListener.onBackSpace();
            }
        });

        //Set the tabs
        mEmoticonTabBtn = view.findViewById(R.id.btn_emoji_tab);
        mGifTabBtn = view.findViewById(R.id.btn_gif_tab);
        mEmoticonTabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(mEmoticonFragment, TAG_EMOTICON_FRAGMENT);
            }
        });
        mGifTabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(mGifFragment, TAG_GIF_FRAGMENT);
            }
        });

        //Setup the search button.
        View searchBtn = view.findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEmoticonTabBtn.isSelected()) {
                    replaceFragment(mEmoticonSearchFragment, TAG_EMOTICON_SEARCH_FRAGMENT);
                } else {
                    replaceFragment(mGifSearchFragment, TAG_GIF_SEARCH_FRAGMENT);
                }
            }
        });

        if (savedInstanceState != null) {
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
            mEmoticonTabBtn.callOnClick();
        }
    }

    private void replaceFragment(Fragment fragment, String tag) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.keyboard_fragment_container, fragment)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_CURRENT_FRAGMENT, getChildFragmentManager()
                .getBackStackEntryAt(getChildFragmentManager().getBackStackEntryCount() - 1)
                .getName());
        super.onSaveInstanceState(outState);
    }

    /**
     * Set the {@link EmoticonSelectListener} to get notify whenever the emoticon is selected or deleted.
     *
     * @param emoticonSelectListener {@link EmoticonSelectListener}
     */
    @SuppressWarnings("ConstantConditions")
    public void setEmoticonSelectListener(@NonNull EmoticonSelectListener emoticonSelectListener) {
        if (emoticonSelectListener == null)
            throw new IllegalArgumentException("EmoticonSelectListener cannot be null.");

        mEmoticonSelectListener = emoticonSelectListener;
        mEmoticonFragment.setEmoticonSelectListener(emoticonSelectListener);
    }

    /**
     * Set the GIF loader adapter.
     *
     * @param gifProvider Loader class that extends {@link GifProviderProtocol}.
     */
    public void setGifProvider(@NonNull GifProviderProtocol gifProvider) {
        mGifFragment.setGifProvider(gifProvider);
        mGifSearchFragment.setGifProvider(gifProvider);
    }

    /**
     * Set the {@link EmoticonProvider} to render different images for unicode. If the value is null,
     * system emoticon images will render.
     *
     * @param emoticonProvider {@link EmoticonProvider}
     */
    public void setEmoticonProvider(@Nullable EmoticonProvider emoticonProvider) {
        mEmoticonFragment.setEmoticonProvider(emoticonProvider);
    }

    /**
     * Set the {@link EmoticonSelectListener} to get notify whenever the emoticon is selected or deleted.
     *
     * @param gifSelectListener {@link EmoticonSelectListener}
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

        changeLayoutFromTag(getChildFragmentManager().getBackStackEntryAt(index).getName());
    }

    private void changeLayoutFromTag(String tag) {
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
        }
    }
}
