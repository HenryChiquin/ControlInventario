package com.grupopdc.controlinventario.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.grupopdc.controlinventario.R;

public class LoginActivity extends CoreActivity {
    private Context ctx;
    private static String TAG_CLASS = "LOGIN ACTIVITY";
    Button btnAccederMenuPrincipal;

    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnAccederMenuPrincipal = findViewById(R.id.btnAgregarTraslado);
        btnAccederMenuPrincipal.setOnClickListener(View -> AcccederMenuPrincipal());
        ctx = this;
        progress = new ProgressDialog(ctx);

    }

    public void AcccederMenuPrincipal(){
        Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}