package com.grupopdc.controlinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MantenimientoProducto extends AppCompatActivity {
    
    private ImageView imgViewRegresarMantenimiento;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimiento_producto);



        imgViewRegresarMantenimiento = findViewById(R.id.imgViewRegresarMantenimiento);

        imgViewRegresarMantenimiento.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}