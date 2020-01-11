package com.example.top_100cryptocurrency.models;

import com.example.top_100cryptocurrency.models.classes.CryptocurrencyLogo;
import com.example.top_100cryptocurrency.models.classes.MarketData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyDetailsModel {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("market_cap_rank")
    @Expose
    private int marketCapRank;

    @SerializedName("image")
    @Expose
    private CryptocurrencyLogo image;

    @SerializedName("market_data")
    @Expose
    private MarketData marketData;

    public String getName() {
        return name;
    }

    public int getMarketCapRank() {
        return marketCapRank;
    }

    public CryptocurrencyLogo getImage() {
        return image;
    }

    public MarketData getMarketData() {
        return marketData;
    }
}
