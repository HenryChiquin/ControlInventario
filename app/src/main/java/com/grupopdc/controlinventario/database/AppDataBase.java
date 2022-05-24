package com.grupopdc.controlinventario.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.grupopdc.controlinventario.database.Dao.AlmacenDao;
import com.grupopdc.controlinventario.database.Dao.CategoriaDao;
import com.grupopdc.controlinventario.database.Dao.ClienteDao;
import com.grupopdc.controlinventario.database.Dao.CompraDetalleDao;
import com.grupopdc.controlinventario.database.Dao.EmpresaDao;
import com.grupopdc.controlinventario.database.Dao.ImpuestoDao;
import com.grupopdc.controlinventario.database.Dao.MonedaDao;
import com.grupopdc.controlinventario.database.Dao.PaisDao;
import com.grupopdc.controlinventario.database.Dao.PerfilDao;
import com.grupopdc.controlinventario.database.Dao.ProductoDao;
import com.grupopdc.controlinventario.database.Dao.ProveedorDao;
import com.grupopdc.controlinventario.database.Dao.ReporteTrasladoDao;
import com.grupopdc.controlinventario.database.Dao.TrasladoDetalleDao;
import com.grupopdc.controlinventario.database.Dao.VentaDetalleDao;
import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;
import com.grupopdc.controlinventario.database.Entity.ClienteEntity;
import com.grupopdc.controlinventario.database.Entity.CompraDetalleEntity;
import com.grupopdc.controlinventario.database.Entity.EmpresaEntity;
import com.grupopdc.controlinventario.database.Entity.ImpuestoEntity;
import com.grupopdc.controlinventario.database.Entity.MonedaEntity;
import com.grupopdc.controlinventario.database.Entity.PaisEntity;
import com.grupopdc.controlinventario.database.Entity.PerfilEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;
import com.grupopdc.controlinventario.database.Entity.ProveedorEntity;
import com.grupopdc.controlinventario.database.Entity.ReporteTrasladoEntity;
import com.grupopdc.controlinventario.database.Entity.TrasladoDetalleEntity;
import com.grupopdc.controlinventario.database.Entity.VentaDetalleEntity;

@Database(entities = {ProductoEntity.class, CategoriaEntity.class, AlmacenEntity.class, ImpuestoEntity.class, ProveedorEntity.class, MonedaEntity.class, ClienteEntity.class, EmpresaEntity.class, PaisEntity.class, PerfilEntity.class, VentaDetalleEntity.class, TrasladoDetalleEntity.class, CompraDetalleEntity.class, ReporteTrasladoEntity.class},version = 1)
public abstract  class AppDataBase extends RoomDatabase {
    public static AppDataBase INSTANCEDABASE;

    public abstract ProductoDao productoDao();
    public abstract CategoriaDao categoriaDao();
    public abstract AlmacenDao almacenDao();
    public abstract ImpuestoDao impuestoDao();
    public abstract ProveedorDao proveedorDao();
    public abstract MonedaDao monedaDao();
    public abstract ClienteDao clienteDao();
    public abstract EmpresaDao empresaDao();
    public abstract PaisDao paisDao();
    public abstract PerfilDao perfilDao();
    public abstract VentaDetalleDao ventaDetalleDao();
    public abstract TrasladoDetalleDao trasladoDetalleDao();
    public abstract CompraDetalleDao compraDetalleDao();
    public abstract ReporteTrasladoDao reporteTrasladoDao();


    public static AppDataBase getInstance(Context context){
        if(INSTANCEDABASE == null){

            INSTANCEDABASE = Room.databaseBuilder(context, AppDataBase.class,"BD_INTERNAL")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCEDABASE;
    }

}
