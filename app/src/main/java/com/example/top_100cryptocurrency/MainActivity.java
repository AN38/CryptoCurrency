package com.example.top_100cryptocurrency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.top_100cryptocurrency.network.NetworkInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity11";

    private RecyclerView recyclerView;
    private AdapterCryptoCurrency adapterCryptoCurrency;
    private int errorText;

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
        Call<ArrayList<ModelCryptocurrency>> call = networkInstance.getCurrencyAPI().getCurrencyList(
                "usd",
                "market_cap_desc",
                100,
                1);

        call.enqueue(new Callback<ArrayList<ModelCryptocurrency>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelCryptocurrency>> call, Response<ArrayList<ModelCryptocurrency>> response) {
                Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
//                ArrayList<ModelCryptocurrency> cryptocurrencies = response.body();
                adapterCryptoCurrency.refresh(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ModelCryptocurrency>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}