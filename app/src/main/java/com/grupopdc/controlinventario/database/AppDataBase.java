package com.grupopdc.controlinventario.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.grupopdc.controlinventario.database.Dao.AlmacenDao;
import com.grupopdc.controlinventario.database.Dao.CategoriaDao;
import com.grupopdc.controlinventario.database.Dao.ImpuestoDao;
import com.grupopdc.controlinventario.database.Dao.ProductoDao;
import com.grupopdc.controlinventario.database.Dao.ProveedorDao;
import com.grupopdc.controlinventario.database.Entity.AlmacenEntity;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntity;
import com.grupopdc.controlinventario.database.Entity.ImpuestoEntity;
import com.grupopdc.controlinventario.database.Entity.ProductoEntity;
import com.grupopdc.controlinventario.database.Entity.ProveedorEntity;

@Database(entities = {ProductoEntity.class, CategoriaEntity.class, AlmacenEntity.class, ImpuestoEntity.class, ProveedorEntity.class},version = 2)
public abstract  class AppDataBase extends RoomDatabase {
    public static AppDataBase INSTANCEDABASE;

    public abstract ProductoDao productoDao();
    public abstract CategoriaDao categoriaDao();
    public abstract AlmacenDao almacenDao();
    public abstract ImpuestoDao impuestoDao();
    public abstract ProveedorDao proveedorDao();


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
