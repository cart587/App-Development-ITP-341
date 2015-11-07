package itp341.carter.christin.a7.app.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Chris on 11/6/2015.
 */
public class StockSingleton {
    ArrayList<Stock> mStocks;

    private static StockSingleton sStockSingleton;
    private Context context;

    public static final String FILENAME = "stocks.json";
    private StockJSONSerializer mSerializer;

    private StockSingleton(Context c){
        this.context = c;

        mSerializer = new StockJSONSerializer(context,FILENAME);

        try{
            mStocks = mSerializer.loadStocks();
            alphabeticallyOrderStocks();
        }catch(Exception e){
            mStocks = new ArrayList<>();
        }
    }

    public static StockSingleton getInstance(Context c){
        if(sStockSingleton == null)
            sStockSingleton = new StockSingleton(c.getApplicationContext());
        return sStockSingleton;
    }

    public void alphabeticallyOrderStocks(){
        Collections.sort(mStocks,new Stock.StockComparator());
    }

    public ArrayList<Stock> getStocks(){
        return mStocks;
    }

    public Stock getStock(int index){
        return mStocks.get(index);
    }

    public void addStock(Stock s){
        mStocks.add(s);
        alphabeticallyOrderStocks();
    }

    public void updateStock(int position, Stock s){
        if(position >= 0 && position < mStocks.size())
            mStocks.set(position,s);
    }

    public void removeStock(int position){
        if(position >= 0 && position < mStocks.size())
            mStocks.remove(position);
    }

    public boolean saveStocks(){
        try{
            mSerializer.saveCoffeeShops(mStocks);
            return true;
        }catch (Exception e){

        }
        return false;
    }
}
