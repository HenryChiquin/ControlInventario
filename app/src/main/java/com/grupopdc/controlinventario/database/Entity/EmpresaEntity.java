package com.grupopdc.controlinventario.database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "EMPRESA")
public class EmpresaEntity {
    @SerializedName("idEmpresa")
    @PrimaryKey private int IdEmpresa;
    @SerializedName("nombre")
    private String Nombre;
    @SerializedName("dominio")
    private String Dominio;

    public EmpresaEntity() {
    }

    public EmpresaEntity(int idEmpresa, String nombre) {
        IdEmpresa = idEmpresa;
        Nombre = nombre;
    }

    public int getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        IdEmpresa = idEmpresa;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDominio() {
        return Dominio;
    }

    public void setDominio(String dominio) {
        Dominio = dominio;
    }

    @Override
    public String toString() {
        return Nombre;
    }
}
