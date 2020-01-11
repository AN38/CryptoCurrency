package com.example.top_100cryptocurrency.models.classes;


public class CryptocurrencyLogo {

    private String imageThumb;
    private String imageSmall;
    private String imageLarge;

    public CryptocurrencyLogo (String imageThumb, String imageSmall, String imageLarge){
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
