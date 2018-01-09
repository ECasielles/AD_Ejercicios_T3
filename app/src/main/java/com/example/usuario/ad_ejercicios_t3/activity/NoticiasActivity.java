package com.example.usuario.ad_ejercicios_t3.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.ad_ejercicios_t3.Noticia;
import com.example.usuario.ad_ejercicios_t3.R;
import com.example.usuario.ad_ejercicios_t3.utils.Analisis;
import com.example.usuario.ad_ejercicios_t3.utils.RestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class NoticiasActivity extends AppCompatActivity {

    static final String SITE = "http://www.europapress.es/rss/rss.aspx?ch=00279";
    ListView listView;
    ArrayList<Noticia> noticias;
    ArrayAdapter<Noticia> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        noticias = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.item_noticia);
        listView = findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("titular", noticias.get(i).getUrl());
                Toast.makeText(NoticiasActivity.this, noticias.get(i).getTitular(), Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(NoticiasActivity.this, WebActivity.class), bundle);
            }
        });
        listView.setAdapter(adapter);
    }

    public void onClick(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        File archivo = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"noticias.txt");
        progressDialog.show();
        RestClient.get(SITE, new FileAsyncHttpResponseHandler(archivo) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                progressDialog.dismiss();
                try {
                    Toast.makeText(NoticiasActivity.this, "Descargando noticias", Toast.LENGTH_SHORT).show();
                    noticias = Analisis.analizarNoticias(file);
                    //adapter.clear();
                    //adapter.addAll(noticias);
                } catch (XmlPullParserException | IOException e) {
                    Toast.makeText(NoticiasActivity.this, "Error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                progressDialog.dismiss();
                Toast.makeText(NoticiasActivity.this, "Fallo en la descarga", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

