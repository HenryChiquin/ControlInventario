package com.grupopdc.controlinventario.database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CATEGORIA")
public class CategoriaEntity {
    @PrimaryKey private int idCategoria;
    private String nombre;

    public CategoriaEntity(int idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public CategoriaEntity() {
        nombre = "N/A";
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
