package com.grupopdc.controlinventario.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.grupopdc.controlinventario.Api.MassDataAPI;
import com.grupopdc.controlinventario.Api.MassDataResponse;
import com.grupopdc.controlinventario.Api.ServiceGeneratorGetAllData;
import com.grupopdc.controlinventario.R;
import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;
import com.grupopdc.controlinventario.database.Entity.ImpuestoEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;
import com.grupopdc.controlinventario.database.Entity.ProveedorEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends CoreActivity {
    private Context ctx;
    private static String TAG_CLASS = "LOGIN ACTIVITY";
    Button btnAccederMenuPrincipal;

    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnAccederMenuPrincipal = findViewById(R.id.btnRegistroProducto);
        btnAccederMenuPrincipal.setOnClickListener(View -> AcccederMenuPrincipal());
        ctx = this;
        progress = new ProgressDialog(ctx);

    }

    public void AcccederMenuPrincipal(){

        Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        //downloadGetAllMassData();
    }
    /* ASYNC TASK - DESCARGA DE TODA LA INFORMACION CON RETROFIT*/
    public void downloadGetAllMassData(){
        class downloadDataSFA extends AsyncTask<String, String, Void> {
            final String META_OBJECT = "get massdata";

            protected void onPreExecute(){
                final float TIMEOUT_CONN = 20.0f;
                final float TIMEOUT_RESP = 65.0f;
                tools.usedNetworking().ChangeTimeouts(TIMEOUT_CONN, TIMEOUT_RESP);
                progress.setTitle("Autenticando");
                progress.setMessage("por favor esperar...");
                progress.setCancelable(false);
                progress.show();
            }
            @Override
            protected Void doInBackground(String... strings) {
                    try {


                        MassDataAPI massDataAPI = ServiceGeneratorGetAllData.createService(MassDataAPI.class);

                        final Call<MassDataResponse> requestMassDataINV = massDataAPI.obtenerMassData();
                        requestMassDataINV.enqueue(new Callback<MassDataResponse>() {
                            @Override
                            public void onResponse(Call<MassDataResponse> call, Response<MassDataResponse> response) {
                                if (response.isSuccessful()) {
                                    tools.MakeToast("Autenticado correctamente");
                                    qkcache.LIST_PRODUCTO = response.body().getDataResponse().getProductoEntities();
                                    qkcache.LIST_CATEGORIA = response.body().getDataResponse().getCategoriaEntities();
                                    qkcache.LIST_ALMACEN = response.body().getDataResponse().getAlmacenEntities();
                                    qkcache.LIST_PROVEEDOR = response.body().getDataResponse().getProveedorEntities();
                                    qkcache.LIST_IMPUESTOS = response.body().getDataResponse().getImpuestoEntities();
                                    progress.dismiss();
                                    saveDataIntoDB();
                                }
                            }

                            @Override
                            public void onFailure(Call<MassDataResponse> call, Throwable t) {
                                progress.dismiss();
                                tools.Log_e("ERROR onFailure retrofit ", TAG_CLASS);
                            }
                        });
                    }
                    catch (Exception e) {
                        progress.dismiss();
                        tools.Log_e("ERROR - Fallo en la URL [Action:" + META_OBJECT + "]. Razon: " + e, TAG_CLASS);
                    }

                return null;
            }
        }
        if(tools.usedNetworking().isNetworkAvailable(this)){
            downloadDataSFA downloadDataSFA = new downloadDataSFA();
            try {
                downloadDataSFA.execute();

            } catch (Exception e){
                downloadDataSFA.cancel(true);
                tools.MakeToast("Se ha producido un erro, vuelva a intentar");
            }

        } else{
            tools.MakeToast("No hay conexion a internet");
        }

    }
    /* ASYNC TASK - GUARDADO DE DATOS MASSDATA EN DB (POST EJECUCION REFRESCA ACTIVIDAD) */
    public void saveDataIntoDB(){
        class SaveDataAsyncTask extends AsyncTask<String, String, Void> {
            private int regErrors = 0;
            private int datasize = 0;


            protected void onPreExecute(){

                tools.Log_i("Removing old DB data", TAG_CLASS);

                progress.setTitle("Guardando datos");
                progress.setMessage("por favor esperar...");
                progress.setCancelable(false);
                progress.show();
                try {
                    String progress_message = "";

                    tools.Log_i("SIZE PRODUCTOS             : " + qkcache.LIST_PRODUCTO.size(), TAG_CLASS);
                    tools.Log_i("SIZE CATEGORIAS            : " + qkcache.LIST_CATEGORIA.size(), TAG_CLASS);
                    tools.Log_i("SIZE ALMACENES             : " + qkcache.LIST_ALMACEN.size(), TAG_CLASS);
                    tools.Log_i("SIZE PROVEEDORES           : " + qkcache.LIST_PROVEEDOR.size(), TAG_CLASS);
                    tools.Log_i("SIZE IMPUESTOS             : " + qkcache.LIST_IMPUESTOS.size(), TAG_CLASS);


                    progress_message +=
                                    qkcache.LIST_PRODUCTO.size()+" PRODUCTOS\n"+
                                    qkcache.LIST_CATEGORIA.size()+" CATEGORIA\n"+
                                    qkcache.LIST_ALMACEN.size()+" ALMACEN\n"+
                                    qkcache.LIST_PROVEEDOR.size()+" PROVEEDOR\n"+
                                    qkcache.LIST_IMPUESTOS.size()+" IMPUESTO\n";


                    progress.setMessage(progress_message);
                }catch (Exception d){
                    tools.Log_e("Error created progress dialog. Razon: " + d, TAG_CLASS);
                }
            }
            //------------[ADD TO DB]-----------------------
            @Override
            protected Void doInBackground(String... params) {

                //////[PRODUCTO]///////  ROOM
                try {
                    for (ProductoEntity productoEntity : qkcache.LIST_PRODUCTO)
                        repositoryProducto.insert(productoEntity);
                } catch (Exception e) {
                    regErrors++;
                    tools.Log_e("Error saving sync date DB. [PRODUCTO] Razon: " + e, TAG_CLASS);
                }
                //////[CATEGORIA]///////  ROOM
                try {
                    for (CategoriaEntity categoriaEntity : qkcache.LIST_CATEGORIA)
                        repositoryCategoria.insert(categoriaEntity);
                } catch (Exception e) {
                    regErrors++;
                    tools.Log_e("Error saving sync date DB. [CATEGORIA] Razon: " + e, TAG_CLASS);
                }
                //////[ALMACEN]///////  ROOM
                try {
                    for (AlmacenEntity almacenEntity : qkcache.LIST_ALMACEN)
                        repositoryAlmacen.insert(almacenEntity);
                } catch (Exception e) {
                    regErrors++;
                    tools.Log_e("Error saving sync date DB. [ALMACEN] Razon: " + e, TAG_CLASS);
                }
                //////[PROVEEDOR]///////  ROOM
                try {
                    for (ProveedorEntity proveedorEntity : qkcache.LIST_PROVEEDOR)
                        repositoryProveedor.insert(proveedorEntity);
                } catch (Exception e) {
                    regErrors++;
                    tools.Log_e("Error saving sync date DB. [PROVEEDOR] Razon: " + e, TAG_CLASS);
                }
                //////[IMPUESTO]/////// ROOM
                try {
                    for (ImpuestoEntity impuestoEntity : qkcache.LIST_IMPUESTOS)
                        repositoryImpuesto.insert(impuestoEntity);
                } catch (Exception e) {
                    regErrors++;
                    tools.Log_e("Error saving sync date DB. [IMPUESTO] Razon: " + e, TAG_CLASS);
                }

                tools.Log_i("Complete create new DB data. Errors register: "+regErrors, TAG_CLASS);
                return null;
            }

            protected void onPostExecute(Void v){
                try {////[SAVE DATE OF LAST DB SYNC]////////////////
                    tools.MakeToast("Guardado Completado");
                    Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Iniciando sesion", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    tools.Log_e("Error saving sync date DB. Razon: " + e, TAG_CLASS);
                }
                progress.dismiss();

            }
        }
        SaveDataAsyncTask saveDataAsyncTask = new SaveDataAsyncTask();
        saveDataAsyncTask.execute();



    }
}