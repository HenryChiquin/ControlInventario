package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.PaisDao;
import com.grupopdc.controlinventario.database.Entity.PaisEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryPais;

import java.util.List;

public class ImplementacionPais implements RepositoryPais {
    PaisDao paisDao;

    public ImplementacionPais(PaisDao paisDao) {
        this.paisDao = paisDao;
    }

    @Override
    public void insert(PaisEntity modelPaisEntity) {
        paisDao.insert(modelPaisEntity);
    }

    @Override
    public List<PaisEntity> getAllPaisLista() {
        return paisDao.getAllPaisLista();
    }
}
