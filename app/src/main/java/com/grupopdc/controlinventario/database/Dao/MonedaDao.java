package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.MonedaEntity;

import java.util.List;

@Dao
public interface MonedaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MonedaEntity modelMonedaEntity);

    @Query("SELECT nombre FROM MONEDA WHERE idPais =:idPais")
    List<String> getByIdPais(int idPais);
}
