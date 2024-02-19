package com.github.dreamph.core.utils;

import java.util.UUID;

public class IDUtils {
    public static String GenID() {
        return UUID.randomUUID().toString();
    }
}
