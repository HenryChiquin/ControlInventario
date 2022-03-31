package com.grupopdc.controlinventario.Tools;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.concurrent.CountDownLatch;

public class Networking {
    private static String TAG_CLASS = "NETWORK CLASS";
    private Context ctx;


    public Networking(Context ctx) {

        this.ctx = ctx;
        LogMessage.this_ctx = ctx;
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
}
