package com.example.usuario.ad_ejercicios_t3.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.ad_ejercicios_t3.Noticia;
import com.example.usuario.ad_ejercicios_t3.R;
import com.example.usuario.ad_ejercicios_t3.utils.Analisis;
import com.example.usuario.ad_ejercicios_t3.utils.RestClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class CreacionActivity extends AppCompatActivity {

    public static final String RSS = "http://www.europapress.es/rss/rss.aspx";
    //public static final String RSS = "http://10.0.2.2/feed/alejandro.xml";
    public static final String TEMPORAL = "alejandro.xml";
    public static final String FICHERO_XML = "resultado.xml";
    Button boton;
    ArrayList<Noticia> noticias;
    ListView listView;
    ArrayAdapter<Noticia> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView = findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CreacionActivity.this, noticias.get(i).getUrl(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreacionActivity.this, WebActivity.class);
                intent.putExtra("url", noticias.get(i).getUrl());
                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);
        boton = findViewById(R.id.btnDescarga);
    }

    public void onClick(View v) {
        if (v == boton)
            descarga(RSS, TEMPORAL);
    }

    private void descarga(String rss, String temporal) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        File archivo = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), temporal);
        progressDialog.show();
        RestClient.get(rss, new FileAsyncHttpResponseHandler(archivo) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                progressDialog.dismiss();
                try {
                    Toast.makeText(CreacionActivity.this, "Descargando noticias", Toast.LENGTH_SHORT).show();
                    noticias = Analisis.analizarDescargaRSS(file);
                    Analisis.crearXML(noticias, FICHERO_XML);
                    adapter.clear();
                    adapter.addAll(noticias);
                } catch (XmlPullParserException | IOException e) {
                    Toast.makeText(CreacionActivity.this, "Error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                progressDialog.dismiss();
                Toast.makeText(CreacionActivity.this, "Fallo en la descarga: " + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
