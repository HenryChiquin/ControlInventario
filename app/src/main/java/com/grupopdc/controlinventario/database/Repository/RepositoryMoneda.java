package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.MonedaEntity;

import java.util.List;

public interface RepositoryMoneda {
    void insert(MonedaEntity modelMonedaEntity);
    List<String> getByIdPais(int idPais);
}
