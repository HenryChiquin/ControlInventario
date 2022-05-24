package com.grupopdc.controlinventario.ui.home;


import android.animation.Animator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.grupopdc.controlinventario.Activities.CatalogoActivity;
import com.grupopdc.controlinventario.Activities.ConsultaActivity;
import com.grupopdc.controlinventario.Activities.CoreActivity;
import com.grupopdc.controlinventario.Activities.IngresoProducto;
import com.grupopdc.controlinventario.Activities.LoginActivity;
import com.grupopdc.controlinventario.Activities.MantenimientoProducto;
import com.grupopdc.controlinventario.Activities.OperacionesActivity;
import com.grupopdc.controlinventario.Api.MassDataAPI;
import com.grupopdc.controlinventario.Api.MassDataResponse;
import com.grupopdc.controlinventario.Api.ServiceGeneratorGetAllData;
import com.grupopdc.controlinventario.R;
import com.grupopdc.controlinventario.Tools.QuickCache;
import com.grupopdc.controlinventario.Tools.Tools;
import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;
import com.grupopdc.controlinventario.database.Entity.ClienteEntity;
import com.grupopdc.controlinventario.database.Entity.EmpresaEntity;
import com.grupopdc.controlinventario.database.Entity.ImpuestoEntity;
import com.grupopdc.controlinventario.database.Entity.PaisEntity;
import com.grupopdc.controlinventario.database.Entity.PerfilEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;
import com.grupopdc.controlinventario.database.Entity.ProveedorEntity;
import com.grupopdc.controlinventario.database.Entity.ReporteTrasladoEntity;
import com.grupopdc.controlinventario.databinding.FragmentHomeBinding;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static String TAG_CLASS = "HOME FRAGMENTO";
    private CoreActivity coreActivity;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private Handler updateBarHandler;
    public Tools tools;
    public static QuickCache qkcache;

    private CardView cardViewRegistro,cardViewMantenimiento,cardViewConsulta, cardViewOperacion,cardViewCatalogo;
    private TextView textview_sync;
    private Button btnSincronizar;



    public HomeFragment() {
    }
    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        tools = new Tools(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initComponents(view);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

/*        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        return root;
    }


    private void initComponents(View view){


        updateBarHandler = new Handler();
        cardViewRegistro = view.findViewById(R.id.cardView_Registro);
        cardViewMantenimiento = view.findViewById(R.id.cardView_Mantenimiento);
        cardViewConsulta = view.findViewById(R.id.cardView_Consulta);
        cardViewOperacion = view.findViewById(R.id.cardView_Operaciones);
        cardViewCatalogo = view.findViewById(R.id.cardView_Catalogo);
        textview_sync = view.findViewById(R.id.textview_sync);
        btnSincronizar = view.findViewById(R.id.btnSincronizar);



        //nav_txtperfil = headerView.findViewById(R.id.nav_perfil);

        btnSincronizar.setOnClickListener(View -> Confirmation_SYNC());
        cardViewRegistro.setOnClickListener(View -> getRegistroProducto());
        cardViewMantenimiento.setOnClickListener(View ->MantenimientoProducto());
        cardViewCatalogo.setOnClickListener(View ->catalogoProducto());
        cardViewOperacion.setOnClickListener(View ->operacionProducto());
        cardViewConsulta.setOnClickListener(View ->consultaProducto());
        constructDataPool();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void constructDataPool(){
        //INDICADOR DE ULTIMA SINCRONIZACION
        String PREF_SYNC_DB_DATE = tools.getPref().getString(getString(R.string.pref_SYNC_DB_DATE), "@");




        if(PREF_SYNC_DB_DATE.equals("@"))
            textview_sync.setText(getString(R.string.psvtxt_pending_sync));
        else {

            textview_sync.setText(getString(R.string.psvtxt_last_sync) + " " + PREF_SYNC_DB_DATE);

        }
    }
    //-----DIALOGS---------
    private void Confirmation_SYNC(){

        Log.i("Confirmation_SYNC - Solicitud de sincronizacion...", TAG_CLASS);
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Log.i("Confirmation_SYNC - Solicitud aprobada por usuario", TAG_CLASS);
                        //RequestMassdata();
                        downloadGetAllMassData();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        Log.i("Confirmation_SYNC - Solicitud cancelada por usuario", TAG_CLASS);
                        //NO HACER NADA
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.dialog_sync));
        builder.setPositiveButton(getString(R.string.psvtxt_yes), dialogClickListener);
        builder.setNegativeButton(getString(R.string.psvtxt_no), dialogClickListener);
        builder.show();

    }

    private void downloadGetAllMassData(){
        if(tools.usedNetworking().isNetworkAvailable(getActivity())){
            ProgressDialog progress;
            progress = new ProgressDialog(getActivity());
            progress.setTitle("Procesando");
            progress.setMessage("Por favor espere ...");
            progress.setCancelable(false);
            progress.show();
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    //tarea a realizar
                    try {
                        MassDataAPI massDataAPI = ServiceGeneratorGetAllData.createService(MassDataAPI.class);

                        final Call<MassDataResponse> requestMassDataINV = massDataAPI.obtenerMassData();
                        requestMassDataINV.enqueue(new Callback<MassDataResponse>() {
                            @Override
                            public void onResponse(Call<MassDataResponse> call, Response<MassDataResponse> response) {
                                if (response.isSuccessful()) {
                                    //tools.MakeToast("Autenticado correctamente");
                                    qkcache.LIST_PRODUCTO = response.body().getDataResponse().getProductoEntities();
                                    qkcache.LIST_CATEGORIA = response.body().getDataResponse().getCategoriaEntities();
                                    qkcache.LIST_ALMACEN = response.body().getDataResponse().getAlmacenEntities();
                                    qkcache.LIST_PROVEEDOR = response.body().getDataResponse().getProveedorEntities();
                                    qkcache.LIST_IMPUESTOS = response.body().getDataResponse().getImpuestoEntities();
                                    qkcache.LIST_CLIENTE = response.body().getDataResponse().getClienteEntities();
                                    qkcache.LIST_EMPRESA = response.body().getDataResponse().getEmpresaEntities();
                                    qkcache.LIST_PAIS = response.body().getDataResponse().getPaisEntities();
                                    qkcache.LIST_PERFIL = response.body().getDataResponse().getPerfilEntities();
                                    qkcache.LIST_REPO_TRASLADO = response.body().getDataResponse().getReporteTrasladoEntities();


                                    progress.dismiss();
                                    saveDataIntoDB();
                                }
                            }

                            @Override
                            public void onFailure(Call<MassDataResponse> call, Throwable t) {
                                progress.dismiss();
                                Log.e("ERROR onFailure retrofit ", TAG_CLASS);
                            }
                        });
                    } catch (Exception ex) {
                        Log.e("Error Fel exception: "+ex, TAG_CLASS);
                    }


                }
            });
            thread.start();
        }else{
            tools.MakeToast("No hay conexion a internet");
        }
    }

    private void saveDataIntoDB(){
        ProgressDialog progress;
        progress = new ProgressDialog(getActivity());
        progress.setTitle("Guardando datos");
        progress.setMessage("por favor esperar...");
        progress.setCancelable(false);
        progress.show();
        String progress_message = "";
        try {


            tools.Log_i("SIZE PRODUCTOS             : " + qkcache.LIST_PRODUCTO.size(), TAG_CLASS);
            tools.Log_i("SIZE CATEGORIAS            : " + qkcache.LIST_CATEGORIA.size(), TAG_CLASS);
            tools.Log_i("SIZE ALMACENES             : " + qkcache.LIST_ALMACEN.size(), TAG_CLASS);
            tools.Log_i("SIZE PROVEEDORES           : " + qkcache.LIST_PROVEEDOR.size(), TAG_CLASS);
            tools.Log_i("SIZE IMPUESTOS             : " + qkcache.LIST_IMPUESTOS.size(), TAG_CLASS);
            tools.Log_i("SIZE CLIENTE               : " + qkcache.LIST_CLIENTE.size(), TAG_CLASS);
            tools.Log_i("SIZE EMPRESA               : " + qkcache.LIST_EMPRESA.size(), TAG_CLASS);
            tools.Log_i("SIZE PAIS                  : " + qkcache.LIST_PAIS.size(), TAG_CLASS);
            tools.Log_i("SIZE PERFIL                : " + qkcache.LIST_PERFIL.size(), TAG_CLASS);
            tools.Log_i("SIZE REPO_TRASLADO         : " + qkcache.LIST_REPO_TRASLADO.size(), TAG_CLASS);


            progress_message +=
                    qkcache.LIST_PRODUCTO.size()+" PRODUCTOS\n"+
                            qkcache.LIST_CATEGORIA.size()+" CATEGORIA\n"+
                            qkcache.LIST_ALMACEN.size()+" ALMACEN\n"+
                            qkcache.LIST_PROVEEDOR.size()+" PROVEEDOR\n"+
                            qkcache.LIST_IMPUESTOS.size()+" IMPUESTO\n"+
                            qkcache.LIST_CLIENTE.size()+" CLIENTE\n"+
                            qkcache.LIST_EMPRESA.size()+" EMPRESA\n"+
                            qkcache.LIST_PAIS.size()+" PAIS\n"+
                            qkcache.LIST_PERFIL.size()+" PERFIL\n";


            progress.setMessage(progress_message);
        }catch (Exception d){
            tools.Log_e("Error created progress dialog. Razon: " + d, TAG_CLASS);
        }
        progress.show();
        progress.setMessage(progress_message);
        progress.show();
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){

                int regErrors = 0;
                //tarea a realizar
                //CtxMainFactmo.appDataBaseConfig.clearAllTables();
                try {

                    //////[PRODUCTO]///////  ROOM
                    try {
                        for (ProductoEntity productoEntity : qkcache.LIST_PRODUCTO)
                            coreActivity.repositoryProducto.insert(productoEntity);
                    } catch (Exception e) {
                        regErrors++;
                        tools.Log_e("Error saving sync date DB. [PRODUCTO] Razon: " + e, TAG_CLASS);
                    }
                    //////[CATEGORIA]///////  ROOM
                    try {
                        for (CategoriaEntity categoriaEntity : qkcache.LIST_CATEGORIA)
                            coreActivity.repositoryCategoria.insert(categoriaEntity);
                    } catch (Exception e) {
                        regErrors++;
                        tools.Log_e("Error saving sync date DB. [CATEGORIA] Razon: " + e, TAG_CLASS);
                    }
                    //////[ALMACEN]///////  ROOM
                    try {
                        for (AlmacenEntity almacenEntity : qkcache.LIST_ALMACEN)
                            coreActivity.repositoryAlmacen.insert(almacenEntity);
                    } catch (Exception e) {
                        regErrors++;
                        tools.Log_e("Error saving sync date DB. [ALMACEN] Razon: " + e, TAG_CLASS);
                    }
                    //////[PROVEEDOR]///////  ROOM
                    try {
                        for (ProveedorEntity proveedorEntity : qkcache.LIST_PROVEEDOR)
                            coreActivity.repositoryProveedor.insert(proveedorEntity);
                    } catch (Exception e) {
                        regErrors++;
                        tools.Log_e("Error saving sync date DB. [PROVEEDOR] Razon: " + e, TAG_CLASS);
                    }
                    //////[IMPUESTO]/////// ROOM
                    try {
                        for (ImpuestoEntity impuestoEntity : qkcache.LIST_IMPUESTOS)
                            coreActivity.repositoryImpuesto.insert(impuestoEntity);
                    } catch (Exception e) {
                        regErrors++;
                        tools.Log_e("Error saving sync date DB. [IMPUESTO] Razon: " + e, TAG_CLASS);
                    }
                    //////[CLIENTE]///////  ROOM
                    try {
                        for (ClienteEntity clienteEntity : qkcache.LIST_CLIENTE)
                            coreActivity.repositoryCliente.insert(clienteEntity);
                    } catch (Exception e) {
                        regErrors++;
                        tools.Log_e("Error saving sync date DB. [CLIENTE] Razon: " + e, TAG_CLASS);
                    }
                    //////[EMPRESA]///////  ROOM
                    try {
                        for (EmpresaEntity empresaEntity : qkcache.LIST_EMPRESA)
                            coreActivity.repositoryEmpresa.insert(empresaEntity);
                    } catch (Exception e) {
                        regErrors++;
                        tools.Log_e("Error saving sync date DB. [EMPRESA] Razon: " + e, TAG_CLASS);
                    }
                    //////[PAIS]///////  ROOM
                    try {
                        for (PaisEntity paisEntity : qkcache.LIST_PAIS)
                            coreActivity.repositoryPais.insert(paisEntity);
                    } catch (Exception e) {
                        regErrors++;
                        tools.Log_e("Error saving sync date DB. [PAIS] Razon: " + e, TAG_CLASS);
                    }
                    //////[PERFIL]///////  ROOM
                    try {
                        for (PerfilEntity perfilEntity : qkcache.LIST_PERFIL)
                            coreActivity.repositoryPerfil.insert(perfilEntity);
                    } catch (Exception e) {
                        regErrors++;
                        tools.Log_e("Error saving sync date DB. [PERFIL] Razon: " + e, TAG_CLASS);
                    }

                    //////[REPORTE TRASLADO]///////  ROOM
                    try {
                        for (ReporteTrasladoEntity reporteTrasladoEntity : qkcache.LIST_REPO_TRASLADO){
                            ReporteTrasladoEntity this_item = new ReporteTrasladoEntity();

                            this_item.setIdTraspaso(reporteTrasladoEntity.getIdTraspaso());
                            this_item.setFecha(tools.EncodeDatevalue(tools.RailsFormatCleanDate(reporteTrasladoEntity.getFecha())));
                            this_item.setProducto(reporteTrasladoEntity.getProducto());
                            this_item.setCantidad(reporteTrasladoEntity.getCantidad());
                            this_item.setAlmacenOrigen(reporteTrasladoEntity.getAlmacenOrigen());
                            this_item.setAlmacenDestino(reporteTrasladoEntity.getAlmacenOrigen());

                            coreActivity.repositoryReporteTraslado.insert(this_item);
                        }

                    } catch (Exception e) {
                        regErrors++;
                        tools.Log_e("Error saving sync date DB. [REPORTE TRASLADO] Razon: " + e, TAG_CLASS);
                    }
                    tools.Log_i("Complete create new DB data. Errors register: "+regErrors, TAG_CLASS);

                } catch (Exception ex) {
                    tools.Log_e("Session exception: "+ex, TAG_CLASS);
                    progress.dismiss();
                }
                progress.dismiss();
                //Confirmation_massdata_error(regErrors);
                updateBarHandler.post(runnableUiDB);
            }
        });
        thread.start();
    }
    Runnable runnableUiDB = new  Runnable(){
        @Override
        public void run() {
            // Escriba aquí la operación de actualización de la interfaz de usuario
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            tools.getEditor().putString(getString(R.string.pref_SYNC_DB_DATE), dateFormat.format(date).toString());
            tools.getEditor().commit();
            String PREF_SYNC_DB_DATE = tools.getPref().getString(getString(R.string.pref_SYNC_DB_DATE), "@");
            textview_sync.setText(getString(R.string.psvtxt_last_sync) + " " + PREF_SYNC_DB_DATE);
            tools.MakeToast("Guardado Completado");
            //SavePref_data();


        }
    };
    ///-------events---------------------------

    public void getRegistroProducto(){
        Intent intent = new Intent(getActivity(), IngresoProducto.class);
        startActivity(intent);
    }

    public void MantenimientoProducto(){
        Intent intent = new Intent(getActivity(), MantenimientoProducto.class);
        startActivity(intent);
    }
    public void catalogoProducto(){
        Intent intent = new Intent(getActivity(), CatalogoActivity.class);
        startActivity(intent);
    }

    public void operacionProducto(){
        Intent intent = new Intent(getActivity(), OperacionesActivity.class);
        startActivity(intent);
    }

    public void consultaProducto(){
        Intent intent = new Intent(getActivity(), ConsultaActivity.class);
        startActivity(intent);
    }
    public void evt_sync(){
        Log.i("Event Launch Sync", TAG_CLASS);
        Confirmation_SYNC();
    }
}