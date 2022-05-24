package com.grupopdc.controlinventario.database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MONEDA")
public class MonedaEntity {
    @PrimaryKey private int idMoneda;
    private String nombre;
    private String abreviatura;
    private String codigo;
    private int idPais;

    public MonedaEntity() {
    }

    public MonedaEntity(int idMoneda, String nombre, String abreviatura, String codigo, int idPais) {
        this.idMoneda = idMoneda;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.codigo = codigo;
        this.idPais = idPais;
    }

    public int getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(int idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }
}
