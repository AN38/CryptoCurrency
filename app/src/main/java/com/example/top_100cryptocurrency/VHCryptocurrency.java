package com.example.top_100cryptocurrency;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.top_100cryptocurrency.interfaces.OnClick;
import com.example.top_100cryptocurrency.models.CryptocurrencyListModel;

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

        currencyLogo = itemView.findViewById(R.id.currrency_logo_vh);
        symbol = itemView.findViewById(R.id.tv_symbol);
        name = itemView.findViewById(R.id.tv_name);
        marketCap = itemView.findViewById(R.id.tv_market_cap);
        price = itemView.findViewById(R.id.tv_price);
        templateCap = itemView.getContext().getString(R.string.market_cap);
        templatePrice = itemView.getContext().getString(R.string.price);
    }

    public void bind(final CryptocurrencyListModel cryptocurrencyListModel, final OnClick listener) {
        Glide.with(itemView).load(cryptocurrencyListModel.getImageUrl()).into(currencyLogo);

        String strMarketCap = String.format(templateCap, cryptocurrencyListModel.getMarketCap());
        String strPrice = String.format(templatePrice, cryptocurrencyListModel.getCurrentPrice());

        symbol.setText(cryptocurrencyListModel.getSymbol());
        name.setText(cryptocurrencyListModel.getName());
        marketCap.setText(strMarketCap);
        price.setText(strPrice);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(cryptocurrencyListModel);
            }
        });
    }
}
