package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.ProveedorEntity;

import java.util.List;

@Dao
public interface ProveedorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProveedorEntity modelProveedorEntity);

    @Query("SELECT * FROM PROVEEDOR")
    List<ProveedorEntity> getAllProveedor();

}
