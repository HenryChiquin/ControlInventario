package com.grupopdc.controlinventario.database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "PAIS")
public class PaisEntity {
    @SerializedName("idPais")
    @PrimaryKey private int IdPais;
    @SerializedName("nombre")
    private String Nombre;

    public PaisEntity() {
    }

    public PaisEntity(int idPais, String nombre) {
        IdPais = idPais;
        Nombre = nombre;
    }

    public int getIdPais() {
        return IdPais;
    }

    public void setIdPais(int idPais) {
        IdPais = idPais;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return Nombre;
    }
}
