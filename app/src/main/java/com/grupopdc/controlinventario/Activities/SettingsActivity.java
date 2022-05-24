package com.grupopdc.controlinventario.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.grupopdc.controlinventario.R;
import com.grupopdc.controlinventario.Tools.QuickCache;
import com.grupopdc.controlinventario.databinding.ActivitySettingsBinding;

public class SettingsActivity extends CoreActivity {

    private static String TAG_CLASS = "SETTINGS ACTIVITY";

    private AppBarConfiguration mAppBarConfiguration;
    private ActivitySettingsBinding binding;
    private boolean click = false;
    private FloatingActionButton fabsettings,fabcloselogin;
    private Animation fabOpen, fabClose, rotateForward, rotateBackward;
    private boolean isOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //PREF_SESION= tools.getPref().getInt(getString(R.string.pref_SESION), 0);
        tools.Log_i("SESSION: "+PREF_SESION, TAG_CLASS);
        if(PREF_SESION == 1){
            initComponents();
        }else{
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }


    }
    private void initComponents(){
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarSettings.toolbar);




        fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this,R.anim.rotate_backward);

        binding.appBarSettings.fabsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });

        binding.appBarSettings.fabclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Toast.makeText(getApplicationContext(),"Sesión finalizada",Toast.LENGTH_SHORT).show();
                tools.getEditor().putInt(getString(R.string.pref_SESION), 0);
                tools.getEditor().commit();
                PREF_SESION= tools.getPref().getInt(getString(R.string.pref_SESION), 0);
                tools.Log_i("SESSION ACTUAL: "+PREF_SESION, TAG_CLASS);
                if(PREF_SESION == 0){

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                    finish();
                }

            }
        });




        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_settings);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        String moneda = repositoryMoneda.getByIdPais(PREF_ID_PAIS).get(0);
        View header = navigationView.getHeaderView(0);
        TextView textUsername = header.findViewById(R.id.txtNav_username);
        TextView textPerfil =header.findViewById(R.id.txtNav_perfil);
        TextView textIdPerfil = header.findViewById(R.id.txtNav_IdPerfil);
        TextView textPais = header.findViewById(R.id.txtNav_Pais);
        TextView textMoneda = header.findViewById(R.id.txtNav_Moneda);

//
//        String username = tools.getPref().getString(getString(R.string.pref_USERNAME), "N/A");
//        String perfil = tools.getPref().getString(getString(R.string.pref_USER_PERFIL), "N/A");
//
        textUsername.setText(PREF_USERNAME.toUpperCase());
        textPerfil.setText(PREF_PERFIL);
        textIdPerfil.setText(""+PREF_ID_PERFIL);
        textPais.setText("Pais: "+PREF_PAIS);
        textMoneda.setText("Moneda: "+moneda);
    }

    private void animateFab(){
        if(isOpen){
            binding.appBarSettings.fabsettings.startAnimation(rotateForward);
            binding.appBarSettings.fabclose.startAnimation(fabClose);
            binding.appBarSettings.fabclose.setClickable(false);
            isOpen=false;
        }else{
            binding.appBarSettings.fabsettings.startAnimation(rotateBackward);
            binding.appBarSettings.fabclose.startAnimation(fabOpen);
            binding.appBarSettings.fabclose.setClickable(true);
            isOpen=true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_settings);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}