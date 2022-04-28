package com.grupopdc.controlinventario.models.request;

public class TrasladoRequest {
    private int IdProducto;
    private int Cantidad;
    private int IdAlmacenOrigen;
    private int IdAlmacenDestino;


    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public int getIdAlmacenOrigen() {
        return IdAlmacenOrigen;
    }

    public void setIdAlmacenOrigen(int idAlmacenOrigen) {
        IdAlmacenOrigen = idAlmacenOrigen;
    }

    public int getIdAlmacenDestino() {
        return IdAlmacenDestino;
    }

    public void setIdAlmacenDestino(int idAlmacenDestino) {
        IdAlmacenDestino = idAlmacenDestino;
    }
}
