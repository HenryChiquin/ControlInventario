package com.grupopdc.controlinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    Button btnAccederMenuPrincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnAccederMenuPrincipal = findViewById(R.id.btnIngresarMenuPrincipal);
        btnAccederMenuPrincipal.setOnClickListener(View -> AcccederMenuPrincipal());

    }

    public void AcccederMenuPrincipal(){
        Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }
}