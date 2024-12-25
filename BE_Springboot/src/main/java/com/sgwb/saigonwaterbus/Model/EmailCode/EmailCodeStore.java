package com.sgwb.saigonwaterbus.Model.EmailCode;

import java.util.HashMap;
import java.util.Map;

public class EmailCodeStore {
    private static final Map<String, String> emailCodeMap = new HashMap<>();

    public static void addCode(String email, String code) {
        emailCodeMap.put(email, code);
    }

    public static String getCode(String email) {
        return emailCodeMap.get(email);
    }

    public static void removeCode(String email) {
        emailCodeMap.remove(email);
    }
}
