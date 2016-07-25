package com.jeff.core;


import org.apache.commons.lang3.RandomStringUtils;

public final class RandomUtils {

    public static final int SEQ_LENGTH=4;

    private RandomUtils() {
    }

    public static String generateSequence() {
        return RandomStringUtils.randomNumeric(SEQ_LENGTH);
    }

}
