package com.example.spideroidreturns.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spideroidreturns.Adapter.RecyclerProductDBAdapter;
import com.example.spideroidreturns.Model.ProductsDBexModel;
import com.example.spideroidreturns.R;
import com.example.spideroidreturns.Utility.MyDBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DataBumActivity extends AppCompatActivity {
    RecyclerView rvInsertedRecord;
    FloatingActionButton fabAddProduct;
    RecyclerProductDBAdapter adapter;
    Context context = DataBumActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_bum);

        rvInsertedRecord = findViewById(R.id.rvInsertedRecord);
        fabAddProduct = findViewById(R.id.fabAddProduct);

        rvInsertedRecord.setLayoutManager(new LinearLayoutManager(context,
                RecyclerView.VERTICAL, false));

        fetchProducts();

        fabAddProduct.setOnClickListener(v -> {
            startActivity(new Intent(context, AddProductActivity.class));
        });


    }

    private void fetchProducts() {
        MyDBHelper db = new MyDBHelper(context);

        if (db.fetchProducts() != null) {
            ArrayList<ProductsDBexModel> arrProductDetails = db.fetchProducts();
            adapter = new RecyclerProductDBAdapter(context, arrProductDetails);
            for (int i = 0; i < arrProductDetails.size(); i++) {
                if (arrProductDetails.get(i).getBitmapProductImage() != null) {
                    Log.d("TAG", "fetchProductss: Not Null");
                } else {
                    Log.d("TAG", "fetchProductss:" + i + "th ProductNull");
                }
            }
            rvInsertedRecord.setAdapter(adapter);
        } else {
            Toast.makeText(context, "NO Deto, hihi.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fetchProducts();
        adapter.notifyDataSetChanged();
    }
}