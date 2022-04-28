package com.grupopdc.controlinventario.database.Dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;

import java.util.List;

@Dao
public interface AlmacenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AlmacenEntity modelAlmacenEntity);

    @Query("SELECT * FROM ALMACEN")
    List<AlmacenEntity> getAllAlmacen();

    @Query("SELECT * FROM ALMACEN ORDER BY idAlmacen DESC")
    List<AlmacenEntity> getAllAlmacenDestino();
}
