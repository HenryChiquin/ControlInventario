package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.CategoriaEntiry;
import com.grupopdc.controlinventario.database.Entity.CompraEntiry;
import com.grupopdc.controlinventario.database.Entity.DestinoEntiry;

import java.util.List;

public interface RepositoryCategoria {
    void insert(CategoriaEntiry modelCategoriaEntiry);

    List<CategoriaEntiry> getAll();

    void insertdestino(DestinoEntiry modelDestinoEntiry);

    List<DestinoEntiry> getAllDestino();

    void insertcompra(CompraEntiry modelCompraEntiry);
}
