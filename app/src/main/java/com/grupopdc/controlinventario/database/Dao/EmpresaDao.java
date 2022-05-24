package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.EmpresaEntity;

import java.util.List;

@Dao
public interface EmpresaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EmpresaEntity modelEmpresaEntity);

    @Query("SELECT * FROM EMPRESA")
    List<EmpresaEntity> getAllEmpresaLista();
}
