package com.example.top_100cryptocurrency.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.top_100cryptocurrency.R;
import com.example.top_100cryptocurrency.interfaces.OnClick;
import com.example.top_100cryptocurrency.models.CryptocurrencyListModel;
import com.example.top_100cryptocurrency.models.CurrencyDetailsModel;
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
    private TextView marketCapRank;
    private TextView marketCapRankValue;
    private TextView change;
    private TextView changeValue;
    private TextView ath;
    private TextView athValue;
    private TextView athChange;
    private TextView athChangeValue;
    private TextView circulatingSupply;
    private TextView circulatingSupplyValue;
    private TextView totalSupply;
    private TextView totalSupplyValue;
    private ProgressBar progressBar;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cryptocurrency_details);
        arrowBack = findViewById(R.id.ic_arrow_back);
        currencyLogo = findViewById(R.id.currrency_logo);
        title = findViewById(R.id.tv_currency_name);
        marketCapRank = findViewById(R.id.tv_market_cap_rank);
        marketCapRankValue = findViewById(R.id.tv_market_cap_rank_value);
        change = findViewById(R.id.tv_change);
        changeValue = findViewById(R.id.tv_change_value);
        ath = findViewById(R.id.tv_ath);
        athValue = findViewById(R.id.tv_ath_value);
        athChange = findViewById(R.id.tv_ath_change);
        athChangeValue = findViewById(R.id.tv_ath_change_value);
        circulatingSupply = findViewById(R.id.tv_circulating_supply);
        circulatingSupplyValue = findViewById(R.id.tv_circulating_supply_value);
        totalSupply = findViewById(R.id.tv_total_supply);
        totalSupplyValue = findViewById(R.id.tv_total_supply_value);
        progressBar = findViewById(R.id.progress_bar);
        getCoinById();
        id = getIntent().getExtras().getString(COIN_ID);
        Toast.makeText(ShowCryptocurrencyDetails.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowCryptocurrencyDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getCoinById() {
        NetworkInstance networkInstance = NetworkInstance.getInstance();
        Call<ArrayList<CurrencyDetailsModel>> call = networkInstance.getCurrencyAPI().getCurrencyDetails(id);

        call.enqueue(new Callback<ArrayList<CurrencyDetailsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CurrencyDetailsModel>> call, Response<ArrayList<CurrencyDetailsModel>> response) {
                response.body();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<CurrencyDetailsModel>> call, Throwable t) {
                Toast.makeText(ShowCryptocurrencyDetails.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
