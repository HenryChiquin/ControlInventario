package com.grupopdc.controlinventario.Activities;

import static com.grupopdc.controlinventario.Tools.KeysRoutes.PATH_REGISTRO;
import static com.grupopdc.controlinventario.Tools.KeysRoutes.PATH_REGISTRO_TRASLADO;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.grupopdc.controlinventario.R;
import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;
import com.grupopdc.controlinventario.database.Entity.TrasladoDetalleEntity;
import com.grupopdc.controlinventario.database.Entity.VentaDetalleEntity;
import com.grupopdc.controlinventario.models.request.TrasladoGeneralRequest;
import com.grupopdc.controlinventario.models.request.TrasladoRequest;
import com.grupopdc.controlinventario.models.request.VentaDetalleRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrasladoActivity extends CoreActivity {

    private static String TAG_CLASS = "TRASLADO ACTIVITY";


    private ImageView imgViewRegresarTraslado;
    private Spinner spOrigen,spDestino;
    private List<AlmacenEntity> listItemAlmacen;
    private AutoCompleteTextView autoCompleteProducto;
    private TrasladoRequest trasladoRequest;
    private TextInputEditText txtCantidadPTraslado;
    private TextView txtCantidadDetalle;
    private List<String> searchList;
    private Button btnAgregarTraslado,btnEnviar;
    List<ProductoEntity> productoList;
    private ImageView imgBtnClean;

    private Handler updateBarHandler;

    private String requestFetch;
    private boolean complet_fetch;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traslado);
        progress = new ProgressDialog(this);
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
        txtCantidadPTraslado = findViewById(R.id.txtCantidadPTraslado);
        btnAgregarTraslado = findViewById(R.id.btnAgregarTraslado);
        trasladoRequest = new TrasladoRequest();
        txtCantidadDetalle = findViewById(R.id.txtCantidadDetalle);
        btnEnviar = findViewById(R.id.btnEnviar);
        updateBarHandler = new Handler();
        populateList();

        List<ProductoEntity> listProd = repositoryProducto.getAllCodigoSKUNames();




        final List<String> array_articulos = new ArrayList<>();
        for(int i = 0; i < listProd.size(); i++)        {
            ProductoEntity this_producto = listProd.get(i);
            array_articulos.add(this_producto.getNombre()+"  -  "+this_producto.getIdProducto() );
        }

        final ArrayList<String> array_autocomplete = new ArrayList<String>();

        array_autocomplete.addAll(array_articulos);


        String[] array_list = new String[array_autocomplete.size()];
        array_list = array_autocomplete.toArray(array_list);

            ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, array_list);
        autoCompleteProducto.setAdapter(arrayAdapter);
        autoCompleteProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String query_item = arg0.getItemAtPosition(arg2).toString();
                tools.Log_i("Autocomplete item selected [" + query_item + "]", TAG_CLASS);
                SearchByQuery(query_item);
            }
        });


        spOrigenList();
        spDestinoList();

        imgBtnClean.setOnClickListener(viewClick -> autoCompleteProducto.setText(""));
        btnAgregarTraslado.setOnClickListener(view -> {
            agregarRegistro();
        });
        btnEnviar.setOnClickListener(view -> {
            send_traslado();
        });
    }

    private void send_traslado(){

        progress.setTitle("Procesando");
        progress.setMessage("Por favor espere ...");
        progress.setCancelable(false);
        progress.show();
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                //tarea a realizar
                try {


                    Gson gson = new Gson();

                    for(int i=0; i<repositoryTrasladoDetalle.getAllTraslado().size();i++){
                        TrasladoDetalleEntity trasladoDetalleEntity = repositoryTrasladoDetalle.getAllTraslado().get(i);


                        qkcache.LIST_TRASLADO.add(new TrasladoRequest(
                                trasladoDetalleEntity.getIdProducto(),
                                trasladoDetalleEntity.getCantidad(),
                                trasladoDetalleEntity.getIdAlmacenOrigen(),
                                trasladoDetalleEntity.getIdAlmacenDestino()
                        ));
                    }

                    TrasladoGeneralRequest trasladoGeneralRequest = new TrasladoGeneralRequest(
                                                ""+tools.PedidoKey(),
                                                qkcache.LIST_TRASLADO.size(),
                                                qkcache.LIST_TRASLADO
                                                );
                    String json = gson.toJson(trasladoGeneralRequest);
                    tools.Log_i("DETALLE TRASLADO: "+gson.toJson(trasladoGeneralRequest), TAG_CLASS);
                    JSONObject jsonObject = new JSONObject(json);

                    String urlWS = PATH_REGISTRO_TRASLADO;
                    String response = tools.usedNetworking().post_http_request(urlWS,jsonObject);
                    JSONObject object = new JSONObject(response);
                    if(!response.equals("FAIL")){
                        requestFetch = object.get("mensaje").toString();
                        complet_fetch = true;
                    }else{
                        complet_fetch = false;
                    }



                } catch (Exception ex) {

                }
                progress.dismiss();
                updateBarHandler.post(runnableUi);
            }
        });
        thread.start();
    }
    Runnable runnableUi = new  Runnable(){
        @Override
        public void run() {
            // Escriba aquí la operación de actualización de la interfaz de usuario
            if(complet_fetch){
                tools.MakeToast(requestFetch);
                qkcache.LIST_TRASLADO.clear();
                repositoryTrasladoDetalle.deleteTraslado();
                startActivity(new Intent(getBaseContext(), OperacionesActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                finish();
            }else{
                tools.MakeToast("Error al momento de registrar");
            }
        }
    };


    private void agregarRegistro(){



        for(int i=0;i < qkcache.LIST_TRASLADO.size();i++){
            TrasladoRequest trasladoRequestinterno = qkcache.LIST_TRASLADO.get(i);
            tools.Log_i("ID ALMACEN: "+ trasladoRequestinterno.getIdProducto(), TAG_CLASS);
           /* if(trasladoRequestinterno.getIdProducto()==trasladoRequest.getIdProducto()){
                tools.MakeToast("El producto ya fue seleccionado..");
                clean();
                return;
            }*/
        }
        if (trasladoRequest.getIdProducto()==0) {
            autoCompleteProducto.setError("El campo está vacío");
            return;
        }
        String cnat = txtCantidadPTraslado.getText().toString();
        if(cnat.isEmpty()){
            txtCantidadPTraslado.setError("El campo está vacío");
            return;
        }
        Gson gson = new Gson();
        int cantidad = Integer.parseInt(txtCantidadPTraslado.getText().toString());

        trasladoRequest.setCantidad(cantidad);
        TrasladoDetalleEntity trasladoDetalleEntity = new TrasladoDetalleEntity();

        trasladoDetalleEntity.setIdProducto(trasladoRequest.getIdProducto());
        trasladoDetalleEntity.setCantidad(trasladoRequest.getCantidad());
        trasladoDetalleEntity.setIdAlmacenDestino(trasladoRequest.getIdAlmacenDestino());
        trasladoDetalleEntity.setIdAlmacenOrigen(trasladoRequest.getIdAlmacenOrigen());
        repositoryTrasladoDetalle.insert(trasladoDetalleEntity);
        txtCantidadDetalle.setText(""+repositoryTrasladoDetalle.getAllTraslado().size());

        clean();
    }
    private void SearchByQuery(String entry){
        tools.Log_i("ENTRY [" + entry + "]", TAG_CLASS);

        try{
            String s_sku = tools.TrimAndGet(entry,"-",1);
            Integer.parseInt(s_sku);
            entry = s_sku;
        }catch (Exception e){
            tools.Log_e("NOT DETECTED. ENTRY: "+entry, TAG_CLASS);
        }

        String query = "";
        boolean flag_type_int = false;
        boolean flag_autogen_sku = false;

        if(!entry.equals("")){

            try{
                tools.Log_i("Comes with code MODE INTEGER", TAG_CLASS);
                Integer.parseInt(entry);
                flag_type_int = true;
            }catch (Exception e){
                tools.Log_i("Comes with query MODE STRING", TAG_CLASS);

                String s_sku = repositoryProducto.getCodigoProductoByProducto(query).toString();

                tools.Log_i("TRY GEN SKU BY QUERY: "+s_sku,TAG_CLASS);
                try{
                    Integer.parseInt(s_sku);
                    query = s_sku;
                    flag_type_int = true;
                    flag_autogen_sku = true;
                    tools.Log_i("GEN CHANGE QUERY: "+s_sku,TAG_CLASS);
                }catch (Exception e2){
                    tools.Log_e("ERROR - QUERY COD. Reason: "+e2,TAG_CLASS);
                }
            }

            if(flag_type_int){
                if(!flag_autogen_sku)
                    query = entry;
            }else{
                tools.Log_i("entry query: [" + entry+"]", TAG_CLASS);

                if(!repositoryProducto.getCodigoMarcaByMarca(entry).isEmpty()){
                    if(repositoryProducto.getCodigoMarcaByMarca(entry).get(0).length() > 0){
                        query = repositoryProducto.getCodigoMarcaByMarca(entry).get(0)+"";
                    }else{
                        query="";
                    }
                }else{
                    query="";
                }
            }
        }else{
            query = entry;
        }
        tools.Log_i("QUERY [" + query + "]", TAG_CLASS);

        InputMethodManager mgr = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(autoCompleteProducto.getWindowToken(), 0);
        //txtCantidadPTraslado.setText(query);
        trasladoRequest.setIdProducto(Integer.parseInt(query));
        //closePopup();
        //ReconstructPool(query, flag_type_int, flag_focus, codigo_sku);
    }

    private void spOrigenList(){
        listItemAlmacen = repositoryAlmacen.getAllAlmacen();
        List<AlmacenEntity> listaAlmacenOrigen = new ArrayList<>();

        for(AlmacenEntity alm : listItemAlmacen)
        {
            int id = alm.getIdAlmacen();
            String nombre = alm.getNombre();
            listaAlmacenOrigen.add(new AlmacenEntity(id,nombre));
        }


        ArrayAdapter<AlmacenEntity> myAdapter = new ArrayAdapter<AlmacenEntity>(getApplicationContext(),
                android.R.layout.simple_list_item_1,listaAlmacenOrigen);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOrigen.setAdapter(myAdapter);
        spOrigen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3){
                String workRequestType = arg0.getItemAtPosition(pos).toString();

                int Id = listaAlmacenOrigen.get(pos).getIdAlmacen();
                trasladoRequest.setIdAlmacenOrigen(Id);
            }
            @Override public void onNothingSelected(AdapterView<?> arg0){

            }
        });
    }


    private void spDestinoList(){
        listItemAlmacen = repositoryAlmacen.getAllAlmacenDestino();


        List<AlmacenEntity> listaAlmacenDestino = new ArrayList<>();

        for(AlmacenEntity alm : listItemAlmacen)
        {
            int id = alm.getIdAlmacen();
            String nombre = alm.getNombre();
            listaAlmacenDestino.add(new AlmacenEntity(id,nombre));
        }

        ArrayAdapter<AlmacenEntity> myAdapter = new ArrayAdapter<AlmacenEntity>(getApplicationContext(),
                android.R.layout.simple_list_item_1,listaAlmacenDestino);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDestino.setAdapter(myAdapter);
        spDestino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3){
                String workRequestType = arg0.getItemAtPosition(pos).toString();

                int Id = listaAlmacenDestino.get(pos).getIdAlmacen();
                trasladoRequest.setIdAlmacenDestino(Id);
            }
            @Override public void onNothingSelected(AdapterView<?> arg0){

            }
        });
    }


    private void populateList(){
        //List<ProductoEntity> listItemProductos = new ArrayList<>();
        //listItemProductos = repositoryProducto.getallProducto();
        //------------[INSTANCES FILTER]------------------------------------------------------------

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

    private void clean(){

        spOrigenList();
        spDestinoList();
        autoCompleteProducto.setText("");


        //autoCompleteProducto.setFocusable(true);
        txtCantidadPTraslado.setText("");


    }
}