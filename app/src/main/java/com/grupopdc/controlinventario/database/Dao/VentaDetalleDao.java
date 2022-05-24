package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.VentaDetalleEntity;

import java.util.List;

@Dao
public interface VentaDetalleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(VentaDetalleEntity modelVentaDetalleEntity);

    @Query("DELETE FROM VENTA_DETALLE")
    void deltedVenta();

    @Query("SELECT * FROM VENTA_DETALLE")
    List<VentaDetalleEntity> getAllVenta();
}
