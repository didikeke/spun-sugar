package com.didikeke.spunsugar.client.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

    public static String pickText(String input, String reg, int pos) {
        Pattern pattern = Pattern.compile(reg, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(pos).trim();
        }
        return null;
    }

    public static String trimTitle(String title) {
        if (null == title) {
            return title;
        }
        //因为获得不到真实的书名，所以尽可能地过滤出书名来
        String realTitle = pickText(title,"(.*?[\u4e00-\u9fa5]) .*", 1);
        if(null == realTitle){
            realTitle = title.replaceAll(" .*", "");
        }
        return realTitle;
    }
    
    public static String trimDate(String date){
        if(null == date){
            return date;
        }
        String realDate = pickText(date,".*(\\d{2}-\\d{2}-\\d{2}).*",1);
        return realDate;
    }
    
}
