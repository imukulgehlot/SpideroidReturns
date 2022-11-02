package com.example.spideroidreturns.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spideroidreturns.Model.ProductsDBexModel;
import com.example.spideroidreturns.R;

import java.util.ArrayList;

public class RecyclerProductDBAdapter extends RecyclerView.Adapter<RecyclerProductDBAdapter.ViewHolder> {
    Context context;
    ArrayList<ProductsDBexModel> arrProductDetails = new ArrayList<>();

    public RecyclerProductDBAdapter(Context context, ArrayList<ProductsDBexModel> arrProductDetails) {
        this.context = context;
        this.arrProductDetails = arrProductDetails;
    }

    @Override
    public RecyclerProductDBAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.singlerow_db_entry, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerProductDBAdapter.ViewHolder holder, int position) {
        ProductsDBexModel model = arrProductDetails.get(position);

        holder.itemView.setOnLongClickListener(v ->

                true);

        holder.tvProductName.setText(model.getProduct_name());
        holder.tvReviewStars.setText(String.valueOf(model.getReview()));
        holder.tvFalsePrice.setText(String.valueOf(model.getFalse_price()));
        holder.tvPrice.setText(String.valueOf(model.getPrice()));
        holder.tvFeature1.setText(model.getFeature1());
        holder.tvFeature2.setText(model.getFeature2());

    }

    @Override
    public int getItemCount() {
        return arrProductDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName;
        TextView tvReviewStars;
        TextView tvFalsePrice;
        TextView tvPrice;
        TextView tvFeature1;
        TextView tvFeature2;
        ImageView imageViewProductImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvReviewStars = itemView.findViewById(R.id.tvReviewStars);
            tvFalsePrice = itemView.findViewById(R.id.tvFalsePrice);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvFeature1 = itemView.findViewById(R.id.tvFeature1);
            tvFeature2 = itemView.findViewById(R.id.tvFeature2);
            imageViewProductImage = itemView.findViewById(R.id.imageViewProductImage);
        }
    }
}
