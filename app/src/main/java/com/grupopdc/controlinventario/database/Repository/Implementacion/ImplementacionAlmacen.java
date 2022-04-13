package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.AlmacenDao;
import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryAlmacen;

import java.util.List;

public class ImplementacionAlmacen implements RepositoryAlmacen {
    AlmacenDao almacenDao;

    public ImplementacionAlmacen(AlmacenDao almacenDao) {
        this.almacenDao = almacenDao;
    }

    @Override
    public void insert(AlmacenEntity modelAlmacenEntity) {
        almacenDao.insert(modelAlmacenEntity);
    }

    @Override
    public List<AlmacenEntity> getAllAlmacen() {
        return almacenDao.getAllAlmacen();
    }
}
