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

    @Query("SELECT nombre FROM PRODUCTO")
    List<String> getAllMarcasTag();

    @Query("SELECT * FROM PRODUCTO")
    List<ProductoEntity> getAllCodigoSKUNames();


    @Query("SELECT idProducto FROM PRODUCTO WHERE nombre IN (:nombreProducto)")
    List<Integer> getCodigoProductoByProducto(String nombreProducto);

    @Query("SELECT costo FROM PRODUCTO WHERE idProducto IN (:idProducto)")
    float getCostoByIdProducto(int idProducto);


    @Query("SELECT DISTINCT nombre FROM PRODUCTO WHERE nombre = :marcaProducto")
    List<String> getCodigoMarcaByMarca(String marcaProducto);

}
