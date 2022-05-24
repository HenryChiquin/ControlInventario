package com.grupopdc.controlinventario.Api;

import com.google.gson.annotations.SerializedName;
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
        @SerializedName("cliente")
        private ArrayList<ClienteEntity> clienteEntities;
        @SerializedName("empresa")
        private ArrayList<EmpresaEntity> empresaEntities;
        @SerializedName("pais")
        private ArrayList<PaisEntity> paisEntities;
        @SerializedName("perfil")
        private ArrayList<PerfilEntity> perfilEntities;
        @SerializedName("reporteTraslado")
        private ArrayList<ReporteTrasladoEntity> reporteTrasladoEntities;


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

        public ArrayList<ClienteEntity> getClienteEntities() {
            return clienteEntities;
        }

        public ArrayList<EmpresaEntity> getEmpresaEntities() {
            return empresaEntities;
        }

        public ArrayList<PaisEntity> getPaisEntities() {
            return paisEntities;
        }

        public ArrayList<PerfilEntity> getPerfilEntities() {
            return perfilEntities;
        }

        public ArrayList<ReporteTrasladoEntity> getReporteTrasladoEntities() {
            return reporteTrasladoEntities;
        }
    }
}
