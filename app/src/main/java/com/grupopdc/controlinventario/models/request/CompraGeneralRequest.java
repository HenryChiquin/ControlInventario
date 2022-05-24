package com.grupopdc.controlinventario.models.request;

import java.util.ArrayList;

public class CompraGeneralRequest {
    private int IdProveedor;
    private int IdUsuario;
    private int IdImpuesto;
    private String Reckey;
    private ArrayList<CompraDetalleRequest> detalle;

    public CompraGeneralRequest(int idProveedor, int idUsuario, int idImpuesto, String reckey, ArrayList<CompraDetalleRequest> detalle) {
        IdProveedor = idProveedor;
        IdUsuario = idUsuario;
        IdImpuesto = idImpuesto;
        Reckey = reckey;
        this.detalle = detalle;
    }
}
