package com.example.spideroidreturns.Utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.spideroidreturns.Model.ProductsDBexModel;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    Context context;
    String TAG = MyDBHelper.class.getSimpleName();
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
    private static final String KEY_IS_FAVORTIRE = "is_favorite";
    private static final String KEY_PRODUCT_IMAGE = "product_image";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PRODUCTS +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_PRODUCT_NAME + " TEXT," + KEY_REVIEW + " INTEGER,"
                + KEY_FALSE_PRICE + " INTEGER," + KEY_PRICE + " INTEGER,"
                + KEY_FEATURE1 + " TEXT," + KEY_FEATURE2 + " TEXT,"
                + KEY_PRODUCT_IMAGE + " BLOB NOT NULL," + KEY_IS_FAVORTIRE + " INTEGER)");
        // create table products(id integer primary key autoincrement, product_name text, product_review text . . . .
        //      , product_image blob)
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addProduct(String name, int review, int false_price, int price, String feature1, String feature2, Bitmap bitmapProduct) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_NAME, name);
        values.put(KEY_REVIEW, review);
        values.put(KEY_FALSE_PRICE, false_price);
        values.put(KEY_PRICE, price);
        values.put(KEY_FEATURE1, feature1);
        values.put(KEY_FEATURE2, feature2);
        values.put(KEY_PRODUCT_IMAGE, Utility.getBytes(bitmapProduct));
        values.put(KEY_IS_FAVORTIRE, 0);

        db.insert(TABLE_PRODUCTS, null, values);

    }

    public ArrayList<ProductsDBexModel> fetchProducts() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);

        ArrayList<ProductsDBexModel> arrProductDetails = new ArrayList<>();

        while (cursor.moveToNext()) {

            ProductsDBexModel model = new ProductsDBexModel();

            model.setProduct_name(cursor.getString(1));
            model.setReview(cursor.getInt(2));
            model.setFalse_price(cursor.getInt(3));
            model.setPrice(cursor.getInt(4));
            model.setFeature1(cursor.getString(5));
            model.setFeature2(cursor.getString(6));
            byte[] blob = (cursor.getBlob(7));
            model.setBitmapProductImage(Utility.getPhoto(blob));
            model.setIsFavorite(cursor.getInt(8));
            arrProductDetails.add(model);
        }
        return arrProductDetails;
    }

    public void DeleteProduct(int ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PRODUCTS, KEY_ID + " =" + ID, null);
        Toast.makeText(context, "Record Deleted Successfully!", Toast.LENGTH_SHORT).show();
    }

    public void updateProductFavorite(int Position, int isFav) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_IS_FAVORTIRE, isFav);

        db.update(TABLE_PRODUCTS, cv, KEY_ID + " = " + Position, null);
    }
}
