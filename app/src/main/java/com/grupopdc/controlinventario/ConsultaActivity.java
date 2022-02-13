package com.grupopdc.controlinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class ConsultaActivity extends AppCompatActivity {
    private ImageView imgViewRegresarConsulta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        imgViewRegresarConsulta = findViewById(R.id.imgViewRegresarConsulta);

        imgViewRegresarConsulta.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}