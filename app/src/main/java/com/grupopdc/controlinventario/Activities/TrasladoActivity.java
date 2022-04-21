package com.grupopdc.controlinventario.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.grupopdc.controlinventario.R;
import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;

import java.util.ArrayList;
import java.util.List;

public class TrasladoActivity extends CoreActivity {
    private ImageView imgViewRegresarTraslado;
    private Spinner spOrigen,spDestino;
    private List<AlmacenEntity> listItemAlmacen;
    private AutoCompleteTextView autoCompleteProducto;
    private List<String> searchList;
    List<ProductoEntity> productoList;
    private ImageView imgBtnClean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traslado);

        initcomponents();

        imgViewRegresarTraslado = findViewById(R.id.imgViewRegresarTraslado);

        imgViewRegresarTraslado.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void initcomponents(){
        spOrigen=(Spinner) findViewById(R.id.spOrigen);
        spDestino=(Spinner) findViewById(R.id.spDestino);
        autoCompleteProducto = findViewById(R.id.autoCompleteProducto);
        searchList = new ArrayList<>();
        productoList = new ArrayList<>();
        imgBtnClean = findViewById(R.id.imgBtnClean);

        populateList();

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, searchList);
        autoCompleteProducto.setAdapter(arrayAdapter);
        autoCompleteProducto.setThreshold(1);

        spOrigenList();
        spDestinoList();

        imgBtnClean.setOnClickListener(viewClick -> autoCompleteProducto.setText(""));
    }

    private void spOrigenList(){
        //listItemAlmacen = repositoryAlmacen.getAllAlmacen();

        List<String> listOrigen = new ArrayList<>();

//        for(int index = 0; index < listItemAlmacen.size(); index++)
//        {
//            AlmacenEntity this_almacen = listItemAlmacen.get(index);
//            listOrigen.add(this_almacen.getNombre()+" "+this_almacen.getIdAlmacen());
//            tools.Log_i("Categoria lista for:" + this_almacen.getNombre() + " :" + this_almacen.getIdAlmacen(), "PRODUCTO ACTIVITY");
//        }
//
        listOrigen.add("Seleccione origen...");
        listOrigen.add("ALMACEN B");
        listOrigen.add("ALMACEN C");
        listOrigen.add("ALMACEN D");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,listOrigen);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOrigen.setAdapter(myAdapter);
    }


    private void spDestinoList(){
        //listItemAlmacen = repositoryAlmacen.getAllAlmacen();

        List<String> listDestino = new ArrayList<>();

//        for(int index = 0; index < listItemAlmacen.size(); index++)
//        {
//            AlmacenEntity this_almacen = listItemAlmacen.get(index);
//            listOrigen.add(this_almacen.getNombre()+" "+this_almacen.getIdAlmacen());
//            tools.Log_i("Categoria lista for:" + this_almacen.getNombre() + " :" + this_almacen.getIdAlmacen(), "PRODUCTO ACTIVITY");
//        }
//
        listDestino.add("Seleccione destino...");
        listDestino.add("ALMACEN B");
        listDestino.add("ALMACEN C");
        listDestino.add("ALMACEN D");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,listDestino);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDestino.setAdapter(myAdapter);
    }


    private void populateList(){
        //List<ProductoEntity> listItemProductos = new ArrayList<>();
        //listItemProductos = repositoryProducto.getallProducto();
        for (int i = 0; i < 15; i++) {
//            ProductoEntity productoEntity = listItemProductos.get(i);

//            productoList.add(new ProductoEntity(
//                    productoEntity.getNombre(),
//                    Float.parseFloat("3.3"),
//                    Integer.parseInt("32" + i * 4),
//                    Integer.parseInt( ""+i * 2)
//
//            ));

            productoList.add(new ProductoEntity(
                    "nesbitts fresa pi "+ 5 * i + " ml",
                    Float.parseFloat("3.3"),
                    Integer.parseInt("32" + i * 4),
                    Integer.parseInt( ""+i * 2)

            ));

            String valores =
                    "32" + i * 4 + " - " +
                            "nesbitts fresa pi  " + 5 * i + " ml";
            searchList.add(valores);

        }


    }
}