package com.grupopdc.controlinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class CatalogoActivity extends AppCompatActivity {
    private ListView listaProducto;
    private ArrayList<String> names;
    private ImageView imgViewRegresarCatalogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        imgViewRegresarCatalogo = findViewById(R.id.imgViewRegresarCatalogo);

        listaProducto = findViewById(R.id.listViewProductos);
        names = new ArrayList<String>();
        names.add("Producto 1");
        names.add("Producto 2");
        names.add("Producto 3");
        names.add("Producto 4");
        names.add("Producto 5");
        names.add("Producto 6");
        names.add("Producto 7");
        names.add("Producto 8");
        names.add("Producto 9");
        names.add("Producto 10");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,names);
        listaProducto.setAdapter(adapter);

        imgViewRegresarCatalogo.setOnClickListener(view -> {
            onBackPressed();
        });
    }



}