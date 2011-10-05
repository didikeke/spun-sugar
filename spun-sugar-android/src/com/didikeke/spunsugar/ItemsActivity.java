package com.didikeke.spunsugar;

import java.io.IOException;
import java.util.List;

import com.didikeke.spunsugar.client.SpunSugarClient;
import com.didikeke.spunsugar.client.domain.Item;

public class ItemsActivity extends AbstractLoadActivity {

    @Override
    public List<Item> getCachedData() {
        return mApp.getItems();
    }

    @Override
    public List<Item> getRemoteData() throws IOException {
        SpunSugarClient client = mApp.getClient();
        List<Item> items = client.getItems();
        mApp.setItems(items);
        return items;
    }

}