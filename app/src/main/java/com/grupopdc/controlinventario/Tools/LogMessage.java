package com.grupopdc.controlinventario.Tools;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class LogMessage {
    public static boolean DEBUG_MODE = true;
    public static Context this_ctx;
    public LogMessage(){
        //DO NOTHING
    }

    public void Log_i(String text, String TAG_CLASS){
        if(DEBUG_MODE)
            Log.i("DEBUG_LOG - " + TAG_CLASS, text);
    }


    public void Log_e(String text, String TAG_CLASS){
        if(DEBUG_MODE)
            Log.e("DEBUG_LOG - "+TAG_CLASS, text);
    }


    public void MakeToast(String toast_text){
        Toast.makeText(this_ctx, toast_text, Toast.LENGTH_LONG).show();
    }

}
