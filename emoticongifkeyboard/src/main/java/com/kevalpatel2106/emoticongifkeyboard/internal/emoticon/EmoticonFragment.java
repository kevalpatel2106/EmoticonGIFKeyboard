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

package com.kevalpatel2106.emoticongifkeyboard.internal.emoticon;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonSelectListener;


/**
 * A {@link Fragment} subclass to load the list of emoticons and display them based on the categories.
 *
 * @author 'https://github.com/kevalpatel2106'
 */
public final class EmoticonFragment extends Fragment {
    /***
     * Total number of tabs in emoticons.
     */
    private static final int TOTAL_TABS = 9;

    /**
     * Instance of caller.
     */
    private Context mContext;

    /**
     * Listener to notify when emoticons selected.
     */
    @Nullable
    private EmoticonSelectListener mEmoticonSelectListener;

    /**
     * Recently used emoticons.
     */
    private EmoticonRecentManager mEmoticonRecentManager;

    /**
     * Emoticon provider
     */
    @Nullable
    private EmoticonProvider mEmoticonProvider;

    private ViewPager mViewPager;

    /**
     * Public constructor. Don't call constructor to create new instance. Use {@link #getNewInstance()}
     * instead.
     *
     * @see #getNewInstance()
     */
    public EmoticonFragment() {
        // Required empty public constructor
    }

    /**
     * Get the new instance of {@link EmoticonFragment}. Use this method over calling constructor.
     * This function is for internal use only.
     *
     * @return {@link EmoticonFragment}
     */
    public static EmoticonFragment getNewInstance() {
        return new EmoticonFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEmoticonRecentManager = EmoticonRecentManager.getInstance(mContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emoticon, container, false);
    }


    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);

        mViewPager = rootView.findViewById(R.id.emoji_category_view_pager);
        mViewPager.setAdapter(new EmoticonCategoryViewpagerAdapter(getChildFragmentManager()));

        final View[] emojiTabs = new View[TOTAL_TABS];
        emojiTabs[EmoticonsCategories.RECENT] = rootView.findViewById(R.id.emojis_tab_0_recents);
        emojiTabs[EmoticonsCategories.PEOPLE] = rootView.findViewById(R.id.emojis_tab_1_people);
        emojiTabs[EmoticonsCategories.NATURE] = rootView.findViewById(R.id.emojis_tab_2_nature);
        emojiTabs[EmoticonsCategories.FOOD] = rootView.findViewById(R.id.emojis_tab_3_food);
        emojiTabs[EmoticonsCategories.ACTIVITY] = rootView.findViewById(R.id.emojis_tab_4_sport);
        emojiTabs[EmoticonsCategories.TRAVEL] = rootView.findViewById(R.id.emojis_tab_5_cars);
        emojiTabs[EmoticonsCategories.OBJECTS] = rootView.findViewById(R.id.emojis_tab_6_elec);
        emojiTabs[EmoticonsCategories.SYMBOLS] = rootView.findViewById(R.id.emojis_tab_7_sym);
        emojiTabs[EmoticonsCategories.FLAGS] = rootView.findViewById(R.id.emojis_tab_8_flag);

        //Set the click listener in each tab
        for (int i = 0; i < emojiTabs.length; i++) {
            final int position = i;
            emojiTabs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Mark current tab as selected.
                    for (View emojiTab : emojiTabs) emojiTab.setSelected(false);
                    v.setSelected(true);

                    mViewPager.setCurrentItem(position);

                    //Save the selected category
                    mEmoticonRecentManager.setLastCategory(position);
                }
            });
        }

        //Add the view pager listener to handle page change.
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Do nothing
            }

            @Override
            public void onPageSelected(int position) {
                //Mark current tab as selected.
                for (View emojiTab : emojiTabs) emojiTab.setSelected(false);
                emojiTabs[position].setSelected(true);

                //Save the selected category
                //noinspection WrongConstant
                mEmoticonRecentManager.setLastCategory(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Do nothing
            }
        });
        //Select recent tabs selected while creating new instance
        emojiTabs[mEmoticonRecentManager.getLastCategory()].setSelected(true);
        mViewPager.setCurrentItem(mEmoticonRecentManager.getLastCategory());
    }

    /**
     * Set the {@link EmoticonSelectListener} to get notify whenever the emoticon is selected or deleted.
     *
     * @param emoticonSelectListener {@link EmoticonSelectListener}
     */
    @SuppressWarnings("ConstantConditions")
    public void setEmoticonSelectListener(@Nullable EmoticonSelectListener emoticonSelectListener) {
        mEmoticonSelectListener = emoticonSelectListener;
    }

    /**
     * Set the {@link EmoticonProvider} to render different images for unicode. If the value is null,
     * system emoticon images will render.
     *
     * @param emoticonProvider {@link EmoticonProvider}
     */
    public void setEmoticonProvider(@Nullable EmoticonProvider emoticonProvider) {
        mEmoticonProvider = emoticonProvider;
    }

    /**
     * An adapter class to bind the view pager with {@link EmoticonGridFragment} to display the
     * grid of {@link com.kevalpatel2106.emoticongifkeyboard.emoticons.Emoticon} for given category.
     *
     * @see FragmentStatePagerAdapter
     */
    private class EmoticonCategoryViewpagerAdapter extends FragmentStatePagerAdapter {

        /**
         * Constructor.
         *
         * @param fm {@link FragmentManager}
         */
        EmoticonCategoryViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return EmoticonGridFragment.newInstance(position,   //Position. Same as emoticon category id
                    mEmoticonSelectListener,    //Callback listener
                    mEmoticonProvider);         //Emoticon icon provider.
        }

        @Override
        public int getCount() {
            return TOTAL_TABS;
        }
    }
}
