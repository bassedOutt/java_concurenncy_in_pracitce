package com.murmylo.volodymyr.publishing;

import java.util.Arrays;

public class SafeArrayPublish {
    private final String[] strings;

    public SafeArrayPublish(String[] strings) {
        this.strings = strings;
    }

    public String[] getStrings() {
        return Arrays.copyOf(strings, strings.length);
    }
}
