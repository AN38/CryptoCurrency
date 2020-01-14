package com.example.top_100cryptocurrency.models;

import com.example.top_100cryptocurrency.models.AthChangePercentageModel;
import com.example.top_100cryptocurrency.models.AthModel;
import com.google.gson.annotations.SerializedName;

public class MarketDataModel {

    @SerializedName("ath")
    private AthModel athModel;

    @SerializedName("ath_change_percentage")
    private AthChangePercentageModel athChangePercentageModel;

    @SerializedName("market_cap_change_percentage_24h")
    private float marketCapChangePercentage;

    @SerializedName("total_supply")
    private float totalSupply;

    @SerializedName("circulating_supply")
    private float circulatingSupply;

    public MarketDataModel(AthModel athModel, AthChangePercentageModel athChangePercentageModel, float marketCapChangePercentage, long totalSupply, long circulatingSupply) {
        this.athModel = athModel;
        this.athChangePercentageModel = athChangePercentageModel;
        this.marketCapChangePercentage = marketCapChangePercentage;
        this.totalSupply = totalSupply;
        this.circulatingSupply = circulatingSupply;
    }

    public AthModel getAthModel() {
        return athModel;
    }

    public AthChangePercentageModel getAthChangePercentageModel() {
        return athChangePercentageModel;
    }

    public float getMarketCapChangePercentage() {
        return marketCapChangePercentage;
    }

    public float getTotalSupply() {
        return totalSupply;
    }

    public float getCirculatingSupply() {
        return circulatingSupply;
    }
}
