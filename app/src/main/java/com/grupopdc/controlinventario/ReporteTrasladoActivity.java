package com.grupopdc.controlinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.grupopdc.controlinventario.Activities.CoreActivity;
import com.grupopdc.controlinventario.Tools.Tools;
import com.grupopdc.controlinventario.database.Entity.EmpresaEntity;
import com.grupopdc.controlinventario.database.Entity.ReporteTrasladoEntity;
import com.grupopdc.controlinventario.models.recursos.TableDynamic;

import java.util.ArrayList;
import java.util.List;

public class ReporteTrasladoActivity extends CoreActivity {
    private TableLayout tbRepoTraslado;
    private String[]header = {" # ","  Fecha  ","  Producto  ","  Cantidad  ","  Almacen Origen  ","  Almacen Destino  "};
    private ArrayList<String[]> rows = new ArrayList<>();

    private List<ReporteTrasladoEntity> listItemRepoTraslado;
    private TableDynamic tableDynamic;
    public Tools tools;
    EditText txtBuscarFechaRepoT;
    Button btnBuscar;

    private ImageView imgViewRegresarRepoTraslado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_traslado);
        tools = new Tools(this);
        initComponents();
    }

    private void initComponents(){
        tbRepoTraslado = findViewById(R.id.tbRepoTraslado);
        imgViewRegresarRepoTraslado = findViewById(R.id.imgViewRegresarRepoTraslado);
        txtBuscarFechaRepoT = findViewById(R.id.txtBuscarFechaRepoT);
        btnBuscar = findViewById(R.id.btnBuscar);

        tableDynamic = new TableDynamic(tbRepoTraslado,getApplicationContext());
   /*     tableDynamic.addHeader(header);
        tableDynamic.addBody(getClients());
        tableDynamic.backgroundHeader(Color.parseColor("#0094D8"));*/
        //tableDynamic.backgroundBody(Color.parseColor("#FFFDFDFD"),Color.parseColor("#FFC0C1C1"));

        imgViewRegresarRepoTraslado.setOnClickListener(view -> {
            onBackPressed();

        });
        btnBuscar.setOnClickListener(view -> {

            tableDynamic.addHeader(header);
            tableDynamic.addBody(getClients());
            tableDynamic.backgroundHeader(Color.parseColor("#0094D8"));

        });

    }

    private ArrayList<String[]> getClients(){

        listItemRepoTraslado = repositoryReporteTraslado.getRepoByFecha("20220426");

        for(ReporteTrasladoEntity alm : listItemRepoTraslado)
        {
            int id = alm.getIdTraspaso();
            String fecha = tools.DecodeDatevalue(alm.getFecha());
            String nombre = alm.getProducto();
            int cantidad = alm.getCantidad();
            String almacenOrigen = alm.getAlmacenOrigen();
            String almacenDestino = alm.getAlmacenDestino();

            rows.add(new String[]{""+id,fecha+"    ",nombre+"    ",""+cantidad+"    ",almacenOrigen+"    ",almacenDestino+"    "});

        }
        return rows;
    }
}