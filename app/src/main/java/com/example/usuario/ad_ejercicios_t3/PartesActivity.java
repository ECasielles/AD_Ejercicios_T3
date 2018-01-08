package com.example.usuario.ad_ejercicios_t3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class PartesActivity extends AppCompatActivity {

    public static final String TEXTO = "<texto><uno>Hello World!</uno><dos>Goodbye</dos></texto>";
    private TextView txvPartes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partes);
        txvPartes = findViewById(R.id.txvPartes);

        try {
            txvPartes.setText(Analisis.analizar(TEXTO));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
