package com.grupopdc.controlinventario.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.grupopdc.controlinventario.R;
import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;

import java.util.ArrayList;
import java.util.List;

public class TrasladoActivity extends CoreActivity {
    private ImageView imgViewRegresarTraslado;
    private Spinner spOrigen,spDestino;
    private List<AlmacenEntity> listItemAlmacen;


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
        spOrigenList();
        spDestinoList();
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
        listOrigen.add("ALMACEN A");
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
        listDestino.add("ALMACEN A");
        listDestino.add("ALMACEN B");
        listDestino.add("ALMACEN C");
        listDestino.add("ALMACEN D");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,listDestino);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDestino.setAdapter(myAdapter);
    }
}