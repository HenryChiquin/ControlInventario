package com.grupopdc.controlinventario.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.grupopdc.controlinventario.R;

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