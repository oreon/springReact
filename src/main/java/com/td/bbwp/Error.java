package com.td.bbwp;

public class Error extends RuntimeException{

    private final int code;
    private final String message;

//    public Error() {
//    }

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}