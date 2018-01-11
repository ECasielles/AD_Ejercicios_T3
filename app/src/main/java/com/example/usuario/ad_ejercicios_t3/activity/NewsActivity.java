package com.example.usuario.ad_ejercicios_t3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.ad_ejercicios_t3.Noticia;
import com.example.usuario.ad_ejercicios_t3.R;
import com.example.usuario.ad_ejercicios_t3.utils.DownloadTask;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    static final String SITE = "http://www.europapress.es/rss/rss.aspx?ch=00279";
    ListView listView;
    ArrayList<Noticia> noticias;
    ArrayAdapter<Noticia> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        noticias = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView = findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(NewsActivity.this, noticias.get(i).getUrl(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewsActivity.this, WebActivity.class);
                intent.putExtra("url", noticias.get(i).getUrl());
                startActivity(intent);
            }
        });
        listView.setAdapter(adapter);
    }

    public void onClick(View view) {
        DownloadTask.executeDownload();
    }

}
