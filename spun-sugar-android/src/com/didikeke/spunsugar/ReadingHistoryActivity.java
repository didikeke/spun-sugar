package com.didikeke.spunsugar;

import java.io.IOException;
import java.util.List;

import com.didikeke.spunsugar.client.domain.Item;


public class ReadingHistoryActivity extends AbstractLoadActivity {

    @Override
    public List<Item> getRemoteData() throws IOException {
        List<Item> readingHistory = mClient.getReadingHistory();
        return readingHistory;
    }

 
}