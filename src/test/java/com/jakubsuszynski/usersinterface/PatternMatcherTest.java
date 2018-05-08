package com.jakubsuszynski.usersinterface;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PatternMatcherTest {


    @Test
    public void checkWrongCodeFormat() {
        String input = "ER6 2017-10-13 2017-11-14";

        assertFalse(PatternMatcher.inputMatches(input));
    }


    @Test
    public void checkWrongYearFormat() {
        String input = "ERD 217-10-13 2017-11-14";

        assertFalse(PatternMatcher.inputMatches(input));
    }


    @Test
    public void checkWrongYearFormat2() {
        String input = "ERD 21102-10-13 2017-11-14";

        assertFalse(PatternMatcher.inputMatches(input));
    }

    @Test
    public void checkWrongMonthFormat() {
        String input = "ERD 2012-10-13 2017-121-14";

        assertFalse(PatternMatcher.inputMatches(input));
    }


    @Test
    public void checkWrongDayFormat() {
        String input = "ERD 2012-10-13 2017-11-QW";

        assertFalse(PatternMatcher.inputMatches(input));
    }

    @Test
    public void checkWrongSeparator() {
        String input = "ERD 2012.10-13 2017-11-11";

        assertFalse(PatternMatcher.inputMatches(input));
    }

    @Test
    public void testProperFormat() {
        String input = "USD 2012-10-13 2017-11-13";

        assertTrue(PatternMatcher.inputMatches(input));
    }


}