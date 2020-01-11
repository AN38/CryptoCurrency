package com.example.top_100cryptocurrency;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.top_100cryptocurrency.interfaces.OnClick;
import com.example.top_100cryptocurrency.models.CryptocurrencyListModel;

import java.util.ArrayList;

public class AdapterCryptoCurrency extends RecyclerView.Adapter<VHCryptocurrency> {

    ArrayList<CryptocurrencyListModel> modelCryptocurrencies = new ArrayList<>();

    private OnClick listener;

    public AdapterCryptoCurrency(OnClick listener){
        this.listener = listener;
    }

    public void refresh(ArrayList<CryptocurrencyListModel> modelCryptocurrencies){
        this.modelCryptocurrencies.clear();
        this.modelCryptocurrencies.addAll(modelCryptocurrencies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VHCryptocurrency onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_crypto,parent,false);
        return new VHCryptocurrency(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHCryptocurrency holder, int position) {
        holder.bind(modelCryptocurrencies.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return modelCryptocurrencies.size();
    }
}
