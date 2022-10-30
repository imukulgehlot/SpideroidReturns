package com.example.spideroidreturns.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spideroidreturns.Model.HomeItemsModel;
import com.example.spideroidreturns.R;
import com.example.spideroidreturns.Utility.AdapterItemClick;

import java.util.ArrayList;

public class RecyclerHomeItemsAdapter extends RecyclerView.Adapter<RecyclerHomeItemsAdapter.ViewHolder> {
    Context context;
    ArrayList<HomeItemsModel> arrHomeItem;
    AdapterItemClick adapterItemClick;

    public RecyclerHomeItemsAdapter(Context context, ArrayList<HomeItemsModel> arrHomeItem, AdapterItemClick adapterItemClick) {
        this.context = context;
        this.arrHomeItem = arrHomeItem;
        this.adapterItemClick = adapterItemClick;
    }

    @NonNull
    @Override
    public RecyclerHomeItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.singlerow_home, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHomeItemsAdapter.ViewHolder holder, int position) {

        holder.textViewItemHeading.setText(arrHomeItem.get(position).getHomeItems());

        holder.cardViewHomeItem.setOnClickListener(v -> {
            adapterItemClick.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return arrHomeItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItemHeading;
        CardView cardViewHomeItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewItemHeading = itemView.findViewById(R.id.textViewItemHeading);
            cardViewHomeItem = itemView.findViewById(R.id.cardViewHomeItem);
        }
    }
}
