package com.example.spideroidreturns.Utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.spideroidreturns.Model.ProductsDBexModel;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SpidroidDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PRODUCTS = "products";
    private static final String KEY_ID = "id";
    private static final String KEY_PRODUCT_NAME = "product_name";
    private static final String KEY_REVIEW = "product_review";
    private static final String KEY_FALSE_PRICE = "product_false_price";
    private static final String KEY_PRICE = "product_price";
    private static final String KEY_FEATURE1 = "product_feature1";
    private static final String KEY_FEATURE2 = "product_feature2";
    private static final String KEY_PRODUCT_IMAGE = "product_image";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PRODUCTS +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_PRODUCT_NAME + " TEXT," + KEY_REVIEW + " INTEGER,"
                + KEY_FALSE_PRICE + " INTEGER," + KEY_PRICE + " INTEGER,"
                + KEY_FEATURE1 + " TEXT," + KEY_FEATURE2 + " TEXT)");

        // create table products(id integer primary key autoincrement, name text, review integer, fPrice integer, price integer, feature1 text, feature2 text)
        SQLiteDatabase database = this.getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addProduct(String name, int review, int false_price, int price, String feature1, String feature2) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_NAME, name);
        values.put(KEY_REVIEW, review);
        values.put(KEY_FALSE_PRICE, false_price);
        values.put(KEY_PRICE, price);
        values.put(KEY_FEATURE1, feature1);
        values.put(KEY_FEATURE2, feature2);
        db.insert(TABLE_PRODUCTS, null, values);
    }

    public ArrayList<ProductsDBexModel> fetchProducts() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);

        ArrayList<ProductsDBexModel> arrProductDetails = new ArrayList<>();

        while (cursor.moveToNext()){

            ProductsDBexModel model = new ProductsDBexModel();

            model.setProduct_name(cursor.getString(1));
            model.setReview(cursor.getInt(2));
            model.setFalse_price(cursor.getInt(3));
            model.setPrice(cursor.getInt(4));
            model.setFeature1(cursor.getString(5));
            model.setFeature2(cursor.getString(6));

            arrProductDetails.add(model);
        }
        return arrProductDetails;
    }
}
