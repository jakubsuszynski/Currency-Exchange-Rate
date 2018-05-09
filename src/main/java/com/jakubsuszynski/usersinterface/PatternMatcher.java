package com.jakubsuszynski.usersinterface;

public class PatternMatcher {

    private static final String PATTERN = "[a-zA-Z]{3}\\ \\d{4}\\-\\d{2}\\-\\d{2}\\ \\d{4}\\-\\d{2}\\-\\d{2}";

    public static Boolean inputMatches(String input) {
        if (input.matches(PATTERN))
            return true;
        return false;
    }
}
