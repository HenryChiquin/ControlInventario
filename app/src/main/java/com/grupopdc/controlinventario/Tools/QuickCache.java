package com.grupopdc.controlinventario.Tools;

import com.grupopdc.controlinventario.Activities.TrasladoActivity;
import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;
import com.grupopdc.controlinventario.database.Entity.ClienteEntity;
import com.grupopdc.controlinventario.database.Entity.EmpresaEntity;
import com.grupopdc.controlinventario.database.Entity.ImpuestoEntity;
import com.grupopdc.controlinventario.database.Entity.MonedaEntity;
import com.grupopdc.controlinventario.database.Entity.PaisEntity;
import com.grupopdc.controlinventario.database.Entity.PerfilEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;
import com.grupopdc.controlinventario.database.Entity.ProveedorEntity;
import com.grupopdc.controlinventario.database.Entity.ReporteTrasladoEntity;
import com.grupopdc.controlinventario.models.request.CompraDetalleRequest;
import com.grupopdc.controlinventario.models.request.TrasladoRequest;
import com.grupopdc.controlinventario.models.request.VentaDetalleRequest;
import com.grupopdc.controlinventario.models.request.VentaGeneralRequest;

import java.util.ArrayList;

public class QuickCache {
    public static ArrayList<ProductoEntity> LIST_PRODUCTO = new ArrayList<>();
    public static ArrayList<CategoriaEntity> LIST_CATEGORIA = new ArrayList<>();
    public static ArrayList<AlmacenEntity> LIST_ALMACEN = new ArrayList<>();
    public static ArrayList<ProveedorEntity> LIST_PROVEEDOR = new ArrayList<>();
    public static ArrayList<ImpuestoEntity> LIST_IMPUESTOS = new ArrayList<>();

    public static ArrayList<TrasladoRequest> LIST_TRASLADO = new ArrayList<>();
    public static ArrayList<CompraDetalleRequest> LIST_COMPRA = new ArrayList<>();
    public static ArrayList<VentaDetalleRequest> LIST_VENTA_DETAIL = new ArrayList<>();

    public static ArrayList<ClienteEntity> LIST_CLIENTE = new ArrayList<>();
    public static ArrayList<EmpresaEntity> LIST_EMPRESA = new ArrayList<>();
    public static ArrayList<PaisEntity> LIST_PAIS = new ArrayList<>();
    public static ArrayList<PerfilEntity> LIST_PERFIL = new ArrayList<>();

    public static ArrayList<ReporteTrasladoEntity> LIST_REPO_TRASLADO = new ArrayList<>();
}
