package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.PerfilDao;
import com.grupopdc.controlinventario.database.Entity.PerfilEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryPerfil;

import java.util.List;

public class ImplementacionPerfil implements RepositoryPerfil {

    PerfilDao perfilDao;

    public ImplementacionPerfil(PerfilDao perfilDao) {
        this.perfilDao = perfilDao;
    }

    @Override
    public void insert(PerfilEntity modelPerfilEntity) {
        perfilDao.insert(modelPerfilEntity);
    }

    @Override
    public List<PerfilEntity> getAllPerfilLista() {
        return perfilDao.getAllPerfilLista();
    }
}
