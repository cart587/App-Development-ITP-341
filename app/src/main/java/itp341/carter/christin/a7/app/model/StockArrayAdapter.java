package itp341.carter.christin.a7.app.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import itp341.carter.christin.a7.app.R;

/**
 * Created by Chris on 11/6/2015.
 */
public class StockArrayAdapter extends ArrayAdapter<Stock>{
    public StockArrayAdapter(Context context, ArrayList<Stock> list){
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Stock stock = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom_adapter_view,
                    parent,
                    false);
        }

        ImageView imageViewLogo = (ImageView) convertView.findViewById(R.id.imageViewLogo);
        TextView textViewName = (TextView) convertView.findViewById(R.id.textViewProduct);
        TextView textViewStock = (TextView) convertView.findViewById(R.id.textViewStock);

        imageViewLogo.setImageResource(stock.getImageId());
        textViewName.setText(stock.getName());
        textViewStock.setText(Integer.toString(stock.getStockCount()));

        return convertView;
    }
}
