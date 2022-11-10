package ru.geekbrains.D_market.exceptions;

import java.util.List;

public class DataValidationException extends RuntimeException{
    private List<String> messages;

    public DataValidationException(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
