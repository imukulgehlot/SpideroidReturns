package com.example.spideroidreturns.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spideroidreturns.Adapter.RecyclerProductDBAdapter;
import com.example.spideroidreturns.AddProductActivity;
import com.example.spideroidreturns.Model.ProductsDBexModel;
import com.example.spideroidreturns.R;
import com.example.spideroidreturns.Utility.MyDBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DataBumActivity extends AppCompatActivity {
    RecyclerView rvInsertedRecord;
    FloatingActionButton fabAddProduct;
    Context context = DataBumActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_bum);

        rvInsertedRecord = findViewById(R.id.rvInsertedRecord);
        fabAddProduct = findViewById(R.id.fabAddProduct);

        rvInsertedRecord.setLayoutManager(new LinearLayoutManager(context,
                RecyclerView.VERTICAL, false));

        fabAddProduct.setOnClickListener(v -> {
            startActivity(new Intent(context, AddProductActivity.class));
        });

        MyDBHelper db = new MyDBHelper(context);

        if (db.fetchProducts() != null) {
            ArrayList<ProductsDBexModel> arrProductDetails = db.fetchProducts();
            RecyclerProductDBAdapter adapter = new RecyclerProductDBAdapter(context, arrProductDetails);
            rvInsertedRecord.setAdapter(adapter);
        } else {
            Toast.makeText(context, "NO Deto, hihi.", Toast.LENGTH_SHORT).show();
        }


    }
}