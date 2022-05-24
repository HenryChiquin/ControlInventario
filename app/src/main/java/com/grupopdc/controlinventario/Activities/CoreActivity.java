package com.grupopdc.controlinventario.Activities;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.grupopdc.controlinventario.R;
import com.grupopdc.controlinventario.Tools.QuickCache;
import com.grupopdc.controlinventario.Tools.Tools;
import com.grupopdc.controlinventario.database.AppDataBase;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionAlmacen;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionCategoria;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionCliente;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionCompraDetalle;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionEmpresa;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionImpuesto;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionMoneda;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionPais;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionPerfil;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionProducto;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionProveedor;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionReporteTraslado;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionTrasladoDetalle;
import com.grupopdc.controlinventario.database.Repository.Implementacion.ImplementacionVentaDetalle;
import com.grupopdc.controlinventario.database.Repository.RepositoryAlmacen;
import com.grupopdc.controlinventario.database.Repository.RepositoryCategoria;
import com.grupopdc.controlinventario.database.Repository.RepositoryCliente;
import com.grupopdc.controlinventario.database.Repository.RepositoryCompraDetalle;
import com.grupopdc.controlinventario.database.Repository.RepositoryEmpresa;
import com.grupopdc.controlinventario.database.Repository.RepositoryImpuesto;
import com.grupopdc.controlinventario.database.Repository.RepositoryMoneda;
import com.grupopdc.controlinventario.database.Repository.RepositoryPais;
import com.grupopdc.controlinventario.database.Repository.RepositoryPerfil;
import com.grupopdc.controlinventario.database.Repository.RepositoryProducto;
import com.grupopdc.controlinventario.database.Repository.RepositoryProveedor;
import com.grupopdc.controlinventario.database.Repository.RepositoryReporteTraslado;
import com.grupopdc.controlinventario.database.Repository.RepositoryTrasladoDetalle;
import com.grupopdc.controlinventario.database.Repository.RepositoryVentaDetalle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class CoreActivity extends AppCompatActivity {
    private static String TAG_CLASS = "CORE ACTIVITY";
    public Tools tools;
    public static QuickCache qkcache;
    //DB ROOM
    public static AppDataBase appDataBase;

    public static RepositoryProducto repositoryProducto;
    public static RepositoryCategoria repositoryCategoria;
    public static RepositoryAlmacen repositoryAlmacen;
    public static RepositoryProveedor repositoryProveedor;
    public static RepositoryImpuesto repositoryImpuesto;
    public static RepositoryMoneda repositoryMoneda;
    public static RepositoryCliente repositoryCliente;
    public static RepositoryEmpresa repositoryEmpresa;
    public static RepositoryPais repositoryPais;
    public static RepositoryPerfil repositoryPerfil;
    public static RepositoryVentaDetalle repositoryVentaDetalle;
    public static RepositoryTrasladoDetalle repositoryTrasladoDetalle;
    public static RepositoryCompraDetalle repositoryCompraDetalle;
    public static RepositoryReporteTraslado repositoryReporteTraslado;

    public static int PREF_SESION=0;
    public static int PREF_IDUSER = 0;
    public static String PREF_USERNAME = "";
    public static String PREF_ACCESS_TOKEN = "";
    public static String PREF_EMPRESA= "";
    public static String PREF_PERFIL= "";
    public static int PREF_ID_PERFIL=0;
    public static String PREF_PAIS= "";
    public static int PREF_ID_PAIS= 0;


    public static boolean PREF_MODE_OFFLINE = false;//default
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
        repositoryMoneda = new ImplementacionMoneda(appDataBase.monedaDao());
        repositoryCliente = new ImplementacionCliente(appDataBase.clienteDao());
        repositoryEmpresa = new ImplementacionEmpresa(appDataBase.empresaDao());
        repositoryPais = new ImplementacionPais(appDataBase.paisDao());
        repositoryPerfil = new ImplementacionPerfil(appDataBase.perfilDao());
        repositoryVentaDetalle = new ImplementacionVentaDetalle(appDataBase.ventaDetalleDao());
        repositoryTrasladoDetalle = new ImplementacionTrasladoDetalle(appDataBase.trasladoDetalleDao());
        repositoryCompraDetalle = new ImplementacionCompraDetalle(appDataBase.compraDetalleDao());
        repositoryReporteTraslado = new ImplementacionReporteTraslado(appDataBase.reporteTrasladoDao());

        PREF_MODE_OFFLINE = tools.getPref().getBoolean(getString(R.string.pref_MODE_OFFLINE), false);

        PREF_SESION = tools.getPref().getInt(getString(R.string.pref_SESION), 0);
        PREF_IDUSER = tools.getPref().getInt(getString(R.string.pref_IDUSER), 0);
        PREF_USERNAME = tools.getPref().getString(getString(R.string.pref_USERNAME), "N/A");
        PREF_ACCESS_TOKEN = tools.getPref().getString(getString(R.string.pref_ACCESS_TOKEN), "N/A");
        PREF_EMPRESA = tools.getPref().getString(getString(R.string.pref_USER_EMPRESA), "N/A");
        PREF_PAIS = tools.getPref().getString(getString(R.string.pref_USER_PAIS), "N/A");
        PREF_ID_PAIS = tools.getPref().getInt(getString(R.string.pref_USER_ID_PAIS), 0);
        PREF_PERFIL = tools.getPref().getString(getString(R.string.pref_USER_PERFIL), "N/A");
        PREF_ID_PERFIL = tools.getPref().getInt(getString(R.string.pref_USER_ID_PERFIL), 0);

        tools.Log_i("iduser: "+PREF_IDUSER, TAG_CLASS);
        tools.Log_i("username: "+PREF_USERNAME, TAG_CLASS);
        tools.Log_i("token: "+PREF_ACCESS_TOKEN, TAG_CLASS);
        tools.Log_i("empresa: "+PREF_EMPRESA, TAG_CLASS);
        tools.Log_i("id perfil: "+PREF_PAIS, TAG_CLASS);
        tools.Log_i("perfil: "+PREF_ID_PAIS, TAG_CLASS);
        tools.Log_i("idpais: "+PREF_PERFIL, TAG_CLASS);
        tools.Log_i("pais: "+PREF_ID_PERFIL, TAG_CLASS);

    }
}