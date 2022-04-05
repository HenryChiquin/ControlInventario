package com.grupopdc.controlinventario;

import static com.grupopdc.controlinventario.CoreActivity.appDataBase;
import static com.grupopdc.controlinventario.CoreActivity.repositoryCategoria;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.grupopdc.controlinventario.Tools.QuickCache;
import com.grupopdc.controlinventario.database.Dao.CategoriaDao;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntiry;
import com.grupopdc.controlinventario.database.Entity.CompraEntiry;
import com.grupopdc.controlinventario.database.Entity.DestinoEntiry;
import com.grupopdc.controlinventario.database.Entity.Producto;


import java.util.ArrayList;
import java.util.List;

public class CompraActivity extends CoreActivity {
    private ImageView imgViewRegresarCompra;
    private TextInputEditText CantidadC, CostoC;
    private Button bntRegistroProducto;
    Spinner producto,destino;
    List<CategoriaEntiry> Listacategoria;
    List<DestinoEntiry> Listadestino;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        imgViewRegresarCompra = findViewById(R.id.imgViewRegresarCompra);

        imgViewRegresarCompra.setOnClickListener(view -> {
            onBackPressed();
        });

        Listacategoria = new ArrayList<>();
        producto=(Spinner) findViewById(R.id.SpinnerProducto);
        ListadeCategoria();

        Listadestino = new ArrayList<>();
        destino=(Spinner) findViewById(R.id.SpinnerDestino);
        ListadeDestino();

        //creardestino();


        CantidadC =  findViewById(R.id.txtCantidadProducto);
        CostoC =  findViewById(R.id.txtCostoProducto);
        bntRegistroProducto =  findViewById(R.id.btnRegistroProducto);
        bntRegistroProducto.setOnClickListener(View->  crearcompra());

    }

    public void ListadeCategoria(){
        Listacategoria = repositoryCategoria.getAll();
        List<String> lcategoria = new ArrayList<>();
        for(int index = 0; index < Listacategoria.size(); index++)
            {
            CategoriaEntiry this_categoria = Listacategoria.get(index);
            lcategoria.add(this_categoria.getNombre()+" "+this_categoria.getIdCategoria());
            tools.Log_i("Categoria lista for:" + this_categoria.getNombre() + " :" + this_categoria.getIdCategoria(), "PRODUCTO ACTIVITY");
            }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CompraActivity.this,
                android.R.layout.simple_list_item_1,lcategoria);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        producto.setAdapter(myAdapter);
        }

    public void ListadeDestino(){
        Listadestino= repositoryCategoria.getAllDestino();
        List<String> ldestino = new ArrayList<>();
        for(int index = 0; index < Listadestino.size(); index++)
        {
            DestinoEntiry this_destino = Listadestino.get(index);
            ldestino.add(this_destino.getNombre()+" "+this_destino.getIdDestino());
            tools.Log_i("Destino lista for:" + this_destino.getNombre() + " :" + this_destino.getIdDestino(), "PRODUCTO ACTIVITY");
        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CompraActivity.this,
                android.R.layout.simple_list_item_1,ldestino);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destino.setAdapter(myAdapter);
    }

    public void creardestino(){
        DestinoEntiry destinoEntiry = new DestinoEntiry();;
        destinoEntiry.setIdDestino(3);
        destinoEntiry.setNombre("Bodega C");
        repositoryCategoria.insertdestino(destinoEntiry);
    }

    public void crearcompra(){
        CompraEntiry compraEntiry = new CompraEntiry();
        //compraEntiry.setIdCompra(4);
        compraEntiry.setProducto(producto.getSelectedItem().toString());
        compraEntiry.setDestino(destino.getSelectedItem().toString());
        compraEntiry.setCantidad(Integer.parseInt(CantidadC.getText().toString()));
        compraEntiry.setCosto(Double.parseDouble(CostoC.getText().toString()));
        qkcache.List_Compra.add(compraEntiry);
        tools.Log_i("Lista de compra"+qkcache.List_Compra,"Compra Activity");
        CompraEntiry ocompra = new CompraEntiry();
        int id=0;
        for(int i = 0; i < qkcache.List_Compra.size(); i++)
        {
            id++;
            CompraEntiry objetoCompra = qkcache.List_Compra.get(i);
            ocompra.setIdCompra(id);
            ocompra.setProducto(objetoCompra.getProducto());
            ocompra.setDestino(objetoCompra.getDestino());
            ocompra.setCantidad(objetoCompra.getCantidad());
            ocompra.setCosto(objetoCompra.getCosto());
            repositoryCategoria.insertcompra(ocompra);
        }


        Toast.makeText(getApplicationContext(), "Registro con exito", Toast.LENGTH_SHORT).show();
    }
}