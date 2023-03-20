package com.example.myapplication;

import androidx.multidex.MultiDexApplication;

import im.crisp.client.Crisp;

/**
 * Created by baptistejamin on 23/05/2017.
 */

public class Application extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        Crisp.configure(getApplicationContext(), "4a1e20bf-b3bc-43e5-8cfe-3b56deaf874d");
    }
}
