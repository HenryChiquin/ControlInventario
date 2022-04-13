package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.ProductoEntity;

import java.util.List;

public interface RepositoryProducto {
    void insert(ProductoEntity modelProductoEntity);
    List<ProductoEntity> getallProducto();
}
