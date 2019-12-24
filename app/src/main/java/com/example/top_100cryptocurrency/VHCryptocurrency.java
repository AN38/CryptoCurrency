package com.example.top_100cryptocurrency;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class VHCryptocurrency extends RecyclerView.ViewHolder {

    private ImageView currencyLogo;
    private TextView symbol;
    private TextView name;
    private TextView marketCap;
    private TextView price;

    private String templateCap;
    private String templatePrice;

    public VHCryptocurrency(@NonNull View itemView) {
        super(itemView);

        currencyLogo = itemView.findViewById(R.id.currrency_logo);
        symbol = itemView.findViewById(R.id.tv_symbol);
        name = itemView.findViewById(R.id.tv_name);
        marketCap = itemView.findViewById(R.id.tv_market_cap);
        price = itemView.findViewById(R.id.tv_price);
    }

    public void bind(ModelCryptocurrency modelCryptocurrency) {
        Glide.with(itemView).load(modelCryptocurrency.getImageUrl()).into(currencyLogo);

        String strMarketCap = String.format(templateCap,modelCryptocurrency.getMarketCap());
        String strPrice = String.format(templatePrice,modelCryptocurrency.getCurrent_price());

        symbol.setText(modelCryptocurrency.getSymbol());
        name.setText(modelCryptocurrency.getName());
        marketCap.setText(strMarketCap);
        price.setText(strPrice);
    }
}
