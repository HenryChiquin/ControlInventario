package com.grupopdc.controlinventario.database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ALMACEN")
public class AlmacenEntity {
    @PrimaryKey private int idAlmacen;
    private String nombre;
    private String ubicacion;

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
