package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.CategoriaDao;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryCategoria;

import java.util.List;

public class ImplementacionCategoria implements RepositoryCategoria {
    CategoriaDao categoriaDao;

    public ImplementacionCategoria(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    @Override
    public void insert(CategoriaEntity modelCategoriaEntity) {
        categoriaDao.insert(modelCategoriaEntity);
    }

    @Override
    public CategoriaEntity getAllCategoria() {
        return categoriaDao.getAllCategoria();
    }

    @Override
    public List<CategoriaEntity> getAllCategoriaLista() {
        return categoriaDao.getAllCategoriaLista();
    }

}
