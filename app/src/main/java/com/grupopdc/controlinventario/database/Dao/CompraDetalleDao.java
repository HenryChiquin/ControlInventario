package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.CompraDetalleEntity;
import com.grupopdc.controlinventario.database.Entity.TrasladoDetalleEntity;

import java.util.List;

@Dao
public interface CompraDetalleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CompraDetalleEntity modelDetalleEntity);

    @Query("DELETE FROM COMPRA_DETALLE")
    void deleteCompra();

    @Query("SELECT * FROM COMPRA_DETALLE")
    List<CompraDetalleEntity> getAllCompra();
}
