package me.imsonmia.demoapi.exceptionhandler;

public class GenericException extends RuntimeException {
    GenericException(String msg) {
        super(msg);

    }

    GenericException() {
        super();
    }
}
