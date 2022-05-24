package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.VentaDetalleDao;
import com.grupopdc.controlinventario.database.Entity.VentaDetalleEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryVentaDetalle;

import java.util.List;

public class ImplementacionVentaDetalle implements RepositoryVentaDetalle {
    VentaDetalleDao ventaDetalleDao;

    public ImplementacionVentaDetalle(VentaDetalleDao ventaDetalleDao) {
        this.ventaDetalleDao = ventaDetalleDao;
    }

    @Override
    public void insert(VentaDetalleEntity modelVentaDetalleEntity) {
        ventaDetalleDao.insert(modelVentaDetalleEntity);
    }

    @Override
    public void deltedVenta() {
        ventaDetalleDao.deltedVenta();
    }

    @Override
    public List<VentaDetalleEntity> getAllVenta() {
        return ventaDetalleDao.getAllVenta();
    }


}
