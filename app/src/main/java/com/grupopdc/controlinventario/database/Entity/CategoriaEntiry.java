package com.grupopdc.controlinventario.database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Categoria")
public class CategoriaEntiry {
    @PrimaryKey(autoGenerate = true) private int IdCategoria;
    private String Cate;
    private String Nombre;
    private double Costo;
    private int existencia;

    public CategoriaEntiry() { Nombre = "N/A"; }

    public String getCate() { return Cate; }

    public void setCate(String cate) { Cate = cate; }

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

    public double getCosto() { return Costo; }

    public void setCosto(double costo) { Costo = costo; }

    public int getExistencia() { return existencia; }

    public void setExistencia(int existencia) { this.existencia = existencia; }
}
