package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.ClienteEntity;

import java.util.List;

@Dao
public interface ClienteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ClienteEntity modelClienteEntity);

    @Query("SELECT * FROM CLIENTE")
    List<ClienteEntity> getAllClienteLista();
}
