package com.vnpt.iot.hoalac.api.common;

public class Utils {
    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }

    public static boolean isBoolean(String s) {
        if (s != null && s.length() == 4) {
            if (s.equals("true") || s.equals("false")) {
                return true;
            }
        }

        return false;
    }

    public static boolean isExist(String s) {
        return !(s == null || s.equals(""));
    }
}
