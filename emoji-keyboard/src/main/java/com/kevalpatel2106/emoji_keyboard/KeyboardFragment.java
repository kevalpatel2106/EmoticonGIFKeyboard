package com.kevalpatel2106.emoji_keyboard;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevalpatel2106.emoji_keyboard.internal.emoticons.EmoticonFragment;


/**
 * A simple {@link Fragment} subclass. This fragment will host the smiles and gifs.
 */
public class KeyboardFragment extends Fragment {
    private Context mContext;
    private View mRootView;

    private EmoticonFragment mEmoticonFragment;

    public KeyboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initiate emoticon fragment.
        mEmoticonFragment = EmoticonFragment.getNewInstance();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
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
        mRootView = view.findViewById(R.id.root_view);

        //Set the click listener for Emoticons
        view.findViewById(R.id.btn_emoji_tab)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getChildFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_container, mEmoticonFragment)
                                .commit();
                    }
                });

        //Set the click listener for GIF
        view.findViewById(R.id.btn_gif_tab)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //TODO display GIF.
                        getChildFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_container, mEmoticonFragment)
                                .commit();
                    }
                });
    }


}
