package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.EmpresaDao;
import com.grupopdc.controlinventario.database.Entity.EmpresaEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryEmpresa;

import java.util.List;

public class ImplementacionEmpresa implements RepositoryEmpresa {
    EmpresaDao empresaDao;

    public ImplementacionEmpresa(EmpresaDao empresaDao) {
        this.empresaDao = empresaDao;
    }

    @Override
    public void insert(EmpresaEntity modelEmpresaEntity) {
        empresaDao.insert(modelEmpresaEntity);
    }

    @Override
    public List<EmpresaEntity> getAllEmpresaLista() {
        return empresaDao.getAllEmpresaLista();
    }
}
