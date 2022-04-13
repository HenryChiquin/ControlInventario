package com.grupopdc.controlinventario.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.grupopdc.controlinventario.database.Entity.ProductoEntity;

import java.util.List;

@Dao
public interface ProductoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductoEntity modelProductoEntity);

    @Query("SELECT * FROM PRODUCTO")
    List<ProductoEntity> getallProducto();

}
