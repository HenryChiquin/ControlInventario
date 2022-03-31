package com.grupopdc.controlinventario.database.Entity;

public class Producto {
    private String Nombre;
    private float Costo;
    private int Categoria;
    private int Cantidad;
    private String FechaVencimiento;
    private String Imagen;



    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public float getCosto() {
        return Costo;
    }

    public void setCosto(float costo) {
        Costo = costo;
    }

    public int getCategoria() {
        return Categoria;
    }

    public void setCategoria(int categoria) {
        Categoria = categoria;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public String getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        FechaVencimiento = fechaVencimiento;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }
}
