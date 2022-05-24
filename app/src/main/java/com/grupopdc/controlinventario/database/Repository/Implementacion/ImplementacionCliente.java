package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.ClienteDao;
import com.grupopdc.controlinventario.database.Entity.ClienteEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryCliente;

import java.util.List;

public class ImplementacionCliente implements RepositoryCliente {
    ClienteDao clienteDao;

    public ImplementacionCliente(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Override
    public void insert(ClienteEntity modelClienteEntity) {
        clienteDao.insert(modelClienteEntity);
    }

    @Override
    public List<ClienteEntity> getAllClienteLista() {
        return clienteDao.getAllClienteLista();
    }
}
