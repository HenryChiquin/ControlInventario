package com.grupopdc.controlinventario.models.request;

import java.util.ArrayList;

public class VentaDetalleRequest {
    private int Cantidad;
    private float Precio;
    private int IdAlmacen;
    private int IdProducto;

    public VentaDetalleRequest() {
    }

    public VentaDetalleRequest(int cantidad, float precio, int idAlmacen, int idProducto) {
        Cantidad = cantidad;
        Precio = precio;
        IdAlmacen = idAlmacen;
        IdProducto = idProducto;
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
