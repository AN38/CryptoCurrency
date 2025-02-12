package com.example.top_100cryptocurrency.network;

import com.example.top_100cryptocurrency.models.CryptocurrencyListModel;
import com.example.top_100cryptocurrency.models.CurrencyDetailsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CurrencyAPI {

    //@Query("sparkline") boolean sparkline) это график

    @GET("v3/coins/markets?sparkline=false")
    Call<ArrayList<CryptocurrencyListModel>> getCurrencyList(@Query("vs_currency") String chosenCurrency,
                                                             @Query("order") String orderBy,
                                                             @Query("per_page") int perPage,
                                                             @Query("page") int page);

    @GET("v3/coins/{id}")
    Call<CurrencyDetailsModel> getCurrencyDetails(@Path("id") String id);
}
