package com.example.top_100cryptocurrency.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptocurrencyListModel {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("image")
    @Expose
    private String imageUrl;

    @SerializedName("symbol")
    @Expose
    private String symbol;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("market_cap")
    @Expose
    private long marketCap;

    @SerializedName("current_price")
    @Expose
    private float currentPrice;

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(int marketCap) {
        this.marketCap = marketCap;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "CryptocurrencyListModel{" +
                "imageUrl='" + imageUrl + '\'' +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", marketCap=" + marketCap +
                ", current_price=" + currentPrice +
                '}';
    }

}
