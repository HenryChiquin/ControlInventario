package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.CompraDetalleEntity;

import java.util.List;

public interface RepositoryCompraDetalle {
    void insert(CompraDetalleEntity modelDetalleEntity);
    void deleteCompra();
    List<CompraDetalleEntity> getAllCompra();
}
