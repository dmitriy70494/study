package ru.job4j.bank;

import java.io.IOException;

public class ExistingUserException extends IOException {
    public ExistingUserException(String msg) {
        super(msg);
    }
}
