package com.grupopdc.controlinventario.Api;

import static com.grupopdc.controlinventario.Tools.KeysRoutes.PATH_GET_ALL_DATA;

import com.google.gson.GsonBuilder;
import com.grupopdc.controlinventario.Tools.DateDeserializer;
import com.grupopdc.controlinventario.Tools.Tools;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGeneratorGetAllData {
    public static Tools tools;
    private static final String API_BASE_URL = PATH_GET_ALL_DATA;


    private static GsonBuilder gsonBuilder = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateDeserializer());


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor loggingInterceptor =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder okHttpClient =
            new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60,TimeUnit.SECONDS);


    public static <T> T createService(Class<T> serviceClass) {
        if (!okHttpClient.interceptors().contains(loggingInterceptor)) {
            okHttpClient.addInterceptor(loggingInterceptor);
            okHttpClient.connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT));
            builder.client(okHttpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }

}
