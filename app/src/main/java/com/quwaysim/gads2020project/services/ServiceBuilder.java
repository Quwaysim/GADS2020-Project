package com.quwaysim.gads2020project.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    //BASE URL
    private static final String URL = "https://gadsapi.herokuapp.com/api/";

    //Creating OkHttp Logging
    private static HttpLoggingInterceptor sHttpLoggingInterceptor =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //Adding Interceptor to the OkHttpClient
    private static OkHttpClient.Builder okHttp = new OkHttpClient.Builder()
            .addInterceptor(sHttpLoggingInterceptor);

    //Retrofit Builder + GsonConverter + Client (mainly the interceptor)
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    //Building Retrofit
    private static Retrofit sRetrofit = builder.build();

    //Retrofit service creator

    public static <S> S buildService(Class<S> serviceType){
        return sRetrofit.create(serviceType);
    }

}
