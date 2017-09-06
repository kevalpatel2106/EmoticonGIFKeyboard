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

package com.kevalpatel2106.emoticongifkeyboard.internal.gif;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonSelectListener;
import com.kevalpatel2106.emoticongifkeyboard.gifs.Gif;
import com.kevalpatel2106.emoticongifkeyboard.gifs.GifProviderProtocol;
import com.kevalpatel2106.emoticongifkeyboard.gifs.GifSelectListener;
import com.kevalpatel2106.emoticongifkeyboard.internal.EmoticonGifImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass to provide search interface for GIF image using the {@link GifProviderProtocol}.
 * After initialization, this will display the list of trending GIFs. Once user enter the search
 * query, this will search for the GIFs from {@link GifProviderProtocol}.
 * This fragment is for internal use only.
 *
 * @author 'https://github.com/kevalpatel2106'
 */
public final class GifSearchFragment extends Fragment implements GifSearchAdapter.ItemSelectListener {

    /**
     * List of GIFs to display
     */
    private ArrayList<Gif> mGifs;

    /**
     * GIF adapter
     */
    private GifSearchAdapter mGifGridAdapter;

    /**
     * Listener to notify when gif selected.
     */
    @Nullable
    private GifSelectListener mGifSelectListener;

    /**
     * GifProviderProtocol to load the GIF from provider
     */
    private GifProviderProtocol mGifProvider;

    /**
     * View flipper to flip between GIFs list and no result found view.
     */
    private ViewFlipper mViewFlipper;

    /**
     * Async Task to search GIFs
     */
    private SearchGifTask mSearchTask;

    /**
     * Async Task to load trending GIFs
     */
    private TrendingGifTask mTrendingGifTask;

    /**
     * Error text view
     */
    private TextView mErrorTv;

    /**
     * GIF recycler view.
     */
    private RecyclerView mRecyclerView;

    /**
     * Search text input
     */
    private EditText mSearchEt;

    /**
     * Public constructor. Don't call constructor to create new instance. Use {@link #getNewInstance()}
     * instead.
     *
     * @see #getNewInstance()
     */
    public GifSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Get the new instance of {@link GifSearchFragment}. Use this method over calling constructor.
     * This function is for internal use only.
     *
     * @return {@link GifSearchFragment}
     */
    public static GifSearchFragment getNewInstance() {
        return new GifSearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gif_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGifs = new ArrayList<>();
        mViewFlipper = view.findViewById(R.id.gif_search_view_pager);
        mErrorTv = view.findViewById(R.id.error_tv);

        //Set the list
        mGifGridAdapter = new GifSearchAdapter(getActivity(), mGifs, this);
        mRecyclerView = view.findViewById(R.id.gif_search_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mGifGridAdapter);

        //Set the search interface
        mSearchEt = view.findViewById(R.id.search_box_et);
        mSearchEt.requestFocus();
        view.findViewById(R.id.search_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                GifSearchFragment.this.searchGif(mSearchEt.getText().toString());
            }
        });
        mSearchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                GifSearchFragment.this.searchGif(mSearchEt.getText().toString());
                return true;
            }
        });

        //Set up button
        EmoticonGifImageView backBtn = view.findViewById(R.id.up_arrow);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view12) {
                GifSearchFragment.this.hideKeyboard();

                mSearchEt.setText("");

                //Pop fragment from the back stack
                GifSearchFragment.this.getFragmentManager().popBackStackImmediate(EmoticonGIFKeyboardFragment.TAG_GIF_FRAGMENT, 0);
            }
        });

        showKeyboard();
    }

    @Override
    public void onResume() {
        super.onResume();
        mSearchEt.setText("");
        //Start loading trending GIFs
        if (mTrendingGifTask != null)
            mTrendingGifTask.cancel(true);
        mTrendingGifTask = new TrendingGifTask();
        mTrendingGifTask.execute();

    }

    /**
     * Show the keyboard.
     */

    private void showKeyboard() {
        //Show the keyboard
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.showSoftInput(mSearchEt, 0);
    }

    /**
     * Hide the keyboard.
     */
    private void hideKeyboard() {
        //Hide the keyboard
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.hideSoftInputFromWindow(mSearchEt.getWindowToken(), 0);
    }

    /**
     * Start searching the GIFs for given search query. This field should set before displaying the
     * fragment. This function is for internal use only.
     *
     * @param searchQuery Search query.
     */
    private void searchGif(@NonNull final String searchQuery) {
        if (searchQuery.length() > 0) {
            mTrendingGifTask.cancel(true);
            if (mSearchTask != null) mSearchTask.cancel(true);

            mSearchTask = new SearchGifTask();
            mSearchTask.execute(searchQuery);

            hideKeyboard();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSearchTask != null) mSearchTask.cancel(true);
    }

    /**
     * @param gif GIF selected from the recycler view.
     */
    @Override
    public void OnListItemSelected(@NonNull final Gif gif) {
        if (mGifSelectListener != null) mGifSelectListener.onGifSelected(gif);
    }

    /**
     * Set the GIF loader. This function is for internal use only.
     *
     * @param gifProvider {@link GifProviderProtocol}
     */
    @SuppressWarnings("ConstantConditions")
    public void setGifProvider(@NonNull final GifProviderProtocol gifProvider) {
        if (gifProvider == null) throw new RuntimeException("Set GIF loader.");
        mGifProvider = gifProvider;
    }

    /**
     * Set the {@link EmoticonSelectListener} to get notify whenever the emoticon is selected or deleted.
     *
     * @param gifSelectListener {@link EmoticonSelectListener}
     */
    public void setGifSelectListener(@Nullable final GifSelectListener gifSelectListener) {
        mGifSelectListener = gifSelectListener;
    }

    @SuppressLint("StaticFieldLeak")
    private class SearchGifTask extends AsyncTask<String, Void, List<Gif>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Display loading view.
            mViewFlipper.setDisplayedChild(0);
        }

        @Override
        protected List<Gif> doInBackground(final String... strings) {
            if (mGifProvider == null) throw new RuntimeException("Set GIF provider.");
            return mGifProvider.searchGifs(20, strings[0]);
        }

        @Override
        protected void onPostExecute(@Nullable final List<Gif> gifs) {
            super.onPostExecute(gifs);

            if (gifs == null) { //Error occurred.
                mErrorTv.setText(R.string.network_error);
                mViewFlipper.setDisplayedChild(2);
            } else if (gifs.isEmpty()) { //No result found.
                mErrorTv.setText(R.string.no_result_found);
                mViewFlipper.setDisplayedChild(2);
            } else {
                mViewFlipper.setDisplayedChild(1);

                //Load the tending gifs
                mGifs.clear();
                mGifs.addAll(gifs);
                mGifGridAdapter.notifyDataSetChanged();

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

    /**
     * Async task to load the list of trending GIFs.
     */
    @SuppressLint("StaticFieldLeak")
    private class TrendingGifTask extends AsyncTask<Void, Void, List<Gif>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Display loading view.
            mViewFlipper.setDisplayedChild(0);
        }

        @Override
        protected List<Gif> doInBackground(Void... voids) {
            if (mGifProvider == null) throw new RuntimeException("Set GIF provider.");
            return mGifProvider.getTrendingGifs(20);
        }

        @Override
        protected void onPostExecute(@Nullable final List<Gif> gifs) {
            super.onPostExecute(gifs);

            if (gifs == null) { //Error occurred.
                mErrorTv.setText(R.string.network_error);
                mViewFlipper.setDisplayedChild(2);
            } else if (gifs.isEmpty()) { //No result found.
                mErrorTv.setText(R.string.no_result_found);
                mViewFlipper.setDisplayedChild(2);
            } else {
                mViewFlipper.setDisplayedChild(1);

                //Load the tending gifs
                mGifs.clear();
                mGifs.addAll(gifs);
                mGifGridAdapter.notifyDataSetChanged();

                //Move to position 0.
                mRecyclerView.scrollToPosition(0);
            }
        }
    }
}
