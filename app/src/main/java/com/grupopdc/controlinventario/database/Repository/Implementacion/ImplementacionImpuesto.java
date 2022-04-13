package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.ImpuestoDao;
import com.grupopdc.controlinventario.database.Entity.ImpuestoEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryImpuesto;

import java.util.List;

public class ImplementacionImpuesto implements RepositoryImpuesto {
    ImpuestoDao impuestoDao;

    public ImplementacionImpuesto(ImpuestoDao impuestoDao) {
        this.impuestoDao = impuestoDao;
    }

    @Override
    public void insert(ImpuestoEntity modelImpuestoEntity) {
        impuestoDao.insert(modelImpuestoEntity);
    }

    @Override
    public List<ImpuestoEntity> getAllImpuesto() {
        return impuestoDao.getAllImpuesto();
    }
}
