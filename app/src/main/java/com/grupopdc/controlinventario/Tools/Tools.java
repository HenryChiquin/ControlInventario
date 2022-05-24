package com.grupopdc.controlinventario.Tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.grupopdc.controlinventario.Activities.CoreActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools extends LogMessage {

    private static String TAG_CLASS = "TOOLS CLASS";

    private Networking networking;
    private SharedPreferences pref;
    private SharedPreferences.Editor edit;
    private Context ctx;

    public Tools(Context ctx) {
        networking = new Networking(ctx);
        this.ctx = ctx;
        pref = ctx.getSharedPreferences("DataGeneralPref", 0);
        edit = pref.edit();
    }
    ////////////////////[NETWORK]///////////////////////////////////////////////////////////////////
    public Networking usedNetworking(){
        return networking;
    }


    ////////////////////////[PREFERENCES]///////////////////////////////////////////////////////////

    public SharedPreferences getPref(){
        return pref;
    }

    public SharedPreferences.Editor getEditor(){
        return edit;
    }

    public String TrimAndGet(String cadena, String trimer, int index){
        String s;
        try{
            String[] a = cadena.split(trimer);
            s = a[index];
        }catch (Exception e){
            s = "ERROR";
        }
        return  cleanWhiteSpaces(s);
    }


    public String cleanWhiteSpaces(String s){
        String r = s.replaceAll("\\s","");
        return r;
    }
    public String PedidoKey(){
        SimpleDateFormat sdfdate  = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date now = new Date();
        String str_date  = sdfdate.format(now);

        String rev_a = str_date.replace("-", "");
        String rev_b = rev_a.replace(":","");
        String rev_c = rev_b.replace(" ", "");

        return  rev_c;
    }
    ////LIMPIA FORMATO DE RAILS DE DATETIME 2016-06-07T16:32:54-06:00 >>> 07-06-2016 (FECHA)
    public String RailsFormatCleanDate(String date){
        String value;
        try {
            String[] a = date.split("T");
            value = a[0];
        }catch(Exception e){
            Log_e("Error Clean Date (Phase 1). Reason: "+e,TAG_CLASS);
            value = date;
        }

        String new_value;
        try{
            String[] a = value.split("-");
            new_value = a[2]+"/"+a[1]+"/"+a[0];
        }catch (Exception e){
            Log_e("Error Clean Date (Phase 2). Reason: "+e,TAG_CLASS);
            new_value = value;
        }
        return new_value;
    }
    public String EncodeDatevalue(String input){
        String[] arr = input.split("/");
        String sd = arr[0];
        String sm = arr[1];
        String sy = arr[2];
        String output = sy+sm+sd;
        return output;
    }

    public String DecodeDatevalue(String input){
        String sy = input.substring(0,4);
        String sm = input.substring(4,6);
        String sd = input.substring(6,8);
        String output = sd+"/"+sm+"/"+sy;
        return  output;
    }
    public String getServer(){
        String url_server = KeysRoutes.PATH_INVENTORYCONTROL;

        return url_server;
    }

}
