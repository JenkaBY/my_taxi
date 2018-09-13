package com.exposit.my_taxi.model.user;

import com.exposit.my_taxi.model.exception.ConversionException;

public enum UserStatus {
    CONFIRMED, PENDING_CONFIRM, BLOCKED;

    public String getLookupCode() {
        return this.formatToLookupCode();
    }

    private String formatToLookupCode() {
//        TODO make formatting using REGEXP
        return this.name().toLowerCase();
    }

    public static UserStatus getFromLookupCode(String lookupCode) {
        try {
            return UserStatus.valueOf(lookupCode.toUpperCase());
        } catch (IllegalArgumentException iae) {
            throw new ConversionException("Lookup code", lookupCode, UserStatus.class);
        }
    }
}
