package com.kevalpatel2106.emoticongifkeyboard.emoticons.internal;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevalpatel2106.emoticongifkeyboard.EmoticonSelectListener;
import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.Emoticon;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 *
 * @author <a href='https://github.com/kevalpatel2106'>Kevalpatel2106</a>
 */
public final class EmoticonFragment extends Fragment implements EmoticonAdapter.ItemSelectListener {
    private Context mContext;

    //Array list to hold currently displaying emoticons list
    private ArrayList<Emoticon> mEmoticons;

    //Adapter to display emoticon grids.
    private EmoticonAdapter mEmoticonAdapter;

    //Listener to notify when emoticons selected.
    private EmoticonSelectListener mEmoticonSelectListener;

    //Recently used emoticons.
    private EmoticonRecentManager mEmoticonRecentManager;

    //Emoticon provider
    @Nullable
    private EmoticonProvider mEmoticonProvider;
    private RecyclerView mRecyclerView;

    public EmoticonFragment() {
        // Required empty public constructor
    }

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

        //Check if we need to load emoticons to the database.
        if (new EmoticonDbHelper(mContext).getCount() <= 0) new EmoticonLoader(mContext).execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emoticon, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set the grid view
        mEmoticons = new ArrayList<>();
        mEmoticons.addAll(getEmoticonsList(mEmoticonRecentManager.getLastCategory()));
        mEmoticonAdapter = new EmoticonAdapter(mContext, mEmoticons, mEmoticonProvider, this);

        mRecyclerView = view.findViewById(R.id.emoji_gridView);
        mRecyclerView.setAdapter(mEmoticonAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 8));

        //Set headers
        setTabHeaders(view);
    }

    /**
     * Set the tab headers with categories of emoticons and back space button.
     *
     * @param rootView Root view.
     */
    private void setTabHeaders(@NonNull View rootView) {
        final View[] emojiTabs = new View[9];
        emojiTabs[EmoticonsCategories.RECENT] = rootView.findViewById(R.id.emojis_tab_0_recents);
        emojiTabs[EmoticonsCategories.PEOPLE] = rootView.findViewById(R.id.emojis_tab_1_people);
        emojiTabs[EmoticonsCategories.NATURE] = rootView.findViewById(R.id.emojis_tab_2_nature);
        emojiTabs[EmoticonsCategories.FOOD] = rootView.findViewById(R.id.emojis_tab_3_food);
        emojiTabs[EmoticonsCategories.SPORT] = rootView.findViewById(R.id.emojis_tab_4_sport);
        emojiTabs[EmoticonsCategories.CARS] = rootView.findViewById(R.id.emojis_tab_5_cars);
        emojiTabs[EmoticonsCategories.ELECTRIC] = rootView.findViewById(R.id.emojis_tab_6_elec);
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

                    //Update the grid with emoticons for that category
                    mEmoticons.clear();
                    //noinspection WrongConstant
                    mEmoticons.addAll(getEmoticonsList(position));
                    mEmoticonAdapter.notifyDataSetChanged();

                    //Scroll the list to top.
                    mRecyclerView.scrollToPosition(0);

                    //Save the selected category
                    //noinspection WrongConstant
                    mEmoticonRecentManager.setLastCategory(position);
                }
            });
        }

        //Select recent tabs selected while creating new instance
        emojiTabs[mEmoticonRecentManager.getLastCategory()].setSelected(true);
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
    }

    /**
     * Get the emoticons list for the selected category.
     *
     * @param category category id.
     * @return List of {@link Emoticon}
     */
    private List<Emoticon> getEmoticonsList(@EmoticonsCategories.EmoticonsCategory int category) {
        switch (category) {
            case EmoticonsCategories.RECENT:
                return mEmoticonRecentManager.getRecentEmoticons();
            case EmoticonsCategories.PEOPLE:
            case EmoticonsCategories.NATURE:
            case EmoticonsCategories.FOOD:
            case EmoticonsCategories.SPORT:
            case EmoticonsCategories.CARS:
            case EmoticonsCategories.ELECTRIC:
            case EmoticonsCategories.SYMBOLS:
            case EmoticonsCategories.FLAGS:
                return new EmoticonDbHelper(mContext).getEmoticons(category);
            default:
                throw new IllegalStateException("Invalid position.");
        }
    }


    @Override
    public void OnEmoticonSelected(@NonNull Emoticon emoticon) {
        //Notify the emoticon
        if (mEmoticonSelectListener != null)
            mEmoticonSelectListener.emoticonSelected(emoticon);

        //Save the emoticon to the recent list
        mEmoticonRecentManager.add(emoticon);
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
}
