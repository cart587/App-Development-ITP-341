package itp341.carter.christin.a7.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import itp341.carter.christin.a7.app.model.Stock;
import itp341.carter.christin.a7.app.model.StockSingleton;

/**
 * Created by Chris on 11/7/2015.
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
        int position = i.getIntExtra(EXTRA_POSITION,-1);
        if(position != -1){
            loadViews(position);
        }else{
            //Shouldn't come here
            Log.d(TAG,"Went into unreachable else statement (Shouldn't be here)");
        }
    }

    private void loadViews(int position){
        Stock s = StockSingleton.getInstance(this).getStock(position);

        textViewProductName.setText(s.getName());
        textViewBrand.setText(s.getBrand());
        textViewPrice.setText(s.getPrice());
        textViewColor.setText(s.getColor());
        textViewStock.setText(Integer.toString(s.getStockCount()));
    }

    private void setListeners(){
        btnBuyStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSellStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSellAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
