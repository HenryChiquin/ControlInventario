package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.ReporteTrasladoEntity;

import java.util.List;

@Dao
public interface ReporteTrasladoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ReporteTrasladoEntity modelReporteTrasladoEntity);

    @Query("SELECT * FROM ReporteTraslado WHERE fecha =:fecha")
    List<ReporteTrasladoEntity> getRepoByFecha(String fecha);
}
