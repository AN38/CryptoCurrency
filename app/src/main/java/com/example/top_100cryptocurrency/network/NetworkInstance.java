package com.example.top_100cryptocurrency.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkInstance {

    private static NetworkInstance instance;
    private CurrencyAPI currencyAPI = null;
    private NetworkInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.coingecko.com/api/")
                .build();
        currencyAPI = retrofit.create(CurrencyAPI.class);
    }

    public CurrencyAPI getCurrencyAPI() {
        return currencyAPI;
    }

    public static NetworkInstance getInstance() {
        if (instance == null) {
            instance = new NetworkInstance();
        }
        return instance;
    }

}
