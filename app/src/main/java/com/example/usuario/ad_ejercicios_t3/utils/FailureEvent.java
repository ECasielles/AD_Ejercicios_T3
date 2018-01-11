package com.example.usuario.ad_ejercicios_t3.utils;


public class FailureEvent {

    public static final int DOWNLOAD_ERROR = 1;

    public final String message;
    public final int statusCode;

    public FailureEvent(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
