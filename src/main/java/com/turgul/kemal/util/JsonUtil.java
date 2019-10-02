package com.turgul.kemal.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.turgul.kemal.model.Subscriber;

/**
 * @author kemalturgul
 *
 */
public class JsonUtil {

    public static <T> T fromJson(String json, Class<T> clazz) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, clazz);
    }

    public static String toJson(Object src) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(src);
    }

    public static List<Subscriber> convertJsonArrayToList(String jsonText, String arrayName) {
        List<Subscriber> parsedList = null;
        try {
            JsonObject jsonObject = JsonUtil.fromJson(jsonText, JsonObject.class);
            JsonElement arrayContent = jsonObject.get(arrayName);
            if (arrayContent != null) {
                parsedList = parseAsArrayList(arrayContent, Subscriber.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsedList;
    }

    public static <T> List<T> parseAsArrayList(JsonElement json, Class<T> type) {
        ArrayList<T> newArray = new ArrayList<T>();
        Gson gson = new Gson();

        JsonArray array = json.getAsJsonArray();
        Iterator<JsonElement> iterator = array.iterator();

        while (iterator.hasNext()) {
            JsonElement json2 = (JsonElement) iterator.next();
            T object = (T) gson.fromJson(json2, type);
            newArray.add(object);
        }

        return newArray;
    }

}
