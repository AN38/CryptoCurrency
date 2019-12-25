package com.example.top_100cryptocurrency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.top_100cryptocurrency.network.NetworkInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterCryptoCurrency adapterCryptoCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapterCryptoCurrency = new AdapterCryptoCurrency();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapterCryptoCurrency);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getRequestedList();
    }

    private void getRequestedList() {
        NetworkInstance networkInstance = NetworkInstance.getInstance();
        Call<ModelCryptocurrency> call = networkInstance.getCurrencyAPI().getCurrencyList(
                "usd",
                "market_cap_desc",
                100,
                1);

        call.enqueue(new Callback<ModelCryptocurrency>() {
            @Override
            public void onResponse(Call<ModelCryptocurrency> call, Response<ModelCryptocurrency> response) {
                ModelCryptocurrency model = response.body();

            }

            @Override
            public void onFailure(Call<ModelCryptocurrency> call, Throwable t) {
                Log.i("ALENA", "failure");
            }
        });
    }
}