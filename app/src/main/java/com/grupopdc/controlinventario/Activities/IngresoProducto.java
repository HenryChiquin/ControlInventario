package com.grupopdc.controlinventario.Activities;

import static com.grupopdc.controlinventario.Tools.KeysRoutes.PATH_REGISTRO;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.grupopdc.controlinventario.R;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;


public class IngresoProducto extends CoreActivity {
    private static String TAG_CLASS = "INGRESO PRODUCTO CLASS";
    private ImageView imgViewRegresarRegistro;
    private TextInputEditText nombreProducto, costoProducto, cantidadProducto;

    private Button bntRegistro;
    private Spinner spCategoria;
    private Handler updateBarHandler;

    private List<CategoriaEntity> Listacategoria;

    private int idcategoriaresponse;

    private static final int File = 1;
    DatabaseReference myRef;
    FirebaseStorage firebaseStorage;
    FirebaseDatabase firebaseDatabase;

    private static final String CARPETA_PRINCIPAL="";
    private static final String CARPETA_IMAGEN="";
    private static final String DIRECTORIO_IMAGEN="";
    private String requestFetch;
    private boolean complet_fetch;

    List<CategoriaEntity> listas;
    ProgressDialog progress;

    @SuppressLint("NonConstantResourceId")


    @BindView(R.id.subirImagenFira)
    ImageView mUploadImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_producto);
        progress = new ProgressDialog(this);

        imgViewRegresarRegistro = findViewById(R.id.imgViewRegresarRegistro);

        nombreProducto =  findViewById(R.id.txtNombreProductoReg);
        costoProducto =  findViewById(R.id.txtPrecioProductoReg);
        cantidadProducto = findViewById(R.id.txtExistenciaProductoReg);

        bntRegistro =  findViewById(R.id.btnAgregarTraslado);

        spCategoria=(Spinner) findViewById(R.id.spCategoria);

        updateBarHandler = new Handler();
        Listacategoria = new ArrayList<>();



        listCategoriaSp();


        imgViewRegresarRegistro.setOnClickListener(view -> {
            onBackPressed();

        });

        ButterKnife.bind(this);
        listas = new ArrayList<>();
        spCategoria=(Spinner) findViewById(R.id.spCategoria);


        myRef = FirebaseDatabase.getInstance().getReference();
        mUploadImageView.setOnClickListener(v -> fileUpload());

        bntRegistro.setOnClickListener(View-> registrodeDatos());

    }



    //LISTA CATEGORIA SPINNER
    public void listCategoriaSp(){

        Listacategoria = repositoryCategoria.getAllCategoriaLista();
        List<CategoriaEntity> listacategoria = new ArrayList<>();


        for(CategoriaEntity ct : Listacategoria)
        {
            int id = ct.getIdCategoria();
            String nombre = ct.getNombre();
            listacategoria.add(new CategoriaEntity(id,nombre));
        }
        ArrayAdapter<CategoriaEntity> myAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1, listacategoria);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCategoria.setAdapter(myAdapter);

        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3){
                String workRequestType = arg0.getItemAtPosition(pos).toString();

                int Id = listacategoria.get(pos).getIdCategoria();
                idcategoriaresponse = Id;
            }
            @Override public void onNothingSelected(AdapterView<?> arg0){

            }
        });
    }


    public void fileUpload() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,File);
    }

    /*[VOLLEY POST]*/
    public void registrodeDatos(){


        String nombre = nombreProducto.getText().toString().trim();
        String costo = costoProducto.getText().toString().trim();
        int idCategoria = idcategoriaresponse;
        String cantidad = cantidadProducto.getText().toString().trim();

        progress.setTitle("Procesando");
        progress.setMessage("Por favor espere ...");
        progress.setCancelable(false);
        progress.show();
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                //tarea a realizar
                try {
                    ProductoEntity productoEntity = new ProductoEntity();

                    productoEntity.setNombre(nombre);
                    productoEntity.setCosto(Float.parseFloat(costo));
                    productoEntity.setCantidad(Integer.parseInt(cantidad));
                    productoEntity.setIdCategoria(idCategoria);


                    Gson gson = new Gson();
                    String json = gson.toJson(productoEntity);

                    JSONObject jsonObject = new JSONObject(json);

                    String urlWS = PATH_REGISTRO;
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

            }else{
                tools.MakeToast("Error al momento de registrar");
            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == File){

            if(resultCode == RESULT_OK){

                Uri FileUri = data.getData();

                StorageReference Folder = FirebaseStorage.getInstance().getReference().child("User");

                final StorageReference file_name = Folder.child("file"+FileUri.getLastPathSegment());



                file_name.putFile(FileUri).addOnSuccessListener(taskSnapshot -> file_name.getDownloadUrl().addOnSuccessListener(uri -> {

                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("link", String.valueOf(uri));
                    myRef.setValue(hashMap);
                    tools.MakeToast("Imagen guardado correctamente");
                    Log.d("Mensaje", "Se subió correctamente");


                }));


            }

        }

    }
}