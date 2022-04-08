package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.ProductoDao;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryProducto;

import java.util.List;

public class ImplementacionProducto implements RepositoryProducto {
    ProductoDao productoDao;

    public ImplementacionProducto(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    @Override
    public void insert(ProductoEntity modelProductoEntity) {
        productoDao.insert(modelProductoEntity);
    }

    @Override
    public List<ProductoEntity> getallProducto() {
        return productoDao.getallProducto();
    }
}
