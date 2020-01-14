package com.example.top_100cryptocurrency.models;

import com.google.gson.annotations.SerializedName;

public class AthModel {

    @SerializedName("bmd")
    private float bmd; //название взято из api

    public AthModel(float bmd){
        this.bmd = bmd;
    }

    public float getBmd() {
        return bmd;
    }
}
