package com.bloomberg.datawarehouse.exception;

/***
* @author monzer
* This enum type used to make the exception more dynamic, where you can know what went wrong by the exception type
*/
public enum DealExceptionType {

    NOT_VALID_DEAL("Not valid deal"),
    MISSING_ATTRIBUTE("Deal missing some required attributes"),
    ACCESS_ERROR("Field are not visible"),
    DUPLICATED_DEAL("Deal already exists");

    private String message;

    private DealExceptionType(String message){
        this.message=message;
    }

}
