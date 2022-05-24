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

    @Override
    public List<String> getAllMarcasTag() {
        return productoDao.getAllMarcasTag();
    }

    @Override
    public List<ProductoEntity> getAllCodigoSKUNames() {
        return productoDao.getAllCodigoSKUNames();
    }

    @Override
    public List<Integer> getCodigoProductoByProducto(String nombreProducto) {
        return productoDao.getCodigoProductoByProducto(nombreProducto);
    }

    @Override
    public List<String> getCodigoMarcaByMarca(String marcaProducto) {
        return productoDao.getCodigoMarcaByMarca(marcaProducto);
    }

    @Override
    public float getCostoByIdProducto(int idProducto) {
        return productoDao.getCostoByIdProducto(idProducto);
    }


}
