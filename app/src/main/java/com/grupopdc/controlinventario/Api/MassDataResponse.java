package com.grupopdc.controlinventario.Api;

import com.google.gson.annotations.SerializedName;
import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;
import com.grupopdc.controlinventario.database.Entity.ImpuestoEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;
import com.grupopdc.controlinventario.database.Entity.ProveedorEntity;

import java.util.ArrayList;

public class MassDataResponse {
    @SerializedName("data")
    private DataINVResponse dataINVResponse;
    public DataINVResponse getDataResponse() {
        return dataINVResponse;
    }
    public class DataINVResponse {
        @SerializedName("producto")
        private ArrayList<ProductoEntity> productoEntities;
        @SerializedName("categoria")
        private ArrayList<CategoriaEntity> categoriaEntities;
        @SerializedName("almacen")
        private ArrayList<AlmacenEntity> almacenEntities;
        @SerializedName("proveedor")
        private ArrayList<ProveedorEntity> proveedorEntities;
        @SerializedName("impuesto")
        private ArrayList<ImpuestoEntity> impuestoEntities;

        public ArrayList<ProductoEntity> getProductoEntities() {
            return productoEntities;
        }

        public ArrayList<CategoriaEntity> getCategoriaEntities() {
            return categoriaEntities;
        }

        public ArrayList<AlmacenEntity> getAlmacenEntities() {
            return almacenEntities;
        }

        public ArrayList<ProveedorEntity> getProveedorEntities() {
            return proveedorEntities;
        }

        public ArrayList<ImpuestoEntity> getImpuestoEntities() {
            return impuestoEntities;
        }
    }
}
