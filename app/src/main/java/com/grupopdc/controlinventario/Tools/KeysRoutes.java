package com.grupopdc.controlinventario.Tools;

public class KeysRoutes {

    private static final String ngrokserver = "aa90-190-148-252-95";

    //POST
    public static final String PATH_REGISTRO = "https://"+ngrokserver+".ngrok.io/api/Producto/AddProduct";
    public static final String PATH_REGISTRO_USER = "https://"+ngrokserver+".ngrok.io/api/usuario/AddEmpresa";
    //POST TRASLADO
    public static final String PATH_REGISTRO_TRASLADO = "https://"+ngrokserver+".ngrok.io/api/Traspaso/Transferencias";
    public static final String PATH_REPORTE_TRASLADO = "https://"+ngrokserver+".ngrok.io/api/Traspaso/ReporteTraspaso";
    public static final String PATH_REGISTRO_VENTA = "https://"+ngrokserver+".ngrok.io/api/Venta/Ventas";
    public static final String PATH_REGISTRO_COMPRA = "https://"+ngrokserver+".ngrok.io/api/Compra/Compras";

    public static final String PATH_INVENTORYCONTROL = "https://"+ngrokserver+".ngrok.io/";
    //PUT


}
