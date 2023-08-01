package me.imsonmia.demoapi.ExceptionHandler;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super("Could not find the data resource required");
    }
}
