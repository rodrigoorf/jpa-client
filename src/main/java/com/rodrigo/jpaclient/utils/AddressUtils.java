package com.rodrigo.jpaclient.utils;

public class AddressUtils {
    private AddressUtils() {
        throw new IllegalStateException("Utility class");
    }
    public static final String ZIP_CODE_REGEXP = "\\d{5}-\\d{3}";
    public static final String ZIP_CODE_PATTERN_MESSAGE = "Zip code should follow pattern XXXXX-XXX with numbers (e.g.: 80420-050)";
    public static final String ZIP_CODE_EMPTY_MESSAGE = "Zip code should not be empty";
    public static final String ZIP_CODE_TITLE = "Invalid zip code";
    public static final String NUMBER_REGEXP = "^[1-9]\\d*(?: ?(?:[a-z]|[/-] ?\\d+[a-z]?))?$";
    public static final String NUMBER_MESSAGE = "Number should be a positive integer number and can contain a letter at the end";
}
