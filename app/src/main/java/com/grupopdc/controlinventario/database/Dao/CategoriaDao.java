package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;

import java.util.List;


@Dao
public interface CategoriaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CategoriaEntity modelCategoriaEntiry);
    @Query("SELECT * FROM CATEGORIA")
    CategoriaEntity getAllCategoria();

    @Query("SELECT * FROM CATEGORIA")
    List<CategoriaEntity> getAllCategoriaLista();

}
