package com.grupopdc.controlinventario.Activities;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.grupopdc.controlinventario.Tools.QuickCache;
import com.grupopdc.controlinventario.Tools.Tools;
import com.grupopdc.controlinventario.database.AppDataBase;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionAlmacen;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionCategoria;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionImpuesto;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionProducto;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionProveedor;
import com.grupopdc.controlinventario.database.Repository.RepositoryAlmacen;
import com.grupopdc.controlinventario.database.Repository.RepositoryCategoria;
import com.grupopdc.controlinventario.database.Repository.RepositoryImpuesto;
import com.grupopdc.controlinventario.database.Repository.RepositoryProducto;
import com.grupopdc.controlinventario.database.Repository.RepositoryProveedor;

import androidx.appcompat.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class CoreActivity extends AppCompatActivity {

    public Tools tools;
    public static QuickCache qkcache;
    //DB ROOM
    public static AppDataBase appDataBase;

    public static RepositoryProducto repositoryProducto;
    public static RepositoryCategoria repositoryCategoria;
    public static RepositoryAlmacen repositoryAlmacen;
    public static RepositoryProveedor repositoryProveedor;
    public static RepositoryImpuesto repositoryImpuesto;

    /////---[OVERRIDES]-----------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        qkcache = new QuickCache();
        tools = new Tools(this);

        InitPropiedadesDataBase();
    }
    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_MENU ) {
            // do nothing
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //INIT
    private void InitPropiedadesDataBase(){
        appDataBase = AppDataBase.getInstance(this);

        repositoryCategoria = new ImplementacionCategoria(appDataBase.categoriaDao());
        repositoryProducto = new ImplementacionProducto(appDataBase.productoDao());
        repositoryAlmacen = new ImplementacionAlmacen(appDataBase.almacenDao());
        repositoryProveedor = new ImplementacionProveedor(appDataBase.proveedorDao());
        repositoryImpuesto = new ImplementacionImpuesto(appDataBase.impuestoDao());
    }
}