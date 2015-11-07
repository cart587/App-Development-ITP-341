package itp341.carter.christin.a7.app;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Chris on 11/7/2015.
 */
public class EditStockActivity extends Activity{

    public static final String EXTRA_POSITION= "itp341.carter.christin.a7.app.extra_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_stock_view);

        findAllViews();
    }

    private void findAllViews(){

    }
}
