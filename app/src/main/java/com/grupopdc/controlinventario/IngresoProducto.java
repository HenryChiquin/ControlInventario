package com.grupopdc.controlinventario;

import static com.grupopdc.controlinventario.Tools.KeysRoutes.PATH_REGISTRO;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
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
import com.grupopdc.controlinventario.database.Entity.CategoriaEntiry;
import com.grupopdc.controlinventario.database.Entity.Producto;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
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


    private static final int File = 1;
    DatabaseReference myRef;
    FirebaseStorage firebaseStorage;
    FirebaseDatabase firebaseDatabase;

    private static final String CARPETA_PRINCIPAL="";
    private static final String CARPETA_IMAGEN="";
    private static final String DIRECTORIO_IMAGEN="";

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
        NombreP = findViewById(R.id.txtNombreProducto);
        CostoP =  findViewById(R.id.txtPrecioProducto);
        CategoriaP =  findViewById(R.id.txtCategoriaProducto);
        CantidadP =  findViewById(R.id.txtExistenciaProducto);
        bntRegistro =  findViewById(R.id.btnRegistroProducto);


        imgViewRegresarRegistro.setOnClickListener(view -> {
            onBackPressed();

        });

        ButterKnife.bind(this);

        myRef = FirebaseDatabase.getInstance().getReference();

        mUploadImageView.setOnClickListener(v -> fileUpload());

        bntRegistro.setOnClickListener(View-> registrodeDatos());
        CategoriaEntiry categoriaEntiry = new CategoriaEntiry();
        categoriaEntiry.setIdCategoria(2);
        categoriaEntiry.setNombre("PRUEBA 2");
        repositoryCategoria.insert(categoriaEntiry);

    }
    public void obtenerdatos(){

        myRef.child("user1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String link = snapshot.child("link").getValue().toString();
                NombreP.setText(link);
                Log.i("LINK image storage: "+link,"PRODUCTO ACTIVITY");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void fileUpload() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,File);
    }

    public void registrodeDatos(){

        String nombre = NombreP.getText().toString();
        String costo = CostoP.getText().toString();
        String idCategoria = CategoriaP.getText().toString();
        String cantidad = CantidadP.getText().toString();
        String fechaVencimiento = "2022-03-02";
        String image = "https://walmartgt.vtexassets.com/arquivos/ids/176274/Jugo-Rabinal-Mango-Pi-a-1800ml-1-15127.jpg";

        progress.setTitle("Procesando");
        progress.setMessage("Por favor espere ...");
        progress.setCancelable(false);
        progress.show();
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                //tarea a realizar
                try {
                    Producto producto = new Producto();

                    producto.setNombre(nombre);
                    producto.setCosto(Float.parseFloat(costo));
                    producto.setCategoria(Integer.parseInt(idCategoria));
                    producto.setCantidad(Integer.parseInt(cantidad));
                    producto.setFechaVencimiento(fechaVencimiento);
                    producto.setImagen(image);

                    Gson gson = new Gson();
                    String json = gson.toJson(producto);

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

                    Log.d("Mensaje", "Se subió correctamente");

                }));


            }

        }

    }
}