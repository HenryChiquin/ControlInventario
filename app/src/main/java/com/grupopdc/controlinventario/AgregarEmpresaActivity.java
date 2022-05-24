package com.grupopdc.controlinventario;

import static com.grupopdc.controlinventario.Tools.KeysRoutes.PATH_REGISTRO_USER;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.grupopdc.controlinventario.Activities.CoreActivity;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AgregarEmpresaActivity extends CoreActivity {
    private TextInputEditText txtNombreRazonSocial,txtDominio;
    private Button btnRegEmpresa;
    private ImageView imgVRegEmpresa;
    ProgressDialog progress;
    private Handler updateBarHandler;

    private String requestFetch;
    private boolean complet_fetch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_empresa);
        progress = new ProgressDialog(this);
        initComponents();
    }

    private void initComponents(){
        txtNombreRazonSocial = findViewById(R.id.txtNombreRazonSocial);
        txtDominio = findViewById(R.id.txtDominio);
        btnRegEmpresa = findViewById(R.id.btnRegEmpresa);
        imgVRegEmpresa = findViewById(R.id.imgVRegEmpresa);

        updateBarHandler = new Handler();


        imgVRegEmpresa.setOnClickListener(view -> {
            onBackPressed();

        });
        btnRegEmpresa.setOnClickListener(view -> {
            registrodeDatos();
        });
    }

    public void registrodeDatos(){


        String Nombre = txtNombreRazonSocial.getText().toString().trim();
        String Dominio = txtDominio.getText().toString().trim();


        progress.setTitle("Procesando");
        progress.setMessage("Por favor espere ...");
        progress.setCancelable(false);
        progress.show();
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                //tarea a realizar
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Nombre",Nombre);
                    jsonObject.put("Dominio",Dominio);


                    String urlWS = PATH_REGISTRO_USER;
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
                clean();
            }else{
                tools.MakeToast("Error al momento de registrar");
            }
        }
    };

    //CLEAN
    private void clean(){
        txtNombreRazonSocial.setText("");
        txtDominio.setText("");

    }
}