package com.example.spideroidreturns.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spideroidreturns.Model.ProductsDBexModel;
import com.example.spideroidreturns.R;
import com.example.spideroidreturns.Utility.MyDBHelper;

import java.util.ArrayList;

public class RecyclerProductDBAdapter extends RecyclerView.Adapter<RecyclerProductDBAdapter.ViewHolder> {
    Context context;
    ArrayList<ProductsDBexModel> arrProductDetails;

    public RecyclerProductDBAdapter(Context context, ArrayList<ProductsDBexModel> arrProductDetails) {
        this.context = context;
        this.arrProductDetails = arrProductDetails;

        for (int i = 0; i < arrProductDetails.size(); i++) {
            Log.d("RecyclerProductDBAdaper", "onBhindiViewHolder: \n1st Pro Fav:" + arrProductDetails.get(i).getIsFavorite());
        }
    }

    @Override
    public RecyclerProductDBAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.singlerow_db_entry, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerProductDBAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductsDBexModel model = arrProductDetails.get(position);

        if (model.getIsFavorite() == 0) {
            holder.imgFavorite.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorPrimary));
        } else {
            holder.imgFavorite.setBackgroundTintList(context.getResources().getColorStateList(R.color.heart_red));
        }

        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setIcon(R.drawable.ic_baseline_delete_outline_24)
                    .setTitle("Delete this product?")
                    .setMessage("Are you sure waana delete this damned product?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        MyDBHelper db = new MyDBHelper(context);
                        db.DeleteProduct(position + 1);
                        arrProductDetails = db.fetchProducts();
                        notifyDataSetChanged();

                    })
                    .setNegativeButton("Nopes", (dialog, which) -> {

                    })
                    .show();
            return true;
        });

        holder.cardFavorite.setOnClickListener(v -> {
            int pos = position + 1;
            MyDBHelper db = new MyDBHelper(context);
            if (arrProductDetails.get(position).getIsFavorite() == 0) {
                db.updateProductFavorite(pos, 1);
                holder.imgFavorite.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.heart_red));
            } else if (arrProductDetails.get(position).getIsFavorite() == 1) {
                db.updateProductFavorite(pos, 0);
                holder.imgFavorite.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.colorPrimary));
            }
            arrProductDetails = db.fetchProducts();
            notifyDataSetChanged();
        });

        holder.tvProductName.setText(model.getProduct_name());
        holder.tvReviewStars.setText(String.valueOf(model.getReview()));
        holder.tvFalsePrice.setText("₹ " + model.getFalse_price());
        holder.tvPrice.setText("₹ " + model.getPrice());
        holder.tvFeature1.setText(model.getFeature1());
        holder.tvFeature2.setText(model.getFeature2());
        holder.imageViewProductImage.setImageBitmap(arrProductDetails.get(position).getBitmapProductImage());

        holder.tvFalsePrice.setPaintFlags(holder.tvFalsePrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
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
        CardView cardFavorite;
        ImageView imgFavorite;
        ImageView imageViewProductImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvReviewStars = itemView.findViewById(R.id.tvReviewStars);
            tvFalsePrice = itemView.findViewById(R.id.tvFalsePrice);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvFeature1 = itemView.findViewById(R.id.tvFeature1);
            tvFeature2 = itemView.findViewById(R.id.tvFeature2);
            cardFavorite = itemView.findViewById(R.id.cardFavorite);
            imgFavorite = itemView.findViewById(R.id.imgFavorite);
            imageViewProductImage = itemView.findViewById(R.id.imageViewProductImage);

        }
    }
}
