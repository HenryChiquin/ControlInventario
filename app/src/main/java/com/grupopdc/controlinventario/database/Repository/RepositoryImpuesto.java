package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.ImpuestoEntity;

import java.util.List;

public interface RepositoryImpuesto {
    void insert(ImpuestoEntity modelImpuestoEntity);
    List<ImpuestoEntity> getAllImpuesto();
}
