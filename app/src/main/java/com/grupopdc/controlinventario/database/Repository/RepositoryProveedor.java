package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.ProveedorEntity;

import java.util.List;

public interface RepositoryProveedor {
    void insert(ProveedorEntity modelProveedorEntity);
    List<ProveedorEntity> getAllProveedor();
}
