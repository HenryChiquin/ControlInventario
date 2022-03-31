package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.CategoriaEntiry;

public interface RepositoryCategoria {
    void insert(CategoriaEntiry modelCategoriaEntiry);
    CategoriaEntiry getAllCategoria();
}
