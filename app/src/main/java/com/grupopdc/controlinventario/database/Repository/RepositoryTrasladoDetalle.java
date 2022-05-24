package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.TrasladoDetalleEntity;

import java.util.List;

public interface RepositoryTrasladoDetalle {
    void insert(TrasladoDetalleEntity modelTrasladoDetalleEntity);
    void deleteTraslado();
    List<TrasladoDetalleEntity> getAllTraslado();
}
