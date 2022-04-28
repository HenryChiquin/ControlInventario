package com.grupopdc.controlinventario.models.request;

import java.util.ArrayList;

public class TrasladoGeneralRequest {
    private String Reckey;
    private int CantidadProducto;
    private ArrayList<TrasladoRequest> detalle;

    public TrasladoGeneralRequest() {
    }

    public TrasladoGeneralRequest(String reckey, int cantidadProducto, ArrayList<TrasladoRequest> detalle) {
        Reckey = reckey;
        CantidadProducto = cantidadProducto;
        this.detalle = detalle;
    }
}
