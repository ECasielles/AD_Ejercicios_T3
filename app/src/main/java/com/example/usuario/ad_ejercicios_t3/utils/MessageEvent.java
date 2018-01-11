package com.example.usuario.ad_ejercicios_t3.utils;

import java.io.File;

public class MessageEvent {

    public final String message;
    public final File file;

    public MessageEvent(String message, File file) {
        this.message = message;
        this.file = file;
    }
}
