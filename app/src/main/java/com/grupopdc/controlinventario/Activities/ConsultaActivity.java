package com.grupopdc.controlinventario.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.grupopdc.controlinventario.R;
import com.grupopdc.controlinventario.ReporteTrasladoActivity;

public class ConsultaActivity extends AppCompatActivity {
    private ImageView imgViewRegresarConsulta;
    Button btnRepoVenta, btnRepoCompra,btnRepoTrslado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        imgViewRegresarConsulta = findViewById(R.id.imgViewRegresarConsulta);
        btnRepoTrslado = findViewById(R.id.btnRepoTrslado);

        imgViewRegresarConsulta.setOnClickListener(view -> {
            onBackPressed();
        });
        btnRepoTrslado.setOnClickListener(view -> {
            repoTraslado();
        });
    }

    private void repoTraslado(){
        Intent intent = new Intent(getApplicationContext(), ReporteTrasladoActivity.class);
        startActivity(intent);
    }
}