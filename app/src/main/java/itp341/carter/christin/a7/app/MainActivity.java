package itp341.carter.christin.a7.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.Collections;

import itp341.carter.christin.a7.app.model.StockArrayAdapter;
import itp341.carter.christin.a7.app.model.StockJSONSerializer;
import itp341.carter.christin.a7.app.model.StockSingleton;

public class MainActivity extends Activity {
    ListView listView;
    ImageButton btnAdd;
    StockArrayAdapter arrayAdapter;

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayAdapter = new StockArrayAdapter(this, StockSingleton.getInstance(this).getStocks());
        findAllViews();
        setListenersAndAdapters();
    }

    private void findAllViews(){
        Log.d(TAG,"In find All Views");

        listView = (ListView) findViewById(R.id.listViewStocks);
        btnAdd = (ImageButton) findViewById(R.id.imageButtonAdd);
    }

    private void setListenersAndAdapters(){
        listView.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddStockActivity.class);
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
