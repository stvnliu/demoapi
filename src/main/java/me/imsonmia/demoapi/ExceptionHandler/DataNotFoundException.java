package me.imsonmia.demoapi.ExceptionHandler;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String msg) {
        super(msg);
    }

    public DataNotFoundException() {
        super("Could not get data resource queried. ");
    }
}
