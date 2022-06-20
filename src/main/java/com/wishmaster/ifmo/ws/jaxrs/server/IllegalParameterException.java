package com.wishmaster.ifmo.ws.jaxrs.server;

public class IllegalParameterException extends Exception{
    private static final long serialVersionUID = -6647544772732631047L;
    public static IllegalParameterException DEFAULT_INSTANCE = new
            IllegalParameterException("Wrong parameter provided");

    public IllegalParameterException(String message) {
        super(message);
    }
}
