package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.CompraDetalleDao;
import com.grupopdc.controlinventario.database.Entity.CompraDetalleEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryCompraDetalle;

import java.util.List;

public class ImplementacionCompraDetalle implements RepositoryCompraDetalle {
    CompraDetalleDao compraDetalleDao;

    public ImplementacionCompraDetalle(CompraDetalleDao compraDetalleDao) {
        this.compraDetalleDao = compraDetalleDao;
    }

    @Override
    public void insert(CompraDetalleEntity modelDetalleEntity) {
        compraDetalleDao.insert(modelDetalleEntity);
    }

    @Override
    public void deleteCompra() {
        compraDetalleDao.deleteCompra();
    }

    @Override
    public List<CompraDetalleEntity> getAllCompra() {
        return compraDetalleDao.getAllCompra();
    }
}
