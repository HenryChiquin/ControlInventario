package com.grupopdc.controlinventario.database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Categoria")
public class CategoriaEntiry {
    @PrimaryKey private int IdCategoria;
    private String Nombre;

    public CategoriaEntiry() {
        Nombre = "N/A";
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        IdCategoria = idCategoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
