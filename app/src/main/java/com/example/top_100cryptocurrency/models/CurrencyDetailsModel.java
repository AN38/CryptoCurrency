package com.example.top_100cryptocurrency.models;

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
    private CryptocurrencyLogoModel image;

    @SerializedName("market_data")
    @Expose
    private MarketDataModel marketDataModel;

    public String getName() {
        return name;
    }

    public int getMarketCapRank() {
        return marketCapRank;
    }

    public CryptocurrencyLogoModel getImage() {
        return image;
    }

    public MarketDataModel getMarketDataModel() {
        return marketDataModel;
    }
}
