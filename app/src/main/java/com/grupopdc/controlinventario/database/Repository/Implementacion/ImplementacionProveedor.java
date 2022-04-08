package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.ProveedorDao;
import com.grupopdc.controlinventario.database.Entity.ProveedorEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryProveedor;

import java.util.List;

public class ImplementacionProveedor implements RepositoryProveedor {

    ProveedorDao proveedorDao;

    public ImplementacionProveedor(ProveedorDao proveedorDao) {
        this.proveedorDao = proveedorDao;
    }

    @Override
    public void insert(ProveedorEntity modelProveedorEntity) {
        proveedorDao.insert(modelProveedorEntity);
    }

    @Override
    public List<ProveedorEntity> getAllProveedor() {
        return proveedorDao.getAllProveedor();
    }
}
