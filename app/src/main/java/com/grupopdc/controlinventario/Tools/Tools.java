package com.grupopdc.controlinventario.Tools;

import com.grupopdc.controlinventario.CoreActivity;

public class Tools {
    private Networking networking;
    public Tools(CoreActivity ctx) {
        networking = new Networking(ctx);
    }
    ////////////////////[NETWORK]///////////////////////////////////////////////////////////////////
    public Networking usedNetworking(){
        return networking;
    }
}
