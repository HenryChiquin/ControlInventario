package com.grupopdc.controlinventario.database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Compra")
public class CompraEntiry {
    @PrimaryKey
    private int IdCompra;
    private String producto;
    private String destino;
    private int cantidad;
    private double costo;


    public int getIdCompra() { return IdCompra; }

    public void setIdCompra(int idCompra) { IdCompra = idCompra; }

    public String getProducto() { return producto; }

    public void setProducto(String producto) { this.producto = producto; }

    public String getDestino() { return destino; }

    public void setDestino(String destino) { this.destino = destino; }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getCosto() { return costo; }

    public void setCosto(double costo) { this.costo = costo; }
}
