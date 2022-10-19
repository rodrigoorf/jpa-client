package com.rodrigo.jpaclient.utils;

public class CustomerUtils {
    private CustomerUtils() {
        throw new IllegalStateException("Utility class");
    }
    public static final String NAME_REGEXP = "^[\\p{L} ,.'-]*$";
    public static final String NAME_EMPTY_MESSAGE = "Name should not be empty";
    public static final String NAME_LETTERS_MESSAGE = "Name should contain only letters";
    public static final String AGE_REGEXP = "^(?:1[01][0-9]|130|1[8-9]|[2-9][0-9])$";
    public static final String AGE_MESSAGE = "Age should be a valid integer number between 18 and 130";
    public static final String ADDRESS_MESSAGE = "At least one address is required";
    public static final String CUSTOMER_NOT_FOUND_TITLE = "Customer not Found";
    public static final String CUSTOMER_NOT_FOUND_MESSAGE = "No customer was found for the requested id.";
}
