package com.grupopdc.controlinventario.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.grupopdc.controlinventario.database.Dao.CategoriaDao;
import com.grupopdc.controlinventario.database.Entity.CategoriaEntiry;
import com.grupopdc.controlinventario.database.Entity.CompraEntiry;
import com.grupopdc.controlinventario.database.Entity.DestinoEntiry;

@Database(entities = {CategoriaEntiry.class,DestinoEntiry.class, CompraEntiry.class},version = 8)
public abstract  class AppDataBase extends RoomDatabase {
    public static AppDataBase INSTANCEDABASE;

    public abstract CategoriaDao categoriaDao();

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
