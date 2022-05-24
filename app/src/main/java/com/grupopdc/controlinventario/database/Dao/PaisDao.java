package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.PaisEntity;

import java.util.List;

@Dao
public interface PaisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PaisEntity modelPaisEntity);

    @Query("SELECT * FROM PAIS")
    List<PaisEntity> getAllPaisLista();
}
