package com.sea.common.exception;

/**
 * Created by lvhaizhen on 2018/8/9.
 */
public class BackendLoginException extends Exception {


    public BackendLoginException(String message){
        super(message);
    }

    public BackendLoginException(String message, Throwable cause){
        super(message,cause);
    }

    public BackendLoginException(){
        super();
    }
}
