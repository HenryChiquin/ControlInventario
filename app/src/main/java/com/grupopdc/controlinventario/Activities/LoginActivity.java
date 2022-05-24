package com.grupopdc.controlinventario.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.grupopdc.controlinventario.R;
import com.grupopdc.controlinventario.database.Entity.MonedaEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends CoreActivity {
    private Context ctx;
    private static String TAG_CLASS = "LOGIN ACTIVITY";
    Button btnAccederMenuPrincipal;
    TextInputEditText edtextUser, edtextPass;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
    }
    private void initComponents(){
        ctx = this;
        progress = new ProgressDialog(ctx);
        edtextUser = findViewById(R.id.form_login_username);
        edtextPass = findViewById(R.id.form_login_password);

        btnAccederMenuPrincipal = findViewById(R.id.btnAgregarTraslado);
        btnAccederMenuPrincipal.setOnClickListener(View -> requestAPIlogin() );

    }

    private void requestAPIlogin() {
        String tmp_login_username = edtextUser.getText().toString();
        String tmp_login_password = edtextPass.getText().toString();

        edtextPass.setText("");//CLEAN PASSWORD FIELD

        InputMethodManager mgr = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(edtextPass.getWindowToken(), 0);

        //---[VALIDACIONES]---
        if( tmp_login_username.isEmpty()){
            edtextUser.setError("El campo está vacío");
            return;
        }
        if( tmp_login_password.isEmpty()){
            edtextPass.setError("El campo está vacío");
            return;
        }
        //---[FIN VALIDACIONES]---

        /*------------------------------------------------------------------------*/
        if(!PREF_MODE_OFFLINE){
            SEND_LOGIN(tmp_login_username.trim(), tmp_login_password.trim(), tools.getServer() + "api/usuario/login");
        }else{
            tools.MakeToast(getResources().getString(R.string.toast_mode_offline_activate));
        }
        /*------------------------------------------------------------------------*/
    }
    private void  SaveAccessData(int user_iduser,String user_username, String auth_token,String user_empresa,int user_id_perfil, String user_perfil,int user_id_pais,String user_pais){
        try {
            tools.getEditor().putInt(getString(R.string.pref_SESION), 1);

            tools.getEditor().putInt(getString(R.string.pref_IDUSER), user_iduser);
            tools.getEditor().putString(getString(R.string.pref_USERNAME), user_username);
            tools.getEditor().putString(getString(R.string.pref_ACCESS_TOKEN), auth_token);
            tools.getEditor().putString(getString(R.string.pref_USER_EMPRESA), user_empresa);
            tools.getEditor().putString(getString(R.string.pref_USER_PERFIL), user_perfil);
            tools.getEditor().putInt(getString(R.string.pref_USER_ID_PERFIL), user_id_perfil);
            tools.getEditor().putString(getString(R.string.pref_USER_PAIS), user_pais);
            tools.getEditor().putInt(getString(R.string.pref_USER_ID_PAIS),user_id_pais);

            tools.getEditor().commit();

        }catch (Exception e){
            tools.Log_e("SaveAccessData, PREF NO PUEDE GUARDAR DATO. Razon: "+e, TAG_CLASS);
        }

    }

    ///////[ASYNC METHODS]//////////////////////////////////////////////////////////////////////////
    private void SEND_LOGIN(final String login_username, final String login_password, final String s_url){
        class fetch_data extends AsyncTask<String, String, Void> {
            boolean complete_fetch;
            String response_fetch;

            ProgressDialog progress;

            /*------------------------------------------------------------------------*/
            final String META_OBJECT = "login request";
            /*------------------------------------------------------------------------*/

            protected void onPreExecute() {
                tools.Log_i("Start PreExecute ASYNC [Action:"+META_OBJECT+"]", TAG_CLASS);


                complete_fetch = false;

                progress = new ProgressDialog(ctx);
                progress.setTitle("Autenticando");
                progress.setMessage("por favor esperar...");
                progress.setCancelable(false);

                progress.show();

                tools.Log_i("End PreExecute ASYNC [Action:" + META_OBJECT + "]", TAG_CLASS);
            }

            @SuppressWarnings("unchecked")
            @Override
            protected Void doInBackground(String... params) {
                tools.Log_i("Start doBackground ASYNC [Action:" + META_OBJECT + "]", TAG_CLASS);
                try {
                    Gson gson = new Gson();
                    JSONObject loginAuthResponse = new JSONObject();
                    loginAuthResponse.put("CuentaUsuario",login_username);
                    loginAuthResponse.put("Contraseña",login_password);


                    String fetch_response = tools.usedNetworking().post_http_request(s_url, loginAuthResponse);

                    JSONObject g_json = new JSONObject(fetch_response);
                    String mensaje_response = g_json.getString("mensaje");
                    JSONObject dataall = g_json.getJSONObject("data");

                    SaveAccessData(
                            dataall.getInt("idUsuario"),
                            dataall.getString("cuentaUsuario"),
                            dataall.getString("token"),
                            dataall.getString("empresa"),
                            dataall.getInt("idPerfil"),
                            dataall.getString("perfil"),
                            dataall.getInt("idPais"),
                            dataall.getString("pais")
                            );

                    String moneda_json = dataall.getString("moneda");


                    JSONArray arr = new JSONArray(moneda_json);
                    for (int i = 0; i < arr.length(); i++){
                        repositoryMoneda.insert(new MonedaEntity(
                                Integer.parseInt(arr.getJSONObject(i).get("idMoneda").toString()),
                                arr.getJSONObject(i).get("nombre").toString(),
                                arr.getJSONObject(i).get("abreviatura").toString(),
                                arr.getJSONObject(i).get("codigo").toString(),
                                Integer.parseInt(arr.getJSONObject(i).get("idPais").toString())
                        ));
                    }
                    response_fetch = mensaje_response;

                    complete_fetch = true;
                } catch (Exception e) {
                    tools.Log_e("ERROR - Se ha interrumpido la conexion [Action:"+META_OBJECT+"]. Razon: "+e,TAG_CLASS);
                }

                tools.Log_i("End doBackground ASYNC [Action:"+META_OBJECT+"]", TAG_CLASS);
                return null;
            }

            protected void onPostExecute(Void v) {
                tools.Log_i("Start PostExecute ASYNC [Action:" + META_OBJECT + "]", TAG_CLASS);
                progress.dismiss();
                if(complete_fetch) {
                    AcccederMenuPrincipal();
                    tools.MakeToast(response_fetch);
                }else {
                    tools.MakeToast("Error en la sesión.");
                }


                tools.Log_i("End PostExecute ASYNC [Action:" + META_OBJECT + "]. STATUS: ", TAG_CLASS);
            }
        }

        if(tools.usedNetworking().isNetworkAvailable(this)){
            fetch_data fd = new fetch_data();
            try {
                fd.execute();
            } catch (Exception e) {
                fd.cancel(true);
                tools.MakeToast(getString(R.string.psvtxt_error_tryagain));
                //qkcache.SESSION_STATUS = "NOSYNC";
            }
        } else {
            tools.MakeToast(getString(R.string.psvtxt_internetfail));
            //qkcache.SESSION_STATUS = "NOSYNC";
        }
    }



    public void AcccederMenuPrincipal(){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}