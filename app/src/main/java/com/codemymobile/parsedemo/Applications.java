package com.codemymobile.parsedemo;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Akhilesh on 29-05-2017.
 */

public class Applications extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.app_id))
                .server(getString(R.string.server_url))
                .enableLocalDataStore()
                .build()
        );
    }



}
