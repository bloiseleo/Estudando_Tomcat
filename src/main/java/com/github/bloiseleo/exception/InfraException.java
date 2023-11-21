package com.github.bloiseleo.exception;

public class InfraException extends Exception{
    public InfraException(Exception exception) {
        super("An error occured while starting the needed Infrastructure");
        displayError(exception);
    }
    private void displayError(Exception exception) {
        System.err.println(exception.getMessage());
    }
}
