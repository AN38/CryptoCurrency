package com.example.top_100cryptocurrency.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.top_100cryptocurrency.R;
import com.example.top_100cryptocurrency.interfaces.OnClick;
import com.example.top_100cryptocurrency.models.CryptocurrencyListModel;
import com.example.top_100cryptocurrency.models.CurrencyDetailsModel;
import com.example.top_100cryptocurrency.models.classes.Ath;
import com.example.top_100cryptocurrency.models.classes.AthChangePercentage;
import com.example.top_100cryptocurrency.models.classes.CryptocurrencyLogo;
import com.example.top_100cryptocurrency.models.classes.MarketData;
import com.example.top_100cryptocurrency.network.NetworkInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.top_100cryptocurrency.activities.MainActivity.COIN_ID;

public class ShowCryptocurrencyDetails extends AppCompatActivity {

    private ImageView arrowBack;
    private ImageView currencyLogo;
    private TextView title;
    private TextView marketCapRankValue;
    private TextView changeValue;
    private TextView athValue;
    private TextView athChangeValue;
    private TextView circulatingSupplyValue;
    private TextView totalSupplyValue;
    private ProgressBar progressBar;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cryptocurrency_details);
        arrowBack = findViewById(R.id.ic_arrow_back);
        currencyLogo = findViewById(R.id.currency_logo_detail);
        title = findViewById(R.id.tv_currency_name);
        marketCapRankValue = findViewById(R.id.tv_market_cap_rank_value);
        changeValue = findViewById(R.id.tv_change_value);
        athValue = findViewById(R.id.tv_ath_value);
        athChangeValue = findViewById(R.id.tv_ath_change_value);
        circulatingSupplyValue = findViewById(R.id.tv_circulating_supply_value);
        totalSupplyValue = findViewById(R.id.tv_total_supply_value);
        progressBar = findViewById(R.id.progress_bar);
        id = getIntent().getExtras().getString(COIN_ID);
        Toast.makeText(ShowCryptocurrencyDetails.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowCryptocurrencyDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });
        getCoinById();
    }
    private void getCoinById() {
        NetworkInstance networkInstance = NetworkInstance.getInstance();
        Call<CurrencyDetailsModel> call = networkInstance.getCurrencyAPI().getCurrencyDetails(id);

        call.enqueue(new Callback<CurrencyDetailsModel>() {
            @Override
            public void onResponse(Call<CurrencyDetailsModel> call, Response<CurrencyDetailsModel> response) {
                progressBar.setVisibility(View.GONE);
                CurrencyDetailsModel currencyDetailsModel = response.body();
                title.setText(currencyDetailsModel.getName());
                marketCapRankValue.setText(String.valueOf(currencyDetailsModel.getMarketCapRank()));
                MarketData marketData = currencyDetailsModel.getMarketData();
                Ath ath = marketData.getAth();
                athValue.setText(String.valueOf(ath.getBmd()));
                AthChangePercentage athChangePercentage = marketData.getAthChangePercentage();
                athChangeValue.setText(String.valueOf(athChangePercentage.getAed()));
                changeValue.setText(String.valueOf(marketData.getMarketCapChangePercentage()));
                totalSupplyValue.setText(String.valueOf(marketData.getTotalSupply()));
                circulatingSupplyValue.setText(String.valueOf(marketData.getCirculatingSupply()));
                CryptocurrencyLogo cryptocurrencyLogo = currencyDetailsModel.getImage();
                Glide.with(ShowCryptocurrencyDetails.this).load(cryptocurrencyLogo.getImageLarge()).into(currencyLogo);
            }

            @Override
            public void onFailure(Call<CurrencyDetailsModel> call, Throwable t) {
                Log.e("myDetails",t.getLocalizedMessage());
                Toast.makeText(ShowCryptocurrencyDetails.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
