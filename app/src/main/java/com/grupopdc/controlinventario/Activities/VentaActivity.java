package com.grupopdc.controlinventario.Activities;

import static com.grupopdc.controlinventario.Tools.KeysRoutes.PATH_REGISTRO_VENTA;

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
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;
import com.grupopdc.controlinventario.database.Entity.VentaDetalleEntity;
import com.grupopdc.controlinventario.models.request.VentaDetalleRequest;
import com.grupopdc.controlinventario.models.request.VentaGeneralcfRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VentaActivity extends CoreActivity {
    private static String TAG_CLASS = "VENTA ACTIVITY";

    private ImageView imgViewRegresarVenta;
    private List<AlmacenEntity> listItemAlmacen;
    private Spinner spAlmacen;

    private VentaDetalleRequest ventaDetalleRequest;

    private TextInputEditText txtPrecioProducto, txtCantidadProducto;
    private TextView txtCantidadDetalleVenta;
    private AutoCompleteTextView autoCompleteProducto;
    private ImageView imgBtnClean;

    private Button btnAgregarTraslado,btnEnviar;

    ProgressDialog progress;
    private Handler updateBarHandler;
    private String requestFetch;
    private boolean complet_fetch;
    private ArrayList<VentaDetalleRequest> LIST_VENTA_DETAIL_LOCAL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);
        progress = new ProgressDialog(this);
        initComponents();



    }

    private void initComponents(){
        imgViewRegresarVenta = findViewById(R.id.imgViewRegresarVenta);

        spAlmacen = findViewById(R.id.spAlmacen);
        ventaDetalleRequest = new VentaDetalleRequest();
        txtPrecioProducto = findViewById(R.id.txtPrecioProducto);
        txtCantidadProducto = findViewById(R.id.txtCantidadProdCompra);
        txtCantidadDetalleVenta = findViewById(R.id.txtCantidadDetalleVenta);
        autoCompleteProducto = findViewById(R.id.autoCompleteProducto);
        imgBtnClean = findViewById(R.id.imgBtnClean);
        btnAgregarTraslado = findViewById(R.id.btnAgregarVenta);
        btnEnviar = findViewById(R.id.btnEnviarVenta);
        updateBarHandler = new Handler();
        List<ProductoEntity> listProd = repositoryProducto.getAllCodigoSKUNames();
        LIST_VENTA_DETAIL_LOCAL = new ArrayList<>();



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

        spAlmacenList();

        imgBtnClean.setOnClickListener(viewClick -> clean_textview());

        imgViewRegresarVenta.setOnClickListener(view -> {
            onBackPressed();
        });
        btnAgregarTraslado.setOnClickListener(view->{
            agregarRegistro();
        });
        btnEnviar.setOnClickListener(view -> {

                send_venta();

        });
    }

    private void send_venta(){

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



                    for(int i=0; i<repositoryVentaDetalle.getAllVenta().size();i++){
                        VentaDetalleEntity ventaDetalleEntity = repositoryVentaDetalle.getAllVenta().get(i);


                        LIST_VENTA_DETAIL_LOCAL.add(new VentaDetalleRequest(
                                ventaDetalleEntity.getCantidad(),
                                ventaDetalleEntity.getPrecio(),
                                ventaDetalleEntity.getIdAlmacen(),
                                ventaDetalleEntity.getIdProducto()
                        ));
                    }
                    VentaGeneralcfRequest ventaGeneralcfRequest = new VentaGeneralcfRequest(
                            PREF_IDUSER,
                            1,
                            ""+tools.PedidoKey(),
                            LIST_VENTA_DETAIL_LOCAL
                    );
                    String json = gson.toJson(ventaGeneralcfRequest);
                    tools.Log_i("DETALLE TRASLADO: "+gson.toJson(ventaGeneralcfRequest), TAG_CLASS);
                    JSONObject jsonObject = new JSONObject(json);

                    String urlWS = PATH_REGISTRO_VENTA;
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
                LIST_VENTA_DETAIL_LOCAL.clear();
                repositoryVentaDetalle.deltedVenta();
                startActivity(new Intent(getBaseContext(), OperacionesActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                finish();
            }else{
                tools.MakeToast("Error al momento de registrar");
            }
        }
    };

    private void agregarRegistro(){

        for(int i=0;i < qkcache.LIST_VENTA_DETAIL.size();i++){
            VentaDetalleRequest ventaDetalleRequest = qkcache.LIST_VENTA_DETAIL.get(i);
            tools.Log_i("ID ALMACEN: "+ ventaDetalleRequest.getIdProducto(), TAG_CLASS);
           /* if(trasladoRequestinterno.getIdProducto()==trasladoRequest.getIdProducto()){
                tools.MakeToast("El producto ya fue seleccionado..");
                clean();
                return;
            }*/
        }
        if (ventaDetalleRequest.getIdProducto()==0) {
            autoCompleteProducto.setError("El campo está vacío");
            return;
        }
        String cnat = txtCantidadProducto.getText().toString();
        String preciotext = txtPrecioProducto.getText().toString();

        if(cnat.isEmpty()){
            txtCantidadProducto.setError("El campo está vacío");
            return;
        }
        if(preciotext.isEmpty()){
            txtPrecioProducto.setError("El campo está vacío");
            return;
        }

        Gson gson = new Gson();
        int cantidad = Integer.parseInt(txtCantidadProducto.getText().toString());
        float precio = Float.parseFloat(txtPrecioProducto.getText().toString());

        ventaDetalleRequest.setCantidad(cantidad);
        ventaDetalleRequest.setPrecio(precio);

        VentaDetalleEntity ventaDetalleEntity = new VentaDetalleEntity();

        ventaDetalleEntity.setCantidad(ventaDetalleRequest.getCantidad());
        ventaDetalleEntity.setPrecio(ventaDetalleRequest.getPrecio());
        ventaDetalleEntity.setIdProducto(ventaDetalleRequest.getIdProducto());
        ventaDetalleEntity.setIdAlmacen(ventaDetalleRequest.getIdAlmacen());

        repositoryVentaDetalle.insert(ventaDetalleEntity);



        txtCantidadDetalleVenta.setText(""+repositoryVentaDetalle.getAllVenta().size());

        clean();
    }

    private void spAlmacenList(){
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
        spAlmacen.setAdapter(myAdapter);
        spAlmacen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3){
                String workRequestType = arg0.getItemAtPosition(pos).toString();

                int Id = listaAlmacenOrigen.get(pos).getIdAlmacen();
                ventaDetalleRequest.setIdAlmacen(Id);
            }
            @Override public void onNothingSelected(AdapterView<?> arg0){

            }
        });
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
        ventaDetalleRequest.setIdProducto(Integer.parseInt(query));

        txtPrecioProducto.setText(""+repositoryProducto.getCostoByIdProducto(Integer.parseInt(query)));

        //closePopup();
        //ReconstructPool(query, flag_type_int, flag_focus, codigo_sku);
    }

    private void clean_textview(){
        autoCompleteProducto.setText("");
        txtPrecioProducto.setText("");
    }
    private void clean(){

        spAlmacenList();

        autoCompleteProducto.setText("");


        //autoCompleteProducto.setFocusable(true);
        txtCantidadProducto.setText("");
        txtPrecioProducto.setText("");
    }
}