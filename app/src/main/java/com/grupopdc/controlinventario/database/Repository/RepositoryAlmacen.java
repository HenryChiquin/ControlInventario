package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;

import java.util.List;

public interface RepositoryAlmacen {
    void insert(AlmacenEntity modelAlmacenEntity);
    List<AlmacenEntity> getAllAlmacen();
    List<AlmacenEntity> getAllAlmacenDestino();
}
