package com.didikeke.spunsugar.util;

import static com.didikeke.spunsugar.client.util.TextUtils.trimDate;
import static com.didikeke.spunsugar.client.util.TextUtils.trimTitle;

import java.util.List;

import com.didikeke.spunsugar.client.domain.Item;

public class ItemUtils {

    public static String[] toArray(List<Item> items) {

        if (null == items) {
            return new String[0];
        }

        String[] result = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            result[i] = trimTitle(items.get(i).getTitle());
            
            String status = items.get(i).getStatus();
            if(null == status){
                status = items.get(i).getDate();
            }
            result[i] = result[i] + " ("+trimDate(status)+")";
           
        }
        return result;
    }
    
}
