package com.example.android.sunshine.app;

//import android.content.Intent;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

import static android.content.Intent.getIntent;

public class DetailFragment extends Fragment {

    private static final String LOG_TAG = DetailFragment.class.getSimpleName();;
    private ShareActionProvider mShareActionProvider;
    public static final String FORECAST_HASHTAG = "#sunshineapp";
    private String forecastStr;


    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void setHasOptionsMenu(boolean hasMenu) {
        super.setHasOptionsMenu(hasMenu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.detail_fragment, menu);

        // Retrieve the share menu item
        MenuItem menuItem = menu.findItem(R.id.menu_item_share);

        // Get the provider and hold onto it to set/change the share intent.
        ShareActionProvider mShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        // Attach an intent to this ShareActionProvider.  You can update this at any time,
        // like when the user selects a new piece of data they might like to share.
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareIntent());
        } else {
            Log.d(LOG_TAG, "Share Action Provider is null?");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        forecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.detail_text);
        textView.setText(forecastStr);
        return rootView;
    }

    private Intent createShareIntent() {

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, forecastStr + FORECAST_HASHTAG);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");

        return shareIntent;
    }

}
