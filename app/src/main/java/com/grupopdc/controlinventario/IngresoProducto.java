package com.grupopdc.controlinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class IngresoProducto extends AppCompatActivity {
    private ImageView imgViewRegresarRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_producto);


        imgViewRegresarRegistro = findViewById(R.id.imgViewRegresarRegistro);

        imgViewRegresarRegistro.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}