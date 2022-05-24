package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.PaisEntity;

import java.util.List;

public interface RepositoryPais {
    void insert(PaisEntity modelPaisEntity);
    List<PaisEntity> getAllPaisLista();
}
