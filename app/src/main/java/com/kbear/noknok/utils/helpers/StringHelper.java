package com.kbear.noknok.utils.helpers;

import java.util.List;

/**
 * Created by allen on 3/16/15.
 */
public class StringHelper {

    public static String getCommaSeparatedString(List<String> strings) {
        StringBuilder sb = new StringBuilder(strings.size());
        String separator = "";
        for (String s : strings) {
            sb.append(separator).append(s);
            separator = ", ";
        }
        return sb.toString();
    }
}
