package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.ClienteEntity;

import java.util.List;

public interface RepositoryCliente {
    void insert(ClienteEntity modelClienteEntity);
    List<ClienteEntity> getAllClienteLista();
}
