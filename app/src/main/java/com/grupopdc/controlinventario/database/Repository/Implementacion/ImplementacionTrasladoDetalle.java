package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.TrasladoDetalleDao;
import com.grupopdc.controlinventario.database.Entity.TrasladoDetalleEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryTrasladoDetalle;

import java.util.List;

public class ImplementacionTrasladoDetalle implements RepositoryTrasladoDetalle {
    TrasladoDetalleDao trasladoDetalleDao;

    public ImplementacionTrasladoDetalle(TrasladoDetalleDao trasladoDetalleDao) {
        this.trasladoDetalleDao = trasladoDetalleDao;
    }

    @Override
    public void insert(TrasladoDetalleEntity modelTrasladoDetalleEntity) {
        trasladoDetalleDao.insert(modelTrasladoDetalleEntity);
    }

    @Override
    public void deleteTraslado() {
        trasladoDetalleDao.deleteTraslado();
    }

    @Override
    public List<TrasladoDetalleEntity> getAllTraslado() {
        return trasladoDetalleDao.getAllTraslado();
    }
}
