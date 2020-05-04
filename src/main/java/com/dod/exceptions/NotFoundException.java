package com.dod.exceptions;

public class NotFoundException extends Throwable {
    public NotFoundException(String uuid) {
        super("Key not found (" + uuid + ")");
    }
}
