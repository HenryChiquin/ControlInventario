package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.PerfilEntity;

import java.util.List;

public interface RepositoryPerfil {
    void insert(PerfilEntity modelPerfilEntity);
    List<PerfilEntity> getAllPerfilLista();
}
