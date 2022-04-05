package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.CategoriaDao;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntiry;
import com.grupopdc.controlinventario.database.Entity.CompraEntiry;
import com.grupopdc.controlinventario.database.Entity.DestinoEntiry;
import com.grupopdc.controlinventario.database.Repository.RepositoryCategoria;

import java.util.List;

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
    public List<CategoriaEntiry> getAll() {
        return categoriaDao.getAll();
    }

    @Override
    public void insertdestino(DestinoEntiry modelDestinoEntiry) {
        categoriaDao.insertdestino(modelDestinoEntiry);
    }

    @Override
    public List<DestinoEntiry> getAllDestino() {
        return categoriaDao.getAllDestino();
    }

    @Override
    public void insertcompra(CompraEntiry modelCompraEntiry) {
        categoriaDao.insertcompra(modelCompraEntiry);
    }
}
