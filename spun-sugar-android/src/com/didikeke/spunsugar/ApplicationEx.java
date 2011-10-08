package com.didikeke.spunsugar;

import android.app.Application;

import com.didikeke.spunsugar.client.SpunSugarClient;

public class ApplicationEx extends Application {

    private SpunSugarClient client;

    public SpunSugarClient getClient() {
        return client;
    }

    public void setClient(SpunSugarClient client) {
        this.client = client;
    }

}
