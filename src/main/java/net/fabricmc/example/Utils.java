package net.fabricmc.example;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {
    public static String generateRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }
}
