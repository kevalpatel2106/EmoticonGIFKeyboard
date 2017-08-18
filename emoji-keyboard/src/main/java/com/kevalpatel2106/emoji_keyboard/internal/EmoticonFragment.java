package com.kevalpatel2106.emoji_keyboard.internal;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.kevalpatel2106.emoji_keyboard.EmoticonSelectListener;
import com.kevalpatel2106.emoji_keyboard.R;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Cars;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Electr;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Emojicon;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Food;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Nature;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.People;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Sport;
import com.kevalpatel2106.emoji_keyboard.internal.emoji.Symbols;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmoticonFragment extends Fragment {
    private Context mContext;

    //Array list to hold currently displaying emoticons list
    private List<Emojicon> mEmoticons;

    //Adapter to display emoticon grids.
    private EmoticonGridAdapter mEmoticonGridAdapter;

    private EmoticonSelectListener mEmoticonSelectListener;

    //set the emoticon click listener
    private AdapterView.OnItemClickListener mOnEmoticonSelectedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            //Notify the emoticon
            if (mEmoticonSelectListener != null)
                mEmoticonSelectListener.emoticonSelected(mEmoticonGridAdapter.getItem(position));
        }
    };

    public EmoticonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public static EmoticonFragment getNewInstance() {
        return new EmoticonFragment();
    }

    public void setEmoticonSelectListener(EmoticonSelectListener emoticonSelectListener) {
        mEmoticonSelectListener = emoticonSelectListener;
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
        GridView gridView = view.findViewById(R.id.emoji_gridView);
        mEmoticons = getEmoticonsList(0);
        mEmoticonGridAdapter = new EmoticonGridAdapter(mContext, mEmoticons, false);
        gridView.setAdapter(mEmoticonGridAdapter);
        gridView.setOnItemClickListener(mOnEmoticonSelectedListener);

        //Set headers
        setTabHeaders(view);
    }

    /**
     * Set the tab headers with categories of emoticons and back space button.
     *
     * @param view Root view.
     */
    private void setTabHeaders(@NonNull View view) {
        final View[] emojiTabs = new View[8];
        emojiTabs[0] = view.findViewById(R.id.emojis_tab_0_recents);
        emojiTabs[1] = view.findViewById(R.id.emojis_tab_1_people);
        emojiTabs[2] = view.findViewById(R.id.emojis_tab_2_nature);
        emojiTabs[3] = view.findViewById(R.id.emojis_tab_3_food);
        emojiTabs[4] = view.findViewById(R.id.emojis_tab_4_sport);
        emojiTabs[5] = view.findViewById(R.id.emojis_tab_5_cars);
        emojiTabs[6] = view.findViewById(R.id.emojis_tab_6_elec);
        emojiTabs[7] = view.findViewById(R.id.emojis_tab_7_sym);

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
                    mEmoticons.addAll(getEmoticonsList(position));
                    mEmoticonGridAdapter.notifyDataSetChanged();
                }
            });
        }

        //Select recent tabs selected while creating new instance
        emojiTabs[0].setSelected(true);

        //TODO handle back space
    }

    private List<Emojicon> getEmoticonsList(int position) {
        switch (position) {
            case 0:
                return new ArrayList<>();
            case 1:
                return Arrays.asList(People.DATA);
            case 2:
                return Arrays.asList(Nature.DATA);
            case 3:
                return Arrays.asList(Food.DATA);
            case 4:
                return Arrays.asList(Sport.DATA);
            case 5:
                return Arrays.asList(Cars.DATA);
            case 6:
                return Arrays.asList(Electr.DATA);
            case 7:
                return Arrays.asList(Symbols.DATA);
            default:
                return new ArrayList<>(0);
        }
    }
}
