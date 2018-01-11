package com.example.usuario.ad_ejercicios_t3.utils;

import android.os.Environment;

import com.example.usuario.ad_ejercicios_t3.Noticia;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by usuario on 11/01/18.
 */

public class DownloadTask {

    static final String SITE = "http://www.europapress.es/rss/rss.aspx?ch=00279";

    public static void executeDownload() {
        File archivo = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "noticias.txt");
        RestClient.get(SITE, new FileAsyncHttpResponseHandler(archivo) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                try {
                    ArrayList<Noticia> noticias = Analisis.analizarNoticias(file);
                } catch (XmlPullParserException | IOException e) {
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
            }
        });
    }
}
