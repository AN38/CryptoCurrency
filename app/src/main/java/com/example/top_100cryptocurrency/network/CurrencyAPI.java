package com.example.top_100cryptocurrency.network;

import com.example.top_100cryptocurrency.ModelCryptocurrency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyAPI {

    //@Query("sparkline") boolean sparkline) wtf???

    @GET("v3/coins/markets?sparkline=false")
    Call<ModelCryptocurrency> getCurrencyList(@Query("vs_currency") String chosenCurrency,
                                              @Query("order") String orderBy,
                                              @Query("per_page") int perPage,
                                              @Query("page") int page);

}
