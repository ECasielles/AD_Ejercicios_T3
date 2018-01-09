package com.example.usuario.ad_ejercicios_t3.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.usuario.ad_ejercicios_t3.R;
import com.example.usuario.ad_ejercicios_t3.utils.Analisis;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class NotasActivity extends AppCompatActivity {

    private TextView txvNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        txvNotas = findViewById(R.id.txvNotas);

        try {
            txvNotas.setText(Analisis.analizarXmlNextText(this));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
