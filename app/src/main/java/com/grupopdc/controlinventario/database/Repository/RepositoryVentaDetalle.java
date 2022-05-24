package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.VentaDetalleEntity;

import java.util.List;

public interface RepositoryVentaDetalle {
    void insert(VentaDetalleEntity modelVentaDetalleEntity);
    void deltedVenta();
    List<VentaDetalleEntity> getAllVenta();
}
