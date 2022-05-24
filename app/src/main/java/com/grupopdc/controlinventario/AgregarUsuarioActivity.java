package com.grupopdc.controlinventario;

import static com.grupopdc.controlinventario.Tools.KeysRoutes.PATH_REGISTRO;
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
import com.google.gson.Gson;
import com.grupopdc.controlinventario.Activities.CoreActivity;
import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;
import com.grupopdc.controlinventario.database.Entity.EmpresaEntity;
import com.grupopdc.controlinventario.database.Entity.PaisEntity;
import com.grupopdc.controlinventario.database.Entity.PerfilEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AgregarUsuarioActivity extends CoreActivity {
    private static String TAG_CLASS = "AGREGAR_USUARIO ACTIVITY";
    private Spinner spPais,spEmpresa,spPerfil;
    private List<PaisEntity> listItemPais;
    private List<EmpresaEntity> listItemEmpresa;
    private List<PerfilEntity> listItemPerfil;
    private int idPais,idPerfil, idEmpresa;
    private TextInputEditText txtNombreUser, txtDPI, txtDireccion, txtZonaUser, txtCuentaUser, txtClaveUser;
    private Button btnRegistrarUser;
    private ImageView imgViewRegresarRegistro2;
    ProgressDialog progress;
    private Handler updateBarHandler;

    private String requestFetch;
    private boolean complet_fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);
        progress = new ProgressDialog(this);

        initComponents();
    }

    private void initComponents(){
        spPais = findViewById(R.id.spPais);
        spEmpresa = findViewById(R.id.spEmpresa);
        spPerfil = findViewById(R.id.spPerfil);

        txtNombreUser = findViewById(R.id.txtNombreUser);
        txtDPI = findViewById(R.id.txtDPI);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtZonaUser = findViewById(R.id.txtZonaUser);
        txtCuentaUser = findViewById(R.id.txtCuentaUser);
        txtClaveUser = findViewById(R.id.txtClaveUser);
        btnRegistrarUser = findViewById(R.id.btnRegistrarUser);
        imgViewRegresarRegistro2 = findViewById(R.id.imgViewRegresarRegistro2);

        updateBarHandler = new Handler();

        spPaisList();
        spEmpresaList();
        spPerfilList();


        imgViewRegresarRegistro2.setOnClickListener(view -> {
            onBackPressed();

        });
        btnRegistrarUser.setOnClickListener(view -> {
            registrodeDatos();
        });
    }


    /*[VOLLEY POST]*/
    private void registrodeDatos(){


        String Nombre = txtNombreUser.getText().toString().trim();
        String DPI = txtDPI.getText().toString().trim();
        String Direccion = txtDireccion.getText().toString().trim();
        int Zona = Integer.parseInt(txtZonaUser.getText().toString().trim());
        String CuentaUser = txtCuentaUser.getText().toString().trim();
        String ClaveUser = txtClaveUser.getText().toString().trim();


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
                    jsonObject.put("DPI",DPI);
                    jsonObject.put("Direccion",Direccion);
                    jsonObject.put("Zona",Zona);
                    jsonObject.put("CuentaUsuario",CuentaUser);
                    jsonObject.put("Contraseña",ClaveUser);
                    jsonObject.put("IdPais",idPais);
                    jsonObject.put("IdPerfil",idPerfil);
                    jsonObject.put("IdEmpresa",idEmpresa);

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



    private void spPaisList(){
        listItemPais = repositoryPais.getAllPaisLista();

        List<PaisEntity> listaPais = new ArrayList<>();

        for(PaisEntity alm : listItemPais)
        {
            int id = alm.getIdPais();
            String nombre = alm.getNombre();
            listaPais.add(new PaisEntity(id,nombre));
        }


        ArrayAdapter<PaisEntity> myAdapter = new ArrayAdapter<PaisEntity>(getApplicationContext(),
                android.R.layout.simple_list_item_1,listaPais);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPais.setAdapter(myAdapter);
        spPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3){
                String workRequestType = arg0.getItemAtPosition(pos).toString();

                int Id = listaPais.get(pos).getIdPais();
                idPais = Id;
                tools.Log_i("IdPais: "+idPais,TAG_CLASS);

            }
            @Override public void onNothingSelected(AdapterView<?> arg0){

            }
        });
    }

    private void spEmpresaList(){
        listItemEmpresa = repositoryEmpresa.getAllEmpresaLista();

        List<EmpresaEntity> listaEmpresa = new ArrayList<>();

        for(EmpresaEntity alm : listItemEmpresa)
        {
            int id = alm.getIdEmpresa();
            String nombre = alm.getNombre();
            listaEmpresa.add(new EmpresaEntity(id,nombre));
        }


        ArrayAdapter<EmpresaEntity> myAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,listaEmpresa);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEmpresa.setAdapter(myAdapter);
        spEmpresa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3){
                String workRequestType = arg0.getItemAtPosition(pos).toString();

                int Id = listaEmpresa.get(pos).getIdEmpresa();
                idEmpresa = Id;
                tools.Log_i("IdEmpresa: "+idEmpresa,TAG_CLASS);

            }
            @Override public void onNothingSelected(AdapterView<?> arg0){

            }
        });
    }

    private void spPerfilList(){
        listItemPerfil = repositoryPerfil.getAllPerfilLista();

        List<PerfilEntity> listaPerfil = new ArrayList<>();

        for(PerfilEntity alm : listItemPerfil)
        {
            int id = alm.getIdPerfil();
            String nombre = alm.getNombre();
            listaPerfil.add(new PerfilEntity(id,nombre));
        }


        ArrayAdapter<PerfilEntity> myAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,listaPerfil);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPerfil.setAdapter(myAdapter);
        spPerfil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3){
                String workRequestType = arg0.getItemAtPosition(pos).toString();

                int Id = listaPerfil.get(pos).getIdPerfil();
                idPerfil = Id;
                tools.Log_i("IdPerfil: "+idPerfil,TAG_CLASS);
            }
            @Override public void onNothingSelected(AdapterView<?> arg0){

            }
        });
    }


    //CLEAN
    private void clean(){
        spPaisList();
        spEmpresaList();
        spPerfilList();
        txtNombreUser.setText("");
        txtDPI.setText("");
        txtDireccion.setText("");
        txtZonaUser.setText("");
        txtCuentaUser.setText("");
        txtClaveUser.setText("");
    }
}