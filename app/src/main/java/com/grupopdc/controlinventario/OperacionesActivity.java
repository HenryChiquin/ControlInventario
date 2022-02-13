package com.grupopdc.controlinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class OperacionesActivity extends AppCompatActivity {
    Button btnCompra, btnVenta,btnTraslado;
    private ImageView imgViewRegresarOperaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operaciones);
        btnCompra = findViewById(R.id.btnCompra);
        btnVenta = findViewById(R.id.btnVenta);
        btnTraslado = findViewById(R.id.btnTraslado);
        imgViewRegresarOperaciones = findViewById(R.id.imgViewRegresarOperaciones);

        btnCompra.setOnClickListener(View -> getCompra());
        btnVenta.setOnClickListener(View -> getVenta());
        btnTraslado.setOnClickListener(View -> getTraslado());

        imgViewRegresarOperaciones.setOnClickListener(view -> {
            onBackPressed();
        });

    }
    private void getCompra(){
        Intent intent = new Intent(getApplicationContext(), CompraActivity.class);
        startActivity(intent);
    }
    private void getVenta(){
        Intent intent = new Intent(getApplicationContext(), VentaActivity.class);
        startActivity(intent);
    }
    private void getTraslado(){
        Intent intent = new Intent(getApplicationContext(), TrasladoActivity.class);
        startActivity(intent);
    }
}