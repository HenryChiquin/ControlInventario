package com.grupopdc.controlinventario.Tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.concurrent.CountDownLatch;

public class Networking extends LogMessage{
    private static String TAG_CLASS = "NETWORK CLASS";
    private Context ctx;
    private final int TIMEOUT_DEF_CONNNECTION  = 1000;
    private final int TIMEOUT_DEF_RESPONSE_DATA = 1000;
    private int TIMEOUT_CONNECTION = TIMEOUT_DEF_CONNNECTION;//MILLISEC.
    private int TIMEOUT_RESPONSE_DATA = TIMEOUT_DEF_RESPONSE_DATA;//MILLISEC.

    public Networking(Context ctx) {

        this.ctx = ctx;
        LogMessage.this_ctx = ctx;
    }

    public void ChangeTimeouts(float reason_conn, float reason_resp){
        TIMEOUT_CONNECTION = (int)(TIMEOUT_DEF_CONNNECTION * reason_conn);//MILLISEC.
        TIMEOUT_RESPONSE_DATA = (int)(TIMEOUT_DEF_RESPONSE_DATA * reason_resp);//MILLISEC.
    }

    public String post_http_request(final String  s_url, final JSONObject jsonBody){
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] responsyBody = new String[1];
        try{
            RequestQueue requestQueue = Volley.newRequestQueue(ctx);
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


    ///////[ASYNC AUX METHODS]//////////////////////////////////////////////////////////////////////
    public boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting() && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            Log_i("isNetworkAvailable - Conexion de red disponible.", TAG_CLASS);
            return true;
        }else {
            Log_e("isNetworkAvailable - ERROR : Conexion de red no disponible.", TAG_CLASS);
            return false;
        }
    }
}
