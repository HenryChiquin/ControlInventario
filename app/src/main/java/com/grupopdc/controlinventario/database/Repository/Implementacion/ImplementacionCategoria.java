package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.CategoriaDao;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntiry;
import com.grupopdc.controlinventario.database.Repository.RepositoryCategoria;

public class ImplementacionCategoria implements RepositoryCategoria {
    CategoriaDao categoriaDao;

    public ImplementacionCategoria(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    @Override
    public void insert(CategoriaEntiry modelCategoriaEntiry) {
        categoriaDao.insert(modelCategoriaEntiry);
    }

    @Override
    public CategoriaEntiry getAllCategoria() {
        return categoriaDao.getAllCategoria();
    }
}
