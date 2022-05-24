package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.EmpresaEntity;

import java.util.List;

public interface RepositoryEmpresa {
    void insert(EmpresaEntity modelEmpresaEntity);
    List<EmpresaEntity> getAllEmpresaLista();
}
