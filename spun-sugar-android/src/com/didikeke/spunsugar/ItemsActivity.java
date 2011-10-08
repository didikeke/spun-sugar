package com.didikeke.spunsugar;

import java.io.IOException;
import java.util.List;

import com.didikeke.spunsugar.client.domain.Item;

public class ItemsActivity extends AbstractLoadActivity {

    @Override
    public List<Item> getRemoteData() throws IOException {
        List<Item> items = mClient.getItems();
        return items;
    }

}