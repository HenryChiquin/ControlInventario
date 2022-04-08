package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.grupopdc.controlinventario.database.Entity.ImpuestoEntity;

import java.util.List;

@Dao
public interface ImpuestoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ImpuestoEntity modelImpuestoEntity);

    @Query("SELECT * FROM IMPUESTO")
    List<ImpuestoEntity> getAllImpuesto();
}
