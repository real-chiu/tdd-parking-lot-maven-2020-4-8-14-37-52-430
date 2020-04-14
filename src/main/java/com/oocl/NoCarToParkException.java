package com.oocl;

public class NoCarToParkException extends NullPointerException {
    public NoCarToParkException() {
        super("Please provide your car!");
    }
}
