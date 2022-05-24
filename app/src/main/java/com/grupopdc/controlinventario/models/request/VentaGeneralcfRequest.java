package com.grupopdc.controlinventario.models.request;

import java.util.ArrayList;

public class VentaGeneralcfRequest {
    private int IdUsuario;
    private int IdImpuesto;
    private String Reckey;
    private ArrayList<VentaDetalleRequest> detalle;

    public VentaGeneralcfRequest(int idUsuario, int idImpuesto, String reckey, ArrayList<VentaDetalleRequest> detalle) {
        IdUsuario = idUsuario;
        IdImpuesto = idImpuesto;
        Reckey = reckey;
        this.detalle = detalle;
    }
}
