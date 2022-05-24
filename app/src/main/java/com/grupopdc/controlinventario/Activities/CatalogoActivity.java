package com.grupopdc.controlinventario.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.grupopdc.controlinventario.R;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;

import java.util.ArrayList;
import java.util.List;

public class CatalogoActivity extends CoreActivity {
    private List<ProductoEntity> listaProducto;
    private ListView listaProductoP;
    private ArrayList<String> names;
    private ImageView imgViewRegresarCatalogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        imgViewRegresarCatalogo = findViewById(R.id.imgViewRegresarCatalogo);

        listaProductoP = findViewById(R.id.listViewProductos);
        listaProducto = repositoryProducto.getallProducto();

        List<String> lcategoria = new ArrayList<>();
        for(int index = 0; index < listaProducto.size(); index++)
        {
            ProductoEntity this_categoria = listaProducto.get(index);
            //lcategoria.add(this_categoria.getIdCategoria(),this_categoria.getNombre());
            lcategoria.add(this_categoria.getIdProducto() +" "+ this_categoria.getNombre());
            //lcategoria.add(this_categoria.getNombre());

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,lcategoria);
        listaProductoP.setAdapter(adapter);

        imgViewRegresarCatalogo.setOnClickListener(view -> {
            onBackPressed();
        });
    }




}