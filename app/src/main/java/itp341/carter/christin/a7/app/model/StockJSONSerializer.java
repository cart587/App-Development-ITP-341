package itp341.carter.christin.a7.app.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Chris on 11/6/2015.
 */
public class StockJSONSerializer {
    private Context context;
    private String filename;

    public static final String TAG = "StockJSONSerializer";
    String[] json_keys = {"iPhone 5s","iPhone 6","iPhone 6s","iWatch","Galaxy S5",
            "Galaxy S6", "Gear S2", "MacBook Pro", "Surface Book", "Occulus", "Holo Lens",
            "Samsung Gear VR"};


    public StockJSONSerializer(Context context, String filename) {
        this.context = context;
        this.filename = filename;
    }

    public ArrayList<Stock> loadStocks() throws IOException, JSONException {
        Log.d(TAG, "In loadStocks()");

        ArrayList<Stock> stocks = new ArrayList<>();
        BufferedReader reader = null;
        InputStream in;
        try {
            // open and read the file into a StringBuilder
            File file = new File(filename);
            if(file.exists()) {
                in = context.openFileInput(filename);
                Log.d(TAG, "Reading stocks.json from internal file");
            } else {
                in = context.getAssets().open(filename);
                Log.d(TAG, "Reading stocks.json from assets");
            }
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            // parse the JSON using JSONTokener
            Log.d(TAG, "Right before Tokener");
            JSONObject object = (JSONObject)new JSONTokener(jsonString.toString()).nextValue();
            JSONObject stockObject = object.getJSONObject("stock");
            Log.d(TAG, "Made it to For-loop");
            // build the array of coffeeShops from JSONObjects
            for (int i = 0; i < json_keys.length; i++) {
                stocks.add(new Stock(stockObject.getJSONObject(json_keys[i]),json_keys[i]));
            }

        } catch (Exception e) {
            Log.d(TAG, "Exception thrown");
        }
        finally {
            if (reader != null)
                reader.close();
        }
        return stocks;
    }

    public void saveCoffeeShops(ArrayList<Stock> stockArrayList) throws JSONException, IOException{
        //build JSON array
        JSONArray jsonArray = new JSONArray();

        for (Stock s: stockArrayList){
            jsonArray.put(s.toJSON());
        }
        //MIGHT NEED TO ADD TRY-CATCH STATEMENT
        //Write file to disk
        Writer writer = null;
        //Open file and connect to data stream
        OutputStream out = context.openFileOutput(filename,Context.MODE_PRIVATE);
        //Writer is now connected to android file system
        writer = new OutputStreamWriter(out);

        writer.write(jsonArray.toString());

        if(writer != null){
            writer.close();
        }
    }

}
