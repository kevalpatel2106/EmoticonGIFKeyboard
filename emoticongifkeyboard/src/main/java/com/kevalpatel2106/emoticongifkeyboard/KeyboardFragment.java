package com.kevalpatel2106.emoticongifkeyboard;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ViewFlipper;

import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonSelectListener;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.internal.EmoticonFragment;
import com.kevalpatel2106.emoticongifkeyboard.gifs.GifProviderProtocol;
import com.kevalpatel2106.emoticongifkeyboard.gifs.internal.EmoticonGifImageView;
import com.kevalpatel2106.emoticongifkeyboard.gifs.internal.GifFragment;


/**
 * A simple {@link Fragment} subclass. This fragment will host the smiles and gifs.
 */
public final class KeyboardFragment extends Fragment {
    //Fragments to load.
    private final EmoticonFragment mEmoticonFragment;
    private final GifFragment mGifFragment;

    //View pager to load Emoticons and GIFs
    private ViewPager mMainViewPager;

    //Tabs
    private View mEmoticonTab;
    private View mGifTab;

    //Backspace button
    private EmoticonGifImageView mBackSpaceBtn;

    //Listener to notify when emoticons selected.
    private EmoticonSelectListener mEmoticonSelectListener;

    //Bottom view flipper
    private ViewFlipper mBottomFlipper;

    public KeyboardFragment() {
        //Initiate emoticon fragment.
        mEmoticonFragment = EmoticonFragment.getNewInstance();
        mGifFragment = GifFragment.getNewInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keyboard, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBottomFlipper = view.findViewById(R.id.bottom_view_flipper);
        mEmoticonTab = view.findViewById(R.id.btn_emoji_tab);
        mGifTab = view.findViewById(R.id.btn_gif_tab);

        //Set search close button
        EmoticonGifImageView backBtn = view.findViewById(R.id.up_arrow);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomFlipper.setDisplayedChild(0);

                //Hide the keyboard
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        //Set backspace button
        setBackSpace(view);

        //Set the search button
        setSearchBtn(view);

        setViewPager(view);

        //Set the click listener for Emoticons
        mEmoticonTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainViewPager.setCurrentItem(ViewPagerAdapter.POS_EMOTICON);
                setTabIcons();
            }
        });

        //Set the click listener for GIF
        mGifTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainViewPager.setCurrentItem(ViewPagerAdapter.POS_GIF);
                setTabIcons();
            }
        });
    }

    /**
     * Set the viewpager. There will be two pages in view pager.
     *
     * @param rootView Root view.
     */
    private void setViewPager(@NonNull View rootView) {
        mMainViewPager = rootView.findViewById(R.id.main_view_pager);
        mMainViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        mMainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Do nothing
            }

            @Override
            public void onPageSelected(int position) {
                setTabIcons();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Do nothing
            }
        });

        //Select Emoticon fragment by default
        mMainViewPager.setCurrentItem(ViewPagerAdapter.POS_EMOTICON);
        setTabIcons();
    }

    /**
     * Set the tab icons tint and backspace button visibility based on the currently selected page in
     * {@link #mMainViewPager}.
     */
    private void setTabIcons() {
        mGifTab.setSelected(mMainViewPager.getCurrentItem() == ViewPagerAdapter.POS_GIF);
        mBackSpaceBtn.setVisibility(mMainViewPager.getCurrentItem() == ViewPagerAdapter.POS_EMOTICON ?
                View.VISIBLE : View.GONE);
        mEmoticonTab.setSelected(mMainViewPager.getCurrentItem() == ViewPagerAdapter.POS_EMOTICON);
    }

    /**
     * Set the click lister for the backspace.
     *
     * @param rootView Root view.
     */
    private void setBackSpace(@NonNull View rootView) {
        mBackSpaceBtn = rootView.findViewById(R.id.emojis_backspace);
        mBackSpaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEmoticonSelectListener != null) mEmoticonSelectListener.onBackSpace();
            }
        });
    }

    /**
     * Set the click lister for the backspace.
     *
     * @param rootView Root view.
     */
    private void setSearchBtn(@NonNull View rootView) {
        rootView.findViewById(R.id.search_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Search view.
                        mBottomFlipper.setDisplayedChild(1);
                    }
                });
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
     * @param gifLoader Loader class that extends {@link GifProviderProtocol}.
     */
    public void setGifProvider(@NonNull GifProviderProtocol gifLoader) {
        mGifFragment.setGifLoader(gifLoader);
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
     * {@link FragmentStatePagerAdapter} for view pager. There are two tabs:
     * <li>Emoticon</li>
     * <li>GIFs</li>
     */
    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private static final int POS_EMOTICON = 0;
        private static final int POS_GIF = 1;

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case POS_EMOTICON:  //Emoticons
                    return mEmoticonFragment;
                case POS_GIF:       //GIFs
                    return mGifFragment;
                default:
                    throw new IllegalStateException("Invalid position.");
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
