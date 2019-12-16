package com.service.parentalcontrol.hamza.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds ratings to help sort in correct order.
 */
public enum ParentalControlLevel {

    U("U", 1),
    PG("PG", 2),
    _12("12", 3),
    _15("15", 4),
    _18("18", 5);

    private static final Map<String, Integer> lookup = new HashMap<String, Integer>();

    private final String key;
    private final Integer value;

    static {
        for (ParentalControlLevel pcl : ParentalControlLevel.values()) {
            lookup.put(pcl.getKey(), pcl.getValue());
        }
    }

    ParentalControlLevel(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public static Integer get(String key) {
        return lookup.containsKey(key) ? lookup.get(key) : 100;
    }




}