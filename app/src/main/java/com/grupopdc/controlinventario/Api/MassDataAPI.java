package com.grupopdc.controlinventario.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MassDataAPI {
    @GET("api/MassData/GetAllMassData")
    Call<MassDataResponse> obtenerMassData();

}
