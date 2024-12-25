package com.sgwb.saigonwaterbus.Util;

import java.security.SecureRandom;

public class RandomCodeGmail {
    private static final int CODE_LENGTH = 6;

    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomCode() {
        int min = (int) Math.pow(10, CODE_LENGTH - 1);
        int max = (int) Math.pow(10, CODE_LENGTH) - 1;
        int randomNumber = random.nextInt((max - min) + 1) + min;
        return String.valueOf(randomNumber);
    }


}
