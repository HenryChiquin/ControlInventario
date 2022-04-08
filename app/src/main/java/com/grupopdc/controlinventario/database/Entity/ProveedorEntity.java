package com.grupopdc.controlinventario.database.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PROVEEDOR")
public class ProveedorEntity {
    @PrimaryKey private int idProveedor;
    private String nombre;

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
