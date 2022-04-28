package com.grupopdc.controlinventario.Tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.grupopdc.controlinventario.Activities.CoreActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools extends LogMessage {
    private Networking networking;
    private SharedPreferences pref;
    private SharedPreferences.Editor edit;
    private Context ctx;

    public Tools(CoreActivity ctx) {
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

}
