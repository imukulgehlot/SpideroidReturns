package com.example.spideroidreturns;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spideroidreturns.Utility.MyDBHelper;

public class AddProductActivity extends AppCompatActivity {
    ImageView imgViewCloseWindow;
    ImageView imageViewProductImage;
    EditText editTextProductName;
    EditText editTextProductReview;
    EditText editTextFalsePrice;
    EditText editTextPrice;
    EditText editTextFeature1;
    EditText editTextFeature2;
    Button buttonAddProduct;
    Context context = AddProductActivity.this;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        imgViewCloseWindow = findViewById(R.id.imgViewCloseWindow);
        imageViewProductImage = findViewById(R.id.imageViewProductImage);
        editTextProductName = findViewById(R.id.editTextProductName);
        editTextFalsePrice = findViewById(R.id.editTextFalsePrice);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextProductReview = findViewById(R.id.editTextProductReview);
        editTextFeature1 = findViewById(R.id.editTextFeature1);
        editTextFeature2 = findViewById(R.id.editTextFeature2);
        buttonAddProduct = findViewById(R.id.buttonAddProduct);


        if (!editTextProductName.getText().toString().isEmpty() || editTextProductName.getText().toString() == null) {
            editTextProductName.setError("Mandatory Field!");
            return;
        } else if (!editTextProductReview.getText().toString().isEmpty() || editTextProductReview.getText().toString() == null) {
            editTextProductReview.setError("Mandatory Field!");
            return;
        } else if (!editTextFalsePrice.getText().toString().isEmpty() || editTextFalsePrice.getText().toString() == null) {
            editTextFalsePrice.setError("Mandatory Field!");
            return;
        } else if (!editTextPrice.getText().toString().isEmpty() || editTextPrice.getText().toString() == null) {
            editTextPrice.setError("Mandatory Field!");
            return;
        } else if (!editTextFeature1.getText().toString().isEmpty() || editTextFeature1.getText().toString() == null) {
            editTextFeature1.setError("Mandatory Field!");
            return;
        } else if (!editTextFeature2.getText().toString().isEmpty() || editTextFeature2.getText().toString() == null) {
            editTextFeature2.setError("Mandatory Field!");
            return;
        } else {
            MyDBHelper db = new MyDBHelper(context);
            db.addProduct(editTextProductName.getText().toString()
                    , Integer.getInteger(editTextProductReview.getText().toString())
                    , Integer.getInteger(editTextFalsePrice.getText().toString())
                    , Integer.getInteger(editTextPrice.getText().toString())
                    , editTextFeature1.getText().toString()
                    , editTextFeature2.getText().toString());
        }
    }
}