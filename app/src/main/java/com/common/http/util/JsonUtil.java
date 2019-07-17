package com.common.http.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * json转换成map
 *
 * @author Administrator
 */
public class JsonUtil {

    public static ArrayList<Map<String, String>> parseKeyAndValueToMapList(String source) {
        if (isEmpty(source)) {
            return new ArrayList<>();
        }

        source=source.replace("[[", "[").replace("]]", "]");

        if (!source.startsWith("[") && !source.endsWith("]")) {
            return new ArrayList<>();
        }
        try {
            ArrayList<Map<String, String>> list = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(source);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                list.add(parseKeyAndValueToMap(jsonObject.toString()));
            }
            return list;

        } catch (JSONException e) {

            return new ArrayList<>();
        }
    }

    public static HashMap<String, String> parseKeyAndValueToMap(String result) {
        if (result == null) {
            return new HashMap<>(0);
        }
        JSONObject sourceObj;
        try {
            sourceObj = new JSONObject(result);
        } catch (JSONException e) {
            return new HashMap<>(0);
        }

        HashMap<String, String> keyAndValueMap = new HashMap<>();
        for (Iterator<String> iter = sourceObj.keys(); iter.hasNext(); ) {
            String key = iter.next();
            putMapNotEmptyKey(keyAndValueMap, key, getString(sourceObj, key, ""));
        }
        return keyAndValueMap;
    }

    private static void putMapNotEmptyKey(Map<String, String> map, String key, String value) {
        if (map == null || isEmpty(key)) {
            return;
        }
        map.put(key, value);
    }

    public static String getString(JSONObject jsonObject, String key, String defaultValue) {
        if (jsonObject == null || isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {

            return defaultValue;
        }
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    public static String parseMapToJson(Map<String, String> map) {
        JSONObject source = new JSONObject();

        for (String key : map.keySet()) {
            try {
                source.put(key, map.get(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return source.toString();
    }

    public static String parseListToJson(List<Map<String, String>> list) {
        JSONArray jsonArray = new JSONArray();
        int j = list.size();
        for (int i = 0; i < j; i++) {
            try {
                JSONObject source = new JSONObject(parseMapToJson(list.get(i)));
                jsonArray.put(source);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray.toString();
    }
}
