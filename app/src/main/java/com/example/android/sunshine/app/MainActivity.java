package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "*** on create *** ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new ForecastFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (item.getItemId() == R.id.action_viewmap) {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            String location = sharedPref.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));

            Uri geoLocation = Uri.parse("geo:0,0").buildUpon().appendQueryParameter("q", location).build();
            viewOnMap(geoLocation);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void viewOnMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, getString(R.string.nomap_found), Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    protected void onPause() {
        Log.i(LOG_TAG, "**** on pause **** ");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.i(LOG_TAG, "**** on resume **** ");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.i(LOG_TAG, "**** on stop **** ");
        super.onStop();
    }

    @Override
    protected void onStart() {
        Log.i(LOG_TAG, "**** on start **** ");
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        Log.i(LOG_TAG, "**** on destroy **** ");
        super.onDestroy();
    }
}