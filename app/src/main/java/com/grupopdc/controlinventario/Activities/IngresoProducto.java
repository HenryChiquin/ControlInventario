package com.grupopdc.controlinventario.Activities;

import static com.grupopdc.controlinventario.Tools.KeysRoutes.PATH_REGISTRO;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.grupopdc.controlinventario.R;
import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;


public class IngresoProducto extends CoreActivity {
    private static String TAG_CLASS = "INGRESO PRODUCTO CLASS";
    private ImageView imgViewRegresarRegistro;
    private TextInputEditText NombreP, CostoP,CategoriaP, CantidadP;
    private TextInputLayout NombreProd;
    private Button bntRegistro;
    private Spinner spCategoria,spAlmacen;

    private List<CategoriaEntity> Listacategoria;
    private List<AlmacenEntity> Listaalmacen;


    private static final int File = 1;
    DatabaseReference myRef;
    FirebaseStorage firebaseStorage;
    FirebaseDatabase firebaseDatabase;

    private static final String CARPETA_PRINCIPAL="";
    private static final String CARPETA_IMAGEN="";
    private static final String DIRECTORIO_IMAGEN="";
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
        CostoP =  findViewById(R.id.txtPrecioProducto);
        CategoriaP =  findViewById(R.id.txtCategoriaProducto);
        CantidadP =  findViewById(R.id.txtExistenciaProducto);
        bntRegistro =  findViewById(R.id.btnRegistroProducto);

        spCategoria=(Spinner) findViewById(R.id.spCategoria);
        Listacategoria = new ArrayList<>();
        Listaalmacen = new ArrayList<>();

        ListadeCategoria();
        listCategoriaSp();
        listAlmacenSp();


        imgViewRegresarRegistro.setOnClickListener(view -> {
            onBackPressed();

        });

        ButterKnife.bind(this);
        listas = new ArrayList<>();
        spCategoria=(Spinner) findViewById(R.id.spCategoria);


