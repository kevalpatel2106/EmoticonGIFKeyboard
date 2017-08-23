package com.kevalpatel2106.emoticongifkeyboard.gifs.internal;


import android.content.Context;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.kevalpatel2106.emoticongifkeyboard.R;
import com.kevalpatel2106.emoticongifkeyboard.emoticons.EmoticonSelectListener;
import com.kevalpatel2106.emoticongifkeyboard.gifs.Gif;
import com.kevalpatel2106.emoticongifkeyboard.gifs.GifProviderProtocol;
import com.kevalpatel2106.emoticongifkeyboard.gifs.GifSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This {@link Fragment} will display the list of trending GIFs in a grid.
 */
public final class GifFragment extends Fragment implements GifGridAdapter.ItemSelectListener {
    private static final String OUT_STATE_GIFS = "out_state_gifs";

    private Context mContext;

    /* Array list to hold currently displaying emoticons list */
    private ArrayList<Gif> mGifs;

    /* Adapter to display emoticon grids. */
    private GifGridAdapter mGifGridAdapter;

    /* View flipper for show different states. */
    private ViewFlipper mViewFlipper;

    /* Gif loader protocol */
    private GifProviderProtocol mGifProvider;

    /* Error text view. */
    private TextView mErrorTv;

    /* Async task to load the trending GIFs. */
    private AsyncTask<Void, Void, List<Gif>> mTrendingGifTask;

    /* Listener to notify when any gif select */
    private GifSelectListener mGifSelectListener;

    public GifFragment() {
        // Required empty public constructor
    }

    /**
     * Get new instance of {@link GifFragment}. This function is for internal use only.
     *
     * @return {@link GifFragment}
     */
    public static GifFragment getNewInstance() {
        return new GifFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mGifs = new ArrayList<>();
        } else {
            mGifs = savedInstanceState.getParcelableArrayList(OUT_STATE_GIFS);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gif, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @SuppressWarnings("DanglingJavadoc")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set the progressbar
        ProgressBar progressBar = view.findViewById(R.id.loading_progressbar);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(ContextCompat.getColor(mContext, R.color.icon_selected),
                        PorterDuff.Mode.SRC_ATOP);

        mViewFlipper = view.findViewById(R.id.gif_view_flipper);
        mErrorTv = view.findViewById(R.id.error_textview);

        //Set the grid view
        mGifGridAdapter = new GifGridAdapter(mContext, mGifs, this);

        /**
         * Instead of using recycler view,we are stick to the GRidView here because in recycler view
         * items from the grid column are not getting displayed until user scrolls up-down. There is
         * no solution found for this problem,
         * @see https://stackoverflow.com/questions/29460164/recyclerview-refreshes-items-only-when-scrolling-down-and-up
         */
        GridView gridView = view.findViewById(R.id.gif_gridView);
        gridView.setAdapter(mGifGridAdapter);

        //Load the list of trending GIFs.
        if (mGifs.isEmpty()) {
            if (mTrendingGifTask != null) mTrendingGifTask.cancel(true);
            mTrendingGifTask = new TrendingGifTask();
            mTrendingGifTask.execute();
            mViewFlipper.setDisplayedChild(0);
        } else {
            mViewFlipper.setDisplayedChild(1);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(OUT_STATE_GIFS, mGifs);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Cancel trending GIF.
        if (mTrendingGifTask != null) mTrendingGifTask.cancel(true);
    }

    @Override
    public void OnListItemSelected(@NonNull Gif gif) {
        if (mGifSelectListener != null) mGifSelectListener.onGifSelected(gif);
    }

    /**
     * Set the GIF loader. This function is for internal use only.
     *
     * @param gifProvider {@link GifProviderProtocol}
     */
    @SuppressWarnings("ConstantConditions")
    public void setGifProvider(@NonNull GifProviderProtocol gifProvider) {
        mGifProvider = gifProvider;
    }

    /**
     * Set the {@link EmoticonSelectListener} to get notify whenever the emoticon is selected or deleted.
     *
     * @param gifSelectListener {@link EmoticonSelectListener}
     */
    public void setGifSelectListener(@NonNull GifSelectListener gifSelectListener) {
        mGifSelectListener = gifSelectListener;
    }


    /**
     * Async task to load the list of trending GIFs.
     */
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
        protected void onPostExecute(@Nullable List<Gif> gifs) {
            super.onPostExecute(gifs);

            if (gifs == null) { //Error occurred.
                mErrorTv.setText(R.string.network_error);
                mViewFlipper.setDisplayedChild(2);
            } else if (gifs.isEmpty()) { //No result found.
                mErrorTv.setText(R.string.no_gif_found);
                mViewFlipper.setDisplayedChild(2);
            } else {
                mViewFlipper.setDisplayedChild(1);

                //Load the tending gifs
                mGifs.clear();
                mGifs.addAll(gifs);
                mGifGridAdapter.notifyDataSetChanged();
            }
        }
    }
}
