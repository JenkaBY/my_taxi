package com.exposit.my_taxi.model.exception;

public class ConversionException extends RuntimeException {
    public ConversionException(String msg) {
        super(msg);
    }

    public ConversionException(String what, String whatValue, Class clazz) {
        super(what + " '" + whatValue + "' can't be converted to " + clazz.getCanonicalName());
    }
}
