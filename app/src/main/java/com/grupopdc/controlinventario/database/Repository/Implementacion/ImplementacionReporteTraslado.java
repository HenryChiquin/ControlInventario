package com.grupopdc.controlinventario.database.Repository.Implementacion;

import com.grupopdc.controlinventario.database.Dao.ReporteTrasladoDao;
import com.grupopdc.controlinventario.database.Entity.ReporteTrasladoEntity;
import com.grupopdc.controlinventario.database.Repository.RepositoryReporteTraslado;

import java.util.List;

public class ImplementacionReporteTraslado implements RepositoryReporteTraslado {
    ReporteTrasladoDao reporteTrasladoDao;

    public ImplementacionReporteTraslado(ReporteTrasladoDao reporteTrasladoDao) {
        this.reporteTrasladoDao = reporteTrasladoDao;
    }

    @Override
    public void insert(ReporteTrasladoEntity modelReporteTrasladoEntity) {
        reporteTrasladoDao.insert(modelReporteTrasladoEntity);
    }

    @Override
    public List<ReporteTrasladoEntity> getRepoByFecha(String fecha) {
        return reporteTrasladoDao.getRepoByFecha(fecha);
    }
}
