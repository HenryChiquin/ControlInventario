package com.grupopdc.controlinventario.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.grupopdc.controlinventario.R;

public class MenuPrincipal extends AppCompatActivity {
    private CardView cardViewRegistro,cardViewMantenimiento,cardViewConsulta, cardViewOperacion,cardViewCatalogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        cardViewRegistro = findViewById(R.id.cardView_Registro);
        cardViewMantenimiento = findViewById(R.id.cardView_Mantenimiento);
        cardViewConsulta = findViewById(R.id.cardView_Consulta);
        cardViewOperacion = findViewById(R.id.cardView_Operaciones);
        cardViewCatalogo = findViewById(R.id.cardView_Catalogo);

        cardViewRegistro.setOnClickListener(View -> getRegistroProducto());
        cardViewMantenimiento.setOnClickListener(View ->MantenimientoProducto());
        cardViewCatalogo.setOnClickListener(View ->catalogoProducto());
        cardViewOperacion.setOnClickListener(View ->operacionProducto());
        cardViewConsulta.setOnClickListener(View ->consultaProducto());
    }


    public void getRegistroProducto(){
        Intent intent = new Intent(getApplicationContext(), IngresoProducto.class);
        startActivity(intent);
    }
    public void MantenimientoProducto(){
        Intent intent = new Intent(getApplicationContext(), MantenimientoProducto.class);
        startActivity(intent);
    }
    public void catalogoProducto(){
        Intent intent = new Intent(getApplicationContext(), CatalogoActivity.class);
        startActivity(intent);
    }

    public void operacionProducto(){
        Intent intent = new Intent(getApplicationContext(), OperacionesActivity.class);
        startActivity(intent);
    }

    public void consultaProducto(){
        Intent intent = new Intent(getApplicationContext(), ConsultaActivity.class);
        startActivity(intent);
    }
}
