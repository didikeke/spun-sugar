package com.didikeke.spunsugar;

import java.io.IOException;
import java.util.List;

import com.didikeke.spunsugar.client.SpunSugarClient;
import com.didikeke.spunsugar.client.domain.Item;


public class ReadingHistoryActivity extends AbstractLoadActivity {

    @Override
    public List<Item> getCachedData() {
        return mApp.getReadingHistory();
    }

    @Override
    public List<Item> getRemoteData() throws IOException {
        SpunSugarClient client = mApp.getClient();
        List<Item> readingHistory = client.getReadingHistory();
        mApp.setReadingHistory(readingHistory);
        return readingHistory;
    }

 
}