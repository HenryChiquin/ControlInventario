package com.grupopdc.controlinventario.Tools;

public class KeysRoutes {

    private static final String ngrokserver = "167f-190-148-252-216";

    //POST
    public static final String PATH_REGISTRO = "https://"+ngrokserver+".ngrok.io/api/Producto/AddProduct";
    //POST TRASLADO
    public static final String PATH_REGISTRO_TRASLADO = "https://"+ngrokserver+".ngrok.io/api/Traspaso/Transferencias";

    public static final String PATH_GET_ALL_DATA = "https://"+ngrokserver+".ngrok.io/";
    //PUT


}
