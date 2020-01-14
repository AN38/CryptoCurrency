package com.example.top_100cryptocurrency.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.top_100cryptocurrency.AdapterCryptoCurrency;
import com.example.top_100cryptocurrency.R;
import com.example.top_100cryptocurrency.activities.MainActivity;
import com.example.top_100cryptocurrency.activities.ShowCryptocurrencyDetails;
import com.example.top_100cryptocurrency.interfaces.OnClick;
import com.example.top_100cryptocurrency.models.CryptocurrencyListModel;
import com.example.top_100cryptocurrency.network.NetworkInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptocurrencyListFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterCryptoCurrency adapterCryptoCurrency;
    public static final String COIN_ID = "COIN_ID";
    private ProgressBar progressBar;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cryptocurrency_list, container, false);
        OnClick onClick = new OnClick() {
            @Override
            public void onClick(CryptocurrencyListModel cryptocurrencyListModel) {
                Intent intent = new Intent();
                intent.putExtra(COIN_ID, cryptocurrencyListModel.getId());
                startActivity(intent);
                NavController navController = Navigation.findNavController(getActivity(), R.id.main_nav_host_fragment);
                navController.navigate(R.id.action_cryptocurrencyListFragment_to_cryptocurrencyDetails);
            }
        };
        return view;

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
//                Toast.makeText(CryptocurrencyListFragment.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                adapterCryptoCurrency.refresh(response.body());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<CryptocurrencyListModel>> call, Throwable t) {
//                Toast.makeText(CryptocurrencyListFragment.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
