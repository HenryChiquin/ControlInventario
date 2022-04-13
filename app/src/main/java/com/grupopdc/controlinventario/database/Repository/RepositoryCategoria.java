package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;

import java.util.List;

public interface RepositoryCategoria {
    void insert(CategoriaEntity modelCategoriaEntity);
    CategoriaEntity getAllCategoria();
    List<CategoriaEntity> getAllCategoriaLista();
}
