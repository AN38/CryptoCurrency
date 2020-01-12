package com.example.top_100cryptocurrency.models.classes;

import com.google.gson.annotations.SerializedName;

public class MarketData {

    @SerializedName("ath")
    private Ath ath;

    @SerializedName("ath_change_percentage")
    private AthChangePercentage athChangePercentage;

    @SerializedName("market_cap_change_percentage_24h")
    private float marketCapChangePercentage;

    @SerializedName("total_supply")
    private long totalSupply;

    @SerializedName("circulating_supply")
    private long circulatingSupply;

    public MarketData(Ath ath, AthChangePercentage athChangePercentage, float marketCapChangePercentage, long totalSupply, long circulatingSupply) {
        this.ath = ath;
        this.athChangePercentage = athChangePercentage;
        this.marketCapChangePercentage = marketCapChangePercentage;
        this.totalSupply = totalSupply;
        this.circulatingSupply = circulatingSupply;
    }

    public Ath getAth() {
        return ath;
    }

    public AthChangePercentage getAthChangePercentage() {
        return athChangePercentage;
    }

    public float getMarketCapChangePercentage() {
        return marketCapChangePercentage;
    }

    public long getTotalSupply() {
        return totalSupply;
    }

    public long getCirculatingSupply() {
        return circulatingSupply;
    }
}