        myRef = FirebaseDatabase.getInstance().getReference();
        mUploadImageView.setOnClickListener(v -> fileUpload());
        //bntRegistro.setOnClickListener(View-> crearCategoria());
        //bntRegistro.setOnClickListener(View-> crearAlmacen());

    }

    public void ListadeCategoria(){
        Listacategoria = repositoryCategoria.getAllCategoriaLista();
        List<String> lcategoria = new ArrayList<>();
        for(int index = 0; index < Listacategoria.size(); index++)
        {
            CategoriaEntity this_categoria = Listacategoria.get(index);
            lcategoria.add(this_categoria.getNombre()+" "+this_categoria.getIdCategoria());
            tools.Log_i("Categoria lista for:" + this_categoria.getNombre() + " :" + this_categoria.getIdCategoria(), "PRODUCTO ACTIVITY");
        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(IngresoProducto.this,
                android.R.layout.simple_list_item_1,lcategoria);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategoria.setAdapter(myAdapter);
    }

    public void crearCategoria(){
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setIdCategoria(2);
        categoriaEntity.setNombre("Gaming");
        repositoryCategoria.insert(categoriaEntity);
        Toast.makeText(getApplicationContext(), "Registro con exito", Toast.LENGTH_SHORT).show();
    }

    public void crearAlmacen(){
        AlmacenEntity almacenEntity = new AlmacenEntity();
        almacenEntity.setIdAlmacen(3);
        almacenEntity.setNombre("C");
        almacenEntity.setUbicacion("Ciudad de Guatemala");
        repositoryAlmacen.insert(almacenEntity);
        Toast.makeText(getApplicationContext(), "Registro con exito", Toast.LENGTH_SHORT).show();
    }

    public void listCategoriaSp(){

        Listacategoria = repositoryCategoria.getAllCategoriaLista();

        List<String> lcategoria = new ArrayList<>();
        for(int index = 0; index < Listacategoria.size(); index++)
        {
            CategoriaEntity this_categoria = Listacategoria.get(index);
            //lcategoria.add(this_categoria.getIdCategoria(),this_categoria.getNombre());
            lcategoria.add(this_categoria.getIdCategoria() +" "+ this_categoria.getNombre());
            //lcategoria.add(this_categoria.getNombre());

        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, lcategoria);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCategoria.setAdapter(myAdapter);


        //String itemCategoria =spCategoria.getItemAtPosition(spCategoria.getSelectedItemPosition()).toString();
        //tools.Log_i("Categoria ID: "+ itemCategoria,"PRODUCTO ACTIVITY");

        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3){
                String workRequestType = arg0.getItemAtPosition(pos).toString();
                CantidadP.setText(workRequestType);
                //String Id = lcategoria.get(pos)
            }
            @Override public void onNothingSelected(AdapterView<?> arg0){

            }
        });
    }

    public void listAlmacenSp(){

        Listaalmacen = repositoryAlmacen.getAllAlmacen();

        List<String> lalmacen = new ArrayList<>();
        for(int index = 0; index < Listaalmacen.size(); index++)
        {
            AlmacenEntity this_almacen = Listaalmacen.get(index);
            //lcategoria.add(this_categoria.getIdCategoria(),this_categoria.getNombre());
            lalmacen.add(this_almacen.getIdAlmacen() +" "+ this_almacen.getNombre() +" "+ this_almacen.getUbicacion());
            //lcategoria.add(this_categoria.getNombre());

        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, lalmacen);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spAlmacen.setAdapter(myAdapter);


        //String itemCategoria =spCategoria.getItemAtPosition(spCategoria.getSelectedItemPosition()).toString();
        //tools.Log_i("Categoria ID: "+ itemCategoria,"PRODUCTO ACTIVITY");

        spAlmacen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3){
                String workRequestType = arg0.getItemAtPosition(pos).toString();
                CantidadP.setText(workRequestType);
                //String Id = lcategoria.get(pos)
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
    public void listaDatos(){

        listas = repositoryCategoria.getAllCategoriaLista();



        for(int index = 0; index < listas.size(); index++) {
            CategoriaEntity this_categoria = listas.get(index);
            this_categoria.getIdCategoria();
            tools.Log_i("Categoria lista for:"+this_categoria.getNombre()+" :" + this_categoria.getIdCategoria(),"PRODUCTO ACTIVITY");

        }

        Toast.makeText(getApplicationContext(), "PPP"+listas, Toast.LENGTH_SHORT).show();
    }
    public void registrodeDatos(){


        String nombre = NombreP.getText().toString();
        String costo = CostoP.getText().toString();
        String idCategoria = CategoriaP.getText().toString();
        String cantidad = CantidadP.getText().toString();

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
                    productoEntity.setIdCategoria(Integer.parseInt(idCategoria));


                    Gson gson = new Gson();
                    String json = gson.toJson(productoEntity);

                    JSONObject jsonObject = new JSONObject(json);

                    String urlWS = PATH_REGISTRO;
                    String response = tools.usedNetworking().post_http_request(urlWS,jsonObject);
                    Toast.makeText(getApplicationContext(),"Response "+response, Toast.LENGTH_SHORT).show();

                } catch (Exception ex) {

                }
                progress.dismiss();
            }
        });
        thread.start();

    }

    /*[VOLLEY POST]*/
    public String POST_HTTP_REQUEST(final String  s_url, final JSONObject jsonBody){
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] responsyBody = new String[1];
        try{
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,s_url,jsonBody,new Response.Listener<JSONObject>(){
                @Override
                public void onResponse(JSONObject response) {
                    responsyBody[0] = response.toString();
                    latch.countDown();
                    Log.i("POST_HTTP_REQUEST", response.toString());
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("onErorrResponse. Razon: "+error,TAG_CLASS);
                    latch.countDown();

                }
            });
            requestQueue.add(jsonObjectRequest);
            latch.await();

        }catch (Exception e) {
            e.printStackTrace();
            Log.e("POST_HTTP_REQUEST general volley", e.toString());
            responsyBody[0] = e.toString();
        }
        return responsyBody[0];

    }
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