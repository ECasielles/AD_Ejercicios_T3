package com.example.usuario.ad_ejercicios_t3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.usuario.ad_ejercicios_t3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPartes:
                startActivity(new Intent(this, PartesActivity.class));
                break;
            case R.id.btnNotas:
                startActivity(new Intent(this, NotasActivity.class));
                break;
            case R.id.btnTitulares:
                startActivity(new Intent(this, TitularesActivity.class));
                break;
            case R.id.btnNoticias:
                startActivity(new Intent(this, NoticiasActivity.class));
                break;
            case R.id.btnCreacion:
                startActivity(new Intent(this, CreacionActivity.class));
                break;

        }
    }
}
