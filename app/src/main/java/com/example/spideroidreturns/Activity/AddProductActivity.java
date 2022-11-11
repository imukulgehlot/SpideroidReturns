package com.example.spideroidreturns.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spideroidreturns.R;
import com.example.spideroidreturns.Utility.MyDBHelper;

import java.io.IOException;

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
    String TAG = AddProductActivity.class.getSimpleName();
    Context context = AddProductActivity.this;
    Bitmap bitmapImg;
    private static int GALLERY_REQ_CODE = 121;

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

        imageViewProductImage.setOnClickListener(v -> {
            imageChoose();
        });

        imgViewCloseWindow.setOnClickListener(v -> {
            if (!editTextProductName.getText().toString().isEmpty() || !editTextProductReview.getText().toString().isEmpty()
                    || !editTextFalsePrice.getText().toString().isEmpty() || !editTextPrice.getText().toString().isEmpty() ||
                    !editTextFeature1.getText().toString().isEmpty() || !editTextFeature2.getText().toString().isEmpty() ||
                    bitmapImg != null)
                exitDialog();
            else {
                finish();
            }

        });

        buttonAddProduct.setOnClickListener(v -> {
            if (editTextProductName.getText().toString().isEmpty() || editTextProductName.getText().toString() == null) {
                editTextProductName.setError("Mandatory Field!");
                return;
            } else if (editTextProductReview.getText().toString().isEmpty() || editTextProductReview.getText().toString() == null) {
                editTextProductReview.setError("Mandatory Field!");
                return;
            } else if (editTextFalsePrice.getText().toString().isEmpty() || editTextFalsePrice.getText().toString() == null) {
                editTextFalsePrice.setError("Mandatory Field!");
                return;
            } else if (editTextPrice.getText().toString().isEmpty() || editTextPrice.getText().toString() == null) {
                editTextPrice.setError("Mandatory Field!");
                return;
            } else if (editTextFeature1.getText().toString().isEmpty() || editTextFeature1.getText().toString() == null) {
                editTextFeature1.setError("Mandatory Field!");
                return;
            } else if (editTextFeature2.getText().toString().isEmpty() || editTextFeature2.getText().toString() == null) {
                editTextFeature2.setError("Mandatory Field!");
                return;
            } else if (bitmapImg == null) {
                Toast.makeText(context, "Insert an image", Toast.LENGTH_SHORT).show();
                return;
            }
            MyDBHelper db = new MyDBHelper(context);
            db.addProduct(editTextProductName.getText().toString().trim(),
                    Integer.parseInt(editTextProductReview.getText().toString()),
                    Integer.parseInt(editTextFalsePrice.getText().toString()),
                    Integer.parseInt(editTextPrice.getText().toString()),
                    editTextFeature1.getText().toString().trim(),
                    editTextFeature2.getText().toString().trim(),
                    bitmapImg);


            Toast.makeText(context, "Product added successphully", Toast.LENGTH_SHORT).show();
            finish();
            /*
            Log.d(AddProductActivity.class.getSimpleName(), "onCreate: noobductname: " + editTextProductName.getText().toString()
                    + "\nReview " + Integer.parseInt(editTextProductReview.getText().toString())
                    + "\nfprice " + Integer.parseInt(editTextFalsePrice.getText().toString())
                    + "\nprice " + Integer.parseInt(editTextPrice.getText().toString())
                    + "\nf1 " + editTextFeature1.getText().toString()
                    + "\nf2 " + editTextFeature2.getText().toString());*/
        });
    }

    private void exitDialog() {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("Discard this form?");
        dialog.setMessage("Are you sure you want to leave this form");
        dialog.setIcon(R.drawable.ic_baseline_exit_to_app_24);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", (dialog1, which) -> finish());
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Nopes", ((dialog2, which) -> dialog.dismiss()));
        dialog.show();
    }

    private void imageChoose() {
        Intent iGallery = new Intent();
        iGallery.setType("image/*");
        iGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(iGallery, "Select product picture"), GALLERY_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imageViewProductImage.setPadding(0, 0, 0, 0);
                    imageViewProductImage.setImageURI(selectedImageUri);
                    try {
                        bitmapImg = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Image not selected", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (!editTextProductName.getText().toString().isEmpty() || !editTextProductReview.getText().toString().isEmpty()
                || !editTextFalsePrice.getText().toString().isEmpty() || !editTextPrice.getText().toString().isEmpty() ||
                !editTextFeature1.getText().toString().isEmpty() || !editTextFeature2.getText().toString().isEmpty() ||
                bitmapImg != null) {

            AlertDialog dialog = new AlertDialog.Builder(context).create();
            dialog.setTitle("Discard this form?");
            dialog.setMessage("Are you sure you want to leave this form");
            dialog.setIcon(R.drawable.ic_baseline_exit_to_app_24);
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", (dialog1, which) -> super.
                    onBackPressed());
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Nopes", ((dialog2, which) -> dialog.dismiss()));
            dialog.show();
        } else {
            super.onBackPressed();
        }
    }
}