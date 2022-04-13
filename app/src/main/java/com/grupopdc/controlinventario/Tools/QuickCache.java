package com.grupopdc.controlinventario.Tools;

import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;
import com.grupopdc.controlinventario.database.Entity.ImpuestoEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;
import com.grupopdc.controlinventario.database.Entity.ProveedorEntity;

import java.util.ArrayList;

public class QuickCache {
    public static ArrayList<ProductoEntity> LIST_PRODUCTO = new ArrayList<>();
    public static ArrayList<CategoriaEntity> LIST_CATEGORIA = new ArrayList<>();
    public static ArrayList<AlmacenEntity> LIST_ALMACEN = new ArrayList<>();
    public static ArrayList<ProveedorEntity> LIST_PROVEEDOR = new ArrayList<>();
    public static ArrayList<ImpuestoEntity> LIST_IMPUESTOS = new ArrayList<>();
}
