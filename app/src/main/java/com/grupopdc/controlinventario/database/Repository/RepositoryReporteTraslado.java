package com.grupopdc.controlinventario.database.Repository;

import com.grupopdc.controlinventario.database.Entity.ReporteTrasladoEntity;

import java.util.List;

public interface RepositoryReporteTraslado {
    void insert(ReporteTrasladoEntity modelReporteTrasladoEntity);
    List<ReporteTrasladoEntity> getRepoByFecha(String fecha);
}
