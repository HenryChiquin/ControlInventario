package com.grupopdc.controlinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.grupopdc.controlinventario.Activities.CoreActivity;
import com.grupopdc.controlinventario.database.Entity.EmpresaEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;
import com.grupopdc.controlinventario.models.recursos.TableDynamic;

import java.util.ArrayList;
import java.util.List;

public class ListaEmpresaActivity extends CoreActivity {
    private TableLayout tbEmpresas;
    private String[]header = {"#","Nombre","Dominio"};
    private ArrayList<String[]> rows = new ArrayList<>();

    private List<EmpresaEntity> listItemEmpresa;
    private TableDynamic tableDynamic;


    private ImageView imgViewRegresarEmpresaLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empresa);
        initComponents();
    }

    private void initComponents(){
        tbEmpresas = findViewById(R.id.tbEmpresas);
        imgViewRegresarEmpresaLista = findViewById(R.id.imgViewRegresarEmpresaLista);

        tableDynamic = new TableDynamic(tbEmpresas,getApplicationContext());
        tableDynamic.addHeader(header);
        tableDynamic.addBody(getClients());
        tableDynamic.backgroundHeader(Color.parseColor("#0094D8"));
        //tableDynamic.backgroundBody(Color.parseColor("#FFFDFDFD"),Color.parseColor("#FFC0C1C1"));

        imgViewRegresarEmpresaLista.setOnClickListener(view -> {
            onBackPressed();

        });

    }

    private ArrayList<String[]> getClients(){
        listItemEmpresa = repositoryEmpresa.getAllEmpresaLista();

        for(EmpresaEntity alm : listItemEmpresa)
        {
            int id = alm.getIdEmpresa();
            String nombre = alm.getNombre();
            String dominio = alm.getDominio();
            rows.add(new String[]{""+id,nombre,dominio});
        }
        return rows;
    }
}