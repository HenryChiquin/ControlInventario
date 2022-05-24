package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.MonedaDao;
import com.grupopdc.controlinventario.database.Entity.MonedaEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryMoneda;

import java.util.List;

public class ImplementacionMoneda implements RepositoryMoneda {
    MonedaDao monedaDao;

    public ImplementacionMoneda(MonedaDao monedaDao) {
        this.monedaDao = monedaDao;
    }

    @Override
    public void insert(MonedaEntity modelMonedaEntity) {
        monedaDao.insert(modelMonedaEntity);
    }

    @Override
    public List<String> getByIdPais(int idPais) {
        return monedaDao.getByIdPais(idPais);
    }
}
