package com.abinge.boot.staging.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParameterSortToStr {

    public static String getSortParameter(Map<String, String> headerMap, Map<String, String> parameMap) {
        Map<String,String> map = new HashMap<>(headerMap);
        map.remove(SystemParametersEnum.X_ENV_STAGE.getName());
        for (Map.Entry<String, String> entry : parameMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (StringUtils.isNotBlank(value)) {
                map.put(key, value);
            }
        }
        String[] keys = map.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : keys) {
            String value = map.get(key);
            stringBuilder.append(key).append(value);
        }
        return stringBuilder.toString();
    }

    public static String getSortString(Map<String, String> map) {
        String[] keys = map.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : keys) {
            String value = map.get(key);
            stringBuilder.append(key).append(value);
        }
        return stringBuilder.toString();
    }
}