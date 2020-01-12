package com.example.top_100cryptocurrency.models.classes;

import com.google.gson.annotations.SerializedName;

public class AthChangePercentage {

    @SerializedName("aed")
    private float aed;

    public AthChangePercentage(float aed){
        this.aed = aed;
    }

    public float getAed() {
        return aed;
    }
}
