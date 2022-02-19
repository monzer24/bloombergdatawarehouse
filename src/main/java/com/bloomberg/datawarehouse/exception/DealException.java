package com.bloomberg.datawarehouse.exception;

/***
 * @author monzer
 * The exception would be thrown when something goes wrong
 */
public class DealException extends Exception {

    private String message;
    private DealExceptionType type;

    public DealException(String message, DealExceptionType type){
        super(message);
        this.message = message;
        this.type=type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public DealExceptionType getType() {
        return type;
    }
}
