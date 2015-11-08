package itp341.carter.christin.a7.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParsePosition;

import itp341.carter.christin.a7.app.model.Stock;
import itp341.carter.christin.a7.app.model.StockSingleton;

/**
 * Created by Chris on 11/6/2015.
 */
public class AddStockActivity extends Activity {
    Button btnSave;
    EditText editTextProductName;
    EditText editTextBrand;
    EditText editTextPrice;
    EditText editTextColor;
    EditText editTextStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_stock_view);

        findAllViews();

        if(btnSave != null){
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveAndClose();
                }
            });
        }
    }

    private void saveAndClose(){
        String pName = editTextProductName.getText().toString();
        String brand = editTextBrand.getText().toString();
        String price = parsePrice(editTextPrice.getText().toString());
        String color = editTextColor.getText().toString();
        int stock = Integer.parseInt(editTextStock.getText().toString());

        Stock newStock = new Stock(pName,brand,price,color,stock);
        StockSingleton.getInstance(this).addStock(newStock);
        StockSingleton.getInstance(this).saveStocks();

        setResult(RESULT_OK);
        finish();
    }

    private void findAllViews(){
        btnSave = (Button) findViewById(R.id.buttonSave);
        editTextProductName = (EditText) findViewById(R.id.editTextProductName);
        editTextBrand = (EditText) findViewById(R.id.editTextBrand);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextColor = (EditText) findViewById(R.id.editTextColor);
        editTextStock = (EditText) findViewById(R.id.editTextStock);

    }

    private String parsePrice(String rawInput){
        if(rawInput.charAt(0) == '$'){
            if(!isNumeric(rawInput.substring(1))){
                Toast.makeText(this,R.string.invalid_price,Toast.LENGTH_LONG).show();
                return "NA";
            }else{
                return rawInput;
            }
        }else{
            if(!isNumeric(rawInput.substring(1))){
                Toast.makeText(this,R.string.invalid_price,Toast.LENGTH_LONG).show();
                return "NA";
            }else{
                return "$" + rawInput;
            }
        }
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
