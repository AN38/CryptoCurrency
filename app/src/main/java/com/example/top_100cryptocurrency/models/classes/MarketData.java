package com.example.top_100cryptocurrency.models.classes;

import com.example.top_100cryptocurrency.models.classes.Ath;
import com.example.top_100cryptocurrency.models.classes.AthChangePercentage;

public class MarketData {

    private Ath ath;
    private AthChangePercentage athChangePercentage;
    private float marketCapChangePercentage;
    private long totalSupply;
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
