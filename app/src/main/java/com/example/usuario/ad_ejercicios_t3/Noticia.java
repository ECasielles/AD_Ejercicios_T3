package com.example.usuario.ad_ejercicios_t3;

public class Noticia {
    String titular, url;

    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return titular;
    }
}