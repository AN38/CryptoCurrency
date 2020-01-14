package com.example.top_100cryptocurrency.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.top_100cryptocurrency.R;
import com.example.top_100cryptocurrency.models.AthChangePercentageModel;
import com.example.top_100cryptocurrency.models.AthModel;
import com.example.top_100cryptocurrency.models.CryptocurrencyLogoModel;
import com.example.top_100cryptocurrency.models.CurrencyDetailsModel;
import com.example.top_100cryptocurrency.models.MarketDataModel;
import com.example.top_100cryptocurrency.network.NetworkInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.top_100cryptocurrency.fragments.CryptocurrencyListFragment.COIN_ID;

public class CryptocurrencyDetailsFragment extends Fragment {

    private Toolbar toolbar;
    private ImageView currencyLogo;
    private TextView marketCapRankValue;
    private TextView changeValue;
    private TextView athValue;
    private TextView athChangeValue;
    private TextView circulatingSupplyValue;
    private TextView totalSupplyValue;
    private ProgressBar progressBar;

    private String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cryptocurrency_details, container, false);
        toolbar = view.findViewById(R.id.toolbar_detail);
        id = getArguments().getString(COIN_ID);
        currencyLogo = view.findViewById(R.id.currency_logo_detail);
        marketCapRankValue = view.findViewById(R.id.tv_market_cap_rank_value);
        changeValue = view.findViewById(R.id.tv_change_value);
        athValue = view.findViewById(R.id.tv_ath_value);
        athChangeValue = view.findViewById(R.id.tv_ath_change_value);
        circulatingSupplyValue = view.findViewById(R.id.tv_circulating_supply_value);
        totalSupplyValue = view.findViewById(R.id.tv_total_supply_value);
        progressBar = view.findViewById(R.id.progress_bar);
        getCoinById();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

    }

    private void getCoinById() {
        NetworkInstance networkInstance = NetworkInstance.getInstance();
        Call<CurrencyDetailsModel> call = networkInstance.getCurrencyAPI().getCurrencyDetails(id);

        call.enqueue(new Callback<CurrencyDetailsModel>() {
            @Override
            public void onResponse(Call<CurrencyDetailsModel> call, Response<CurrencyDetailsModel> response) {
                progressBar.setVisibility(View.GONE);
                CurrencyDetailsModel currencyDetailsModel = response.body();
                toolbar.setTitle(currencyDetailsModel.getName());
                marketCapRankValue.setText(String.valueOf(currencyDetailsModel.getMarketCapRank()));
                MarketDataModel marketDataModel = currencyDetailsModel.getMarketDataModel();
                AthModel athModel = marketDataModel.getAthModel();
                athValue.setText(String.valueOf(athModel.getBmd()));
                AthChangePercentageModel athChangePercentageModel = marketDataModel.getAthChangePercentageModel();
                athChangeValue.setText(String.valueOf(athChangePercentageModel.getAed()));
                changeValue.setText(String.valueOf(marketDataModel.getMarketCapChangePercentage()));
                totalSupplyValue.setText(String.valueOf(marketDataModel.getTotalSupply()));
                circulatingSupplyValue.setText(String.valueOf(marketDataModel.getCirculatingSupply()));
                CryptocurrencyLogoModel cryptocurrencyLogoModel = currencyDetailsModel.getImage();
                Glide.with(getContext()).load(cryptocurrencyLogoModel.getImageLarge()).into(currencyLogo);
            }

            @Override
            public void onFailure(Call<CurrencyDetailsModel> call, Throwable t) {
                Log.e("myDetails",t.getLocalizedMessage());
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    @Override
//    public boolean onSupportNavigateUp() { onBackPressed();
//        return true;
//    }
}
