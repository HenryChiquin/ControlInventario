package com.grupopdc.controlinventario.database.Entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PRODUCTO")
public class ProductoEntity {
    @PrimaryKey private int idProducto;
    private String nombre;
    private float costo;
    private int idCategoria;
    private int cantidad;
    private String fechaRegistro;
    private int idEstado;

    public ProductoEntity() {
    }

    public ProductoEntity(String nombre, float costo, int idCategoria, int cantidad) {
        this.nombre = nombre;
        this.costo = costo;
        this.idCategoria = idCategoria;
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
}
