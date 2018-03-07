package com.ronrong.thymeleaf.mat.util;


public final class StringUtils {

    public static boolean isEmptyOrWhitespace(final String target) {
        if (target == null) {
            return true;
        }
        final int targetLen = target.length();
        if (targetLen == 0) {
            return true;
        }
        final char c0 = target.charAt(0);
        if ((c0 >= 'a' && c0 <= 'z') || (c0 >= 'A' && c0 <= 'Z')) {
            return false;
        }
        for (int i = 0; i < targetLen; i++) {
            final char c = target.charAt(i);
            if (c != ' ' && !Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    public static String captureName(String name) {
        //     name = name.substring(0, 1).toUpperCase() + name.substring(1);
        //     return  name;
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);

    }



    public static String replace(final Object target, final String before, final String after) {

        if (target == null) {
            return null;
        }

        final String targetStr = target.toString();
        final int targetStrLen = targetStr.length();
        final int beforeLen = before.length();

        if (targetStrLen == 0 || beforeLen == 0) {
            return targetStr;
        }

        int index = targetStr.indexOf(before);
        if (index < 0) {
            return targetStr;
        }

        final StringBuilder stringBuilder = new StringBuilder(targetStrLen + 10);

        int lastPos = 0;
        while (index >= 0) {

            stringBuilder.append(targetStr, lastPos, index);
            stringBuilder.append(after);
            lastPos = index + beforeLen;
            index = targetStr.indexOf(before, lastPos);

        }

        stringBuilder.append(targetStr, lastPos, targetStrLen);

        return stringBuilder.toString();

    }

}
