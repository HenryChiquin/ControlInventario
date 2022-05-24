package com.grupopdc.controlinventario.database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "COMPRA_DETALLE")
public class CompraDetalleEntity {
    @PrimaryKey(autoGenerate = true) private int IdCompra;
    private int Cantidad;
    private float Precio;
    private int IdAlmacen;
    private int IdProducto;

    public int getIdCompra() {
        return IdCompra;
    }

    public void setIdCompra(int idCompra) {
        IdCompra = idCompra;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }

    public int getIdAlmacen() {
        return IdAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        IdAlmacen = idAlmacen;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }
}
