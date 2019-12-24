package com.example.top_100cryptocurrency;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCryptoCurrency extends RecyclerView.Adapter<VHCryptocurrency> {

    ArrayList<ModelCryptocurrency> modelCryptocurrencies = new ArrayList<>();

    public AdapterCryptoCurrency(){

    }

    public void refresh(ArrayList<ModelCryptocurrency> modelCryptocurrencies){
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
        holder.bind(modelCryptocurrencies.get(position));
    }

    @Override
    public int getItemCount() {
        return modelCryptocurrencies.size();
    }
}
