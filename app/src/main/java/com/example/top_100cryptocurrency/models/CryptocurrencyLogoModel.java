package com.example.top_100cryptocurrency.models;


import com.google.gson.annotations.SerializedName;

public class CryptocurrencyLogoModel {


    @SerializedName("thumb")
    private String imageThumb;

    @SerializedName("small")
    private String imageSmall;

    @SerializedName("large")
    private String imageLarge;

    public CryptocurrencyLogoModel(String imageThumb, String imageSmall, String imageLarge){
        this.imageLarge = imageLarge;
        this.imageThumb = imageThumb;
        this.imageSmall = imageSmall;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public String getImageSmall() {
        return imageSmall;
    }
}
