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

    /**
     * Public constructor.
     */
    public KeyboardFragment() {
        mEmoticonFragment = EmoticonFragment.getNewInstance();
        mEmoticonSearchFragment = EmoticonSearchFragment.getNewInstance();

        mGifFragment = GifFragment.getNewInstance();
        mGifSearchFragment = GifSearchFragment.getNewInstance();
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
        final View backSpaceBtn = view.findViewById(R.id.emojis_backspace);
        backSpaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEmoticonSelectListener != null) mEmoticonSelectListener.onBackSpace();
            }
        });

        //Set the tabs
        final View emoticonTab = view.findViewById(R.id.btn_emoji_tab);
        final View gifTab = view.findViewById(R.id.btn_gif_tab);
        emoticonTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.keyboard_fragment_container, mEmoticonFragment)
                        .addToBackStack(TAG_EMOTICON_FRAGMENT)
                        .commit();

                //Set the tab
                emoticonTab.setSelected(true);
                gifTab.setSelected(!emoticonTab.isSelected());
                backSpaceBtn.setVisibility(View.VISIBLE);
            }
        });
        emoticonTab.callOnClick();
        gifTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.keyboard_fragment_container, mGifFragment)
                        .addToBackStack(TAG_GIF_FRAGMENT)
                        .commit();

                //Set the tab
                emoticonTab.setSelected(false);
                gifTab.setSelected(!emoticonTab.isSelected());
                backSpaceBtn.setVisibility(View.GONE);
            }
        });

        //Setup the search button.
        view.findViewById(R.id.search_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emoticonTab.isSelected()) {
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.keyboard_fragment_container, mEmoticonSearchFragment)
                            .addToBackStack(TAG_EMOTICON_SEARCH_FRAGMENT)
                            .commit();
                } else {
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.keyboard_fragment_container, mGifSearchFragment)
                            .addToBackStack(TAG_GIF_SEARCH_FRAGMENT)
                            .commit();
                }
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
     * Handle the backstack changes in the fragment container.
     */
    @Override
    public void onBackStackChanged() {
        int index = getChildFragmentManager().getBackStackEntryCount() - 1;
        if (index < 0) return;

        switch (getChildFragmentManager().getBackStackEntryAt(index).getName()) {
            case TAG_EMOTICON_FRAGMENT:
                //Display bottom bar of not displayed.
                if (mBottomViewContainer != null) mBottomViewContainer.setVisibility(View.VISIBLE);
                break;
            case TAG_GIF_FRAGMENT:
                //Display bottom bar of not displayed.
                if (mBottomViewContainer != null) mBottomViewContainer.setVisibility(View.VISIBLE);
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
