package com.didikeke.spunsugar;

import java.io.IOException;
import java.util.List;

import com.didikeke.spunsugar.client.SpunSugarClient;
import com.didikeke.spunsugar.client.domain.Item;


public class HoldsActivity extends AbstractLoadActivity {

    @Override
    public List<Item> getCachedData() {
        return mApp.getHolds();
    }

    @Override
    public List<Item> getRemoteData() throws IOException {
        SpunSugarClient client = mApp.getClient();
        List<Item> holds = client.getHolds();
        mApp.setHolds(holds);
        return holds;
    }

 
}