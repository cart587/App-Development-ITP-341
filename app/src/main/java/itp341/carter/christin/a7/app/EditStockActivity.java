package itp341.carter.christin.a7.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import itp341.carter.christin.a7.app.model.Stock;
import itp341.carter.christin.a7.app.model.StockSingleton;

/**
 * Created by Chris Carter on 11/7/2015
 */
public class EditStockActivity extends Activity{
    Button btnBuyStock;
    Button btnSellStock;
    Button btnSellAll;
    Button btnDeleteItem;
    TextView textViewProductName;
    TextView textViewBrand;
    TextView textViewPrice;
    TextView textViewColor;
    TextView textViewStock;
    int stockCount = 0;
    int position;
    Stock s;

    public static final String EXTRA_POSITION= "itp341.carter.christin.a7.app.extra_position";
    public final String TAG = "EditStockActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_stock_view);

        findAllViews();
        setListeners();

        //Load View
        Intent i = getIntent();
        position = i.getIntExtra(EXTRA_POSITION,-1);
        if(position != -1){
            loadViews();
            updateStockView();
        }else{
            //Shouldn't come here
            Log.d(TAG,"Went into unreachable else statement (Shouldn't be here)");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        StockSingleton.getInstance(this).saveStocks();
    }

    private void loadViews(){
        s = StockSingleton.getInstance(this).getStock(position);

        textViewProductName.setText(s.getName());
        textViewBrand.setText(s.getBrand());
        textViewPrice.setText(s.getPrice());
        textViewColor.setText(s.getColor());
        textViewStock.setText(Integer.toString(s.getStockCount()));

        stockCount = s.getStockCount();
    }

    private void incrementStock(){
        s.setStockCount(s.getStockCount() + 1);
    }

    private void decrementStock(){
        s.setStockCount(s.getStockCount() - 1);
    }

    private void sellAllStock(){
        s.setStockCount(0);
    }

    private void updateStockView(){
        textViewStock.setText(Integer.toString(s.getStockCount()));

        if(s.getStockCount() == 0){
            btnSellStock.setEnabled(false);
            btnSellAll.setEnabled(false);
        }else{
            btnSellStock.setEnabled(true);
            btnSellAll.setEnabled(true);
        }
    }

    private void setListeners(){
        btnBuyStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementStock();
                updateStockView();
                setResult(RESULT_OK);
            }
        });

        btnSellStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementStock();
                updateStockView();
                setResult(RESULT_OK);
            }
        });

        btnSellAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellAllStock();
                updateStockView();
                Toast.makeText(getApplicationContext(),R.string.sold_all_stocks,Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
            }
        });

        btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StockSingleton.getInstance(getApplicationContext()).removeStock(position);
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void findAllViews(){
        btnBuyStock = (Button) findViewById(R.id.ButtonBuyStock);
        btnSellStock = (Button) findViewById(R.id.ButtonSellStock);
        btnSellAll = (Button) findViewById(R.id.ButtonSellAll);
        btnDeleteItem = (Button) findViewById(R.id.ButtonDeleteItem);

        textViewProductName = (TextView) findViewById(R.id.editProductName);
        textViewBrand = (TextView) findViewById(R.id.editBrand);
        textViewPrice = (TextView) findViewById(R.id.editPrice);
        textViewColor = (TextView) findViewById(R.id.editColor);
        textViewStock = (TextView) findViewById(R.id.editStock);
    }
}
