package com.example.usuario.ad_ejercicios_t3.utils;

import android.os.Environment;

import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import cz.msebera.android.httpclient.Header;


public class DownloadTask {

    public static void executeDownload(String canal, String temp) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), temp);
        RestClient.get(canal, new FileAsyncHttpResponseHandler(file) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                EventBus.getDefault().post(new MessageEvent("Descarga completada", file));
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                EventBus.getDefault().post(new FailureEvent("Descarga completada", FailureEvent.DOWNLOAD_ERROR));
            }
        });
    }

}
