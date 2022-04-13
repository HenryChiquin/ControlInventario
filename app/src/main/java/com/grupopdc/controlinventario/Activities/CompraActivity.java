package com.grupopdc.controlinventario.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.grupopdc.controlinventario.R;

public class CompraActivity extends AppCompatActivity {
    private ImageView imgViewRegresarCompra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        imgViewRegresarCompra = findViewById(R.id.imgViewRegresarCompra);

        imgViewRegresarCompra.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}