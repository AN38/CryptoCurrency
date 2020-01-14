package com.example.top_100cryptocurrency.models;

import com.google.gson.annotations.SerializedName;

public class AthChangePercentageModel {

    @SerializedName("aed")
    private float aed;

    public AthChangePercentageModel(float aed){
        this.aed = aed;
    }

    public float getAed() {
        return aed;
    }
}
