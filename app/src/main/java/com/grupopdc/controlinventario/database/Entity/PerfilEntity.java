package com.grupopdc.controlinventario.database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "PERFIL")
public class PerfilEntity {
    @SerializedName("idPerfil")
    @PrimaryKey private int IdPerfil;
    @SerializedName("nombre")
    private String Nombre;

    public PerfilEntity() {
    }

    public PerfilEntity(int idPerfil, String nombre) {
        IdPerfil = idPerfil;
        Nombre = nombre;
    }

    public int getIdPerfil() {
        return IdPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        IdPerfil = idPerfil;
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
