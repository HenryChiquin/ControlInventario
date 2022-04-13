package com.grupopdc.controlinventario.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.grupopdc.controlinventario.R;

public class TrasladoActivity extends AppCompatActivity {
    private ImageView imgViewRegresarTraslado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traslado);


        imgViewRegresarTraslado = findViewById(R.id.imgViewRegresarTraslado);

        imgViewRegresarTraslado.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}