package fr.nywhere.nywheremod.common.world.entity.player;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {
    public static String generateRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }
}
