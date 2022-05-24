package com.grupopdc.controlinventario.models.request;

import java.util.ArrayList;

public class VentaGeneralRequest {
    private int IdCliente;
    private int IdUsuario;
    private int IdImpuesto;
    private String Reckey;
    private ArrayList<VentaDetalleRequest> detalle;

    public VentaGeneralRequest(int idCliente, int idUsuario, int idImpuesto, String reckey, ArrayList<VentaDetalleRequest> detalle) {
        IdCliente = idCliente;
        IdUsuario = idUsuario;
        IdImpuesto = idImpuesto;
        Reckey = reckey;
        this.detalle = detalle;
    }

}
