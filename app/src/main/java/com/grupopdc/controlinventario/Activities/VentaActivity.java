package com.grupopdc.controlinventario.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.grupopdc.controlinventario.R;

public class VentaActivity extends AppCompatActivity {
    private ImageView imgViewRegresarVenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);
        imgViewRegresarVenta = findViewById(R.id.imgViewRegresarVenta);

        imgViewRegresarVenta.setOnClickListener(view -> {
            onBackPressed();
        });

    }
}