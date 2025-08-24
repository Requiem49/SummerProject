package org.example.service;

// Constructor that allows a specific error message to be specified.
public class UserException extends Exception {

    // Passes msg as the detail message.
    public UserException(String msg) {
        super(msg);
    }

    // User not found exception
    static class UserNotFoundException extends UserException {
        public UserNotFoundException(String msg) {
            super(msg);
        }
    }

    // Invalid password exception
    static class InvalidPasswordException extends UserException {
        public InvalidPasswordException(String msg) {
            super(msg);
        }
    }

    // Invalid parameter exception
    static class InvalidParameterException extends UserException {
        public InvalidParameterException(String msg) {
            super(msg);
        }
    }
}
