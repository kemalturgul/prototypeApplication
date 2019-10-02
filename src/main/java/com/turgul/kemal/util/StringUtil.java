package com.turgul.kemal.util;

import java.util.Collection;

/**
 * @author kemalturgul
 *
 */
public class StringUtil {

    public StringUtil() {
    }

    /**
     * @param input 
     *          string to be checked
     * @return true if it is null or empty false otherwise
     */
    public static final boolean isNullOrEmpty(final String input) {
        boolean result = false;
        if (input == null || input.trim().equals("")) {
            result = true;
        }
        return result;
    }

    public static final boolean isNullOrEmpty(final Collection input) {
        return input == null || input.isEmpty();
    }

    /**
     * Trims the input string.
     * 
     * @param input
     *        string to be trim
     * @return trimmed input string
     */
    public final static String trim(final String input) {

        if (input != null) {
            return input.trim();
        }

        return null;
    }

}
