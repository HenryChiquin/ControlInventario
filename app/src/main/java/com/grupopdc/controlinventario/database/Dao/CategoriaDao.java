package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.grupopdc.controlinventario.database.Entity.CategoriaEntiry;
import com.grupopdc.controlinventario.database.Entity.CompraEntiry;
import com.grupopdc.controlinventario.database.Entity.DestinoEntiry;

import org.intellij.lang.annotations.JdkConstants;

import java.util.List;

@Dao
public interface CategoriaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CategoriaEntiry modelCategoriaEntiry);

    @Query("SELECT *FROM Categoria ORDER BY IdCategoria DESC")
    List<CategoriaEntiry> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertdestino(DestinoEntiry modelDestinoEntiry);

    @Query("SELECT *FROM Destino ORDER BY IdDestino DESC")
    List<DestinoEntiry> getAllDestino();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertcompra(CompraEntiry modelCompraEntiry);
}
