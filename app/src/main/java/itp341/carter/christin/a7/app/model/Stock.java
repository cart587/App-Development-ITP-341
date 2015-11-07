package itp341.carter.christin.a7.app.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;

import itp341.carter.christin.a7.app.R;

/**
 * Created by Chris on 11/6/2015.
 */
public class Stock {
//    private static final String JSON_NAME = "name";
    private static final String JSON_BRAND = "brand";
    private static final String JSON_PRICE = "price";
    private static final String JSON_COLOR = "color";
    private static final String JSON_STOCK = "stock";

    private String name;
    private String brand;
    private String price;
    private String color;
    private int stockCount;
    private int imageId;

    private final String TAG = "Stock";
    public Stock(String name, String brand, String price, String color, int stockCount) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.color = color;
        this.stockCount = stockCount;
        imageId = getImage(brand);
    }


    public Stock(JSONObject jsonObject, String name) throws JSONException{
        Log.d(TAG, "In stock jsonObject constructor");

        this.name = name;
        brand = jsonObject.getString(JSON_BRAND);
        price = jsonObject.getString(JSON_PRICE);
        color = jsonObject.getString(JSON_COLOR);
        stockCount = jsonObject.getInt(JSON_STOCK);
        imageId = getImage(brand);
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put(JSON_NAME,name);
        jsonObject.put(JSON_PRICE,price);
//        jsonObject.put(JSON_STOCK,stockCount);
        jsonObject.put(JSON_COLOR,color);
        jsonObject.put(JSON_BRAND,brand);
        return jsonObject;
    }

    public int getImage(String brand){
        brand = brand.toLowerCase();
        int srcID = -1;
        switch (brand){
            case "apple":
                srcID = R.drawable.apple;
                break;
            case "microsoft":
                srcID = R.drawable.microsoft;
                break;
            case "samsung":
                srcID = R.drawable.samsung;
                break;
            default:
                srcID = R.drawable.logo;
        }
        return srcID;
    }

    public int getImageId(){
        return imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    static class StockComparator implements Comparator<Stock> {
        @Override
        public int compare(Stock lhs, Stock rhs) {
            return lhs.getName().compareToIgnoreCase(rhs.getName());
        }
    }
}
