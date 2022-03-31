package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.CategoriaEntiry;

@Dao
public interface CategoriaDao {
    @Insert
    void insert(CategoriaEntiry modelCategoriaEntiry);
    @Query("SELECT * FROM categoria")
    CategoriaEntiry getAllCategoria();

}
