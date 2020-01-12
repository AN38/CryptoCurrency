package com.example.top_100cryptocurrency.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.top_100cryptocurrency.AdapterCryptoCurrency;
import com.example.top_100cryptocurrency.interfaces.OnClick;
import com.example.top_100cryptocurrency.models.CryptocurrencyListModel;
import com.example.top_100cryptocurrency.R;
import com.example.top_100cryptocurrency.network.NetworkInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterCryptoCurrency adapterCryptoCurrency;
    public static final String COIN_ID = "COIN_ID";
    private ProgressBar progressBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClick listener = new OnClick() {
            @Override
            public void onClick(CryptocurrencyListModel cryptocurrencyListModel) {
                Intent intent = new Intent(MainActivity.this, ShowCryptocurrencyDetails.class);
                intent.putExtra(COIN_ID, cryptocurrencyListModel.getId());
                startActivity(intent);

            }
        };
        adapterCryptoCurrency = new AdapterCryptoCurrency(listener);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapterCryptoCurrency);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.progress_bar);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(R.string.app_name);
        getRequestedList();
    }

    private void getRequestedList() {
        NetworkInstance networkInstance = NetworkInstance.getInstance();
        Call<ArrayList<CryptocurrencyListModel>> call = networkInstance.getCurrencyAPI().getCurrencyList(
                "usd",
                "market_cap_desc",
                100,
                1);

        call.enqueue(new Callback<ArrayList<CryptocurrencyListModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CryptocurrencyListModel>> call, Response<ArrayList<CryptocurrencyListModel>> response) {
                Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                adapterCryptoCurrency.refresh(response.body());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<CryptocurrencyListModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}