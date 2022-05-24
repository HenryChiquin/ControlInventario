package com.grupopdc.controlinventario.database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "TRASLADO_DETALLE")
public class TrasladoDetalleEntity {

    @PrimaryKey(autoGenerate = true) private int IdTraslado;
    private int IdProducto;
    private int Cantidad;
    private int IdAlmacenOrigen;
    private int IdAlmacenDestino;

    public int getIdTraslado() {
        return IdTraslado;
    }

    public void setIdTraslado(int idTraslado) {
        IdTraslado = idTraslado;
    }

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
