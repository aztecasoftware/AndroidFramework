package com.aztecasoftware.kernel.net;

/**
 * Created by Ricardo on 30/09/2016.
 */

public class HttpException extends Exception {
    public int StatusCode;
    public HttpRequest Request;

    public HttpException(String message, int statusCode, HttpRequest request, Exception innerException){
        super(message, innerException);
        this.StatusCode=statusCode;
        this.Request=request;
    }
}
