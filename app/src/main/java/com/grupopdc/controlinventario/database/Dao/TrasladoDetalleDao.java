package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.TrasladoDetalleEntity;
import com.grupopdc.controlinventario.database.Entity.VentaDetalleEntity;

import java.util.List;

@Dao
public interface TrasladoDetalleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TrasladoDetalleEntity modelTrasladoDetalleEntity);

    @Query("DELETE FROM TRASLADO_DETALLE")
    void deleteTraslado();

    @Query("SELECT * FROM TRASLADO_DETALLE")
    List<TrasladoDetalleEntity> getAllTraslado();
}
