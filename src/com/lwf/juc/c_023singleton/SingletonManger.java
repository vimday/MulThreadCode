package com.lwf.juc.c_023singleton;

import java.util.HashMap;
import java.util.Map;

public class SingletonManger {
    private static Map<String, Object> objMap = new HashMap<String, Object>();

    private SingletonManger() {
    }

    public static void registerService(String key, Object instance) {
        if (!objMap.containsKey(key)) {
            objMap.put(key, instance);
        }
    }


    public static Object getService(String key) {
        return objMap.get(key);
    }
}

