package com.brinvex.util.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toCollection;

public class StringUtil {

    @SuppressWarnings("SpellCheckingInspection")
    public static class Constants {
        public static final char[] DIGITS_CHARS = "0123456789".toCharArray();
        public static final char[] UP_LETTER_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        public static final char[] UP_LETTER_AND_DIGIT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    }

    private static class LazyHolder {
        private static final String EMPTY = "";
        private static final String SPACE = " ";
        private static final Pattern FORMAT_CHARACTER_PATTERN = Pattern.compile("\\p{Cf}");

    }

    public static String deleteAllWhitespaces(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        final int sz = str.length();
        final char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }
        if (count == 0) {
            return LazyHolder.EMPTY;
        }
        return new String(chs, 0, count);
    }

    public static String stripToNull(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        if ("".equals(s)) {
            return null;
        }
        return s;
    }

    public static String deleteFormatCharacters(String str) {
        if (str == null) {
            return null;
        }
        return LazyHolder.FORMAT_CHARACTER_PATTERN.matcher(str).replaceAll("");
    }

    public static String deleteTags(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("<[^>]*>", "");
    }

    public static List<String> generateWords(char[]... charRanges) {
        if (charRanges.length == 0) {
            return new ArrayList<>();
        }

        List<StringBuilder> words = new ArrayList<>();
        words.add(new StringBuilder());

        for (char[] charRange : charRanges) {
            if (charRange.length == 0) {
                continue;
            }
            List<StringBuilder> newWords = new ArrayList<>();
            for (StringBuilder word : words) {
                for (char c : charRange) {
                    newWords.add(new StringBuilder(word).append(c));
                }
            }
            words = newWords;
        }
        return words.stream().map(Objects::toString).collect(toCollection(ArrayList::new));
    }

}
