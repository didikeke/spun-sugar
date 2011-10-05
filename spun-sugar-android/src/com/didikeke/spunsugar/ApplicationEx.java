package com.didikeke.spunsugar;

import java.util.List;

import android.app.Application;

import com.didikeke.spunsugar.client.SpunSugarClient;
import com.didikeke.spunsugar.client.domain.Item;

public class ApplicationEx extends Application {

    private SpunSugarClient client;
    private List<Item> items;
    private List<Item> holds;
    private List<Item> readingHistory;

    public SpunSugarClient getClient() {
        return client;
    }

    public void setClient(SpunSugarClient client) {
        this.client = client;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getHolds() {
        return holds;
    }

    public void setHolds(List<Item> holds) {
        this.holds = holds;
    }

    public List<Item> getReadingHistory() {
        return readingHistory;
    }

    public void setReadingHistory(List<Item> readingHistory) {
        this.readingHistory = readingHistory;
    }

}
