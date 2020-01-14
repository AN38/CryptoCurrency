package com.example.top_100cryptocurrency.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.top_100cryptocurrency.AdapterCryptoCurrency;
import com.example.top_100cryptocurrency.R;
import com.example.top_100cryptocurrency.interfaces.OnClick;
import com.example.top_100cryptocurrency.models.CryptocurrencyListModel;
import com.example.top_100cryptocurrency.network.NetworkInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CryptocurrencyListFragment extends Fragment {

    public static final String COIN_ID = "COIN_ID";

    private RecyclerView recyclerView;
    private AdapterCryptoCurrency adapterCryptoCurrency;
    private ProgressBar progressBar;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cryptocurrency_list, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        toolbar = view.findViewById(R.id.toolbar);
        progressBar = view.findViewById(R.id.progress_bar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OnClick listener = new OnClick() {
            @Override
            public void onClick(CryptocurrencyListModel cryptocurrencyListModel) {
                Bundle bundle = new Bundle();
                bundle.putString(COIN_ID, String.valueOf(cryptocurrencyListModel.getId()));

                NavController navController = Navigation.findNavController(getActivity(),R.id.main_nav_host_fragment);
                navController.navigate(R.id.action_cryptocurrencyListFragment_to_cryptocurrencyDetails, bundle);
                Toast.makeText(getContext(), "CLICK to " + cryptocurrencyListModel.getId(), Toast.LENGTH_SHORT).show();
            }
        };
        adapterCryptoCurrency = new AdapterCryptoCurrency(listener);
        recyclerView.setAdapter(adapterCryptoCurrency);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
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
                Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                adapterCryptoCurrency.refresh(response.body());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<CryptocurrencyListModel>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
