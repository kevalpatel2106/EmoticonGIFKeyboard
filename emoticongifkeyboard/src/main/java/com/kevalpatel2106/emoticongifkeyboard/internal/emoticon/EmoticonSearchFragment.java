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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.kevalpatel2106.emoticongifkeyboard.EmoticonGIFKeyboardFragment;
import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.Emoticon;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonProvider;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonSelectListener;
import com.kevalpatel2106.emoticongifkeyboard.internal.EmoticonGifImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass to provide search interface for emoticon using the database.
 * After initialization, this will display the list of recent emoticons. Once user enter the search
 * query, this will search for the emoticons from database.
 * This class is for internal use only.
 *
 * @author 'https://github.com/kevalpatel2106'
 */
public final class EmoticonSearchFragment extends Fragment implements EmoticonSearchAdapter.ItemSelectListener {
    private Context mContext;

    /**
     * List of emoticons to display
     */
    private ArrayList<Emoticon> mEmoticons;

    /**
     * Emoticon adapter
     */
    private EmoticonSearchAdapter mAdapter;

    /**
     * Listener to notify when emoticons selected.
     */
    private EmoticonSelectListener mEmoticonSelectListener;

    /**
     * View flipper to flip between emoticon list and no result found view.
     */
    private ViewFlipper mViewFlipper;

    /**
     * Async Task to search emoticons
     */
    private SearchEmoticonTask mSearchTask;

    /**
     * Recycler view to display search result.
     */
    private RecyclerView mRecyclerView;

    /**
     * Search text input
     */
    private EditText mSearchEt;

    /**
     * Emoticon provider
     */
    @Nullable
    private EmoticonProvider mEmoticonProvider;

    /**
     * Public constructor. Don't call constructor to create new instance. Use {@link #getNewInstance()}
     * instead.
     *
     * @see #getNewInstance()
     */
    public EmoticonSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Get the new instance of {@link EmoticonSearchFragment}. Use this method over calling constructor.
     * This function is for internal use only.
     *
     * @return {@link EmoticonSearchFragment}
     */
    public static EmoticonSearchFragment getNewInstance() {
        return new EmoticonSearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emoticon_search, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewFlipper = view.findViewById(R.id.emoticon_search_view_pager);

        //Set the list.
        //When the fragment initialize display list of recent emoticons.
        mEmoticons = new ArrayList<>();
        mAdapter = new EmoticonSearchAdapter(getActivity(), mEmoticons, mEmoticonProvider, this);
        mRecyclerView = view.findViewById(R.id.emoticon_search_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mAdapter);

        //Set the search interface
        mSearchEt = view.findViewById(R.id.search_box_et);
        mSearchEt.requestFocus();
        mSearchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                searchEmoticon(mSearchEt.getText().toString());
                return true;
            }
        });
        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchEmoticon(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Do nothing
            }
        });

        //Set up button
        EmoticonGifImageView backBtn = view.findViewById(R.id.up_arrow);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                EmoticonSearchFragment.this.hideKeyboard();

                mSearchEt.setText("");

                //Pop fragment from the back stack
                EmoticonSearchFragment.this.getFragmentManager().popBackStackImmediate(EmoticonGIFKeyboardFragment.TAG_EMOTICON_FRAGMENT, 0);
            }
        });

        //Open the keyboard.
        showKeyboard();
    }

    @Override
    public void onResume() {
        super.onResume();
        mEmoticons.clear();
        mEmoticons.addAll(EmoticonRecentManager.getInstance(mContext).getRecentEmoticons());
        mSearchEt.setText("");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSearchTask != null) mSearchTask.cancel(true);
    }

    /**
     * Search the emoticon based on the search query. If the length of query is 0 or null, it will
     * display recent emoticon or else it will search into database using
     * {@link EmoticonSearchFragment.SearchEmoticonTask}.
     *
     * @param searchQuery Query text to search.
     * @see EmoticonSearchFragment.SearchEmoticonTask
     */
    private void searchEmoticon(@Nullable final String searchQuery) {
        //Cancel previously running search task
        if (mSearchTask != null) mSearchTask.cancel(true);

        //If the search query is more than one character...
        if (searchQuery != null && searchQuery.length() > 0) {

            //Search for the emoticon
            mSearchTask = new SearchEmoticonTask();
            mSearchTask.execute(searchQuery);
        } else {

            //Show the recent emoticons
            //If there are no recent emoticons, it will show blank list
            mEmoticons.clear();
            mEmoticons.addAll(EmoticonRecentManager.getInstance(mContext).getRecentEmoticons());
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Show the keyboard.
     */
    @SuppressWarnings("ConstantConditions")
    private void showKeyboard() {
        //Show the keyboard
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mSearchEt, 0);
    }

    /**
     * Hide the keyboard.
     */
    @SuppressWarnings("ConstantConditions")
    private void hideKeyboard() {
        //Hide the keyboard
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mSearchEt.getWindowToken(), 0);
    }

    @Override
    public void OnEmoticonSelected(@NonNull Emoticon emoticon) {
        if (mEmoticonSelectListener != null) mEmoticonSelectListener.emoticonSelected(emoticon);
    }

    /**
     * Set the {@link EmoticonSelectListener} to get notify whenever the emoticon is selected or deleted.
     * This function is for internal use only.
     *
     * @param emoticonSelectListener {@link EmoticonSelectListener}
     */
    public void setEmoticonSelectListener(@NonNull EmoticonSelectListener emoticonSelectListener) {
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
     * Async task to load search emoticons into database in background and refresh the list in main
     * thread.
     */
    @SuppressLint("StaticFieldLeak")
    private class SearchEmoticonTask extends AsyncTask<String, Void, List<Emoticon>> {

        @Override
        protected List<Emoticon> doInBackground(final String... strings) {
            //Search for the tag in database.
            return new EmoticonDbHelper(mContext).searchEmoticons(strings[0], mEmoticonProvider);
        }

        @Override
        protected void onPostExecute(@NonNull final List<Emoticon> emoticons) {
            super.onPostExecute(emoticons);

            if (emoticons.isEmpty()) { //No result found.
                mViewFlipper.setDisplayedChild(1);
            } else {
                mViewFlipper.setDisplayedChild(0);

                //Load the searched emoticons
                mEmoticons.clear();
                mEmoticons.addAll(emoticons);
                mAdapter.notifyDataSetChanged();

                //Move to position 0.
                mRecyclerView.scrollToPosition(0);
            }

            mSearchTask = null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            mSearchTask = null;
        }
    }
}
