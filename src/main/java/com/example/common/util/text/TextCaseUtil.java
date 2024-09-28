package com.example.common.util.text;

import java.util.concurrent.ConcurrentHashMap;

public final class TextCaseUtil {

    private static final ConcurrentHashMap<String, String> cachedCapitalizationMap = new ConcurrentHashMap<>();

    public static String capitalizeAndSaveUpperSnakeCase(String upperSnakeCase) {
        return cachedCapitalizationMap.computeIfAbsent(upperSnakeCase, TextCaseUtil::capitalizeUpperSnakeCase);
    }

    public static String capitalizeUpperSnakeCase(String upperSnakeCase) {
        // 파라미터는 확실하게 UPPER_CASE_WITH_UNDERSCORE 로 왔다고 가정함.
        char[] chars = upperSnakeCase.toCharArray();
        boolean willBeLower = false;

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (willBeLower && 'A' <= ch && ch <= 'Z') {
                ch |= ' '; // ch = ch | ' ';
            } else if (ch == '_') {
                ch = ' ';
                willBeLower = false;
            } else {
                willBeLower = true;
            }
            chars[i] = ch;
        }

        return new String(chars);
    }
}