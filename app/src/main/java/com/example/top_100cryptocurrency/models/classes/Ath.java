package com.example.top_100cryptocurrency.models.classes;

import com.google.gson.annotations.SerializedName;

public class Ath {

    @SerializedName("bmd")
    private float bmd; //название взято из api

    public Ath(float bmd){
        this.bmd = bmd;
    }

    public float getBmd() {
        return bmd;
    }
}
