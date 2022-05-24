package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.PerfilEntity;

import java.util.List;

@Dao
public interface PerfilDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PerfilEntity modelPerfilEntity);

    @Query("SELECT * FROM PERFIL")
    List<PerfilEntity> getAllPerfilLista();
}
