package com.exposit.my_taxi.model.user;

import com.exposit.my_taxi.model.exception.ConversionException;

public enum UserType {
    ADMIN, DRIVER, OPERATOR, CUSTOMER;

    public String getLookupCode() {
        return this.formatToLookupCode();
    }

    private String formatToLookupCode() {
//        TODO make formatting using REGEXP
        return this.name().toLowerCase();
    }

    public static UserType getFromLookupCode(String lookupCode) {
        try {
            return UserType.valueOf(lookupCode.toUpperCase());
        } catch (IllegalArgumentException iae) {
            throw new ConversionException("Lookup code", lookupCode, UserType.class);
        }
    }
}
