package com.uom.cse.shoppinglist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.uom.cse.shoppinglist.DAO.Item;
import com.uom.cse.shoppinglist.DAO.ItemsDBHandler;
import com.uom.cse.shoppinglist.R;
import com.uom.cse.shoppinglist.ShopingListActivity;

import java.util.List;

public class ItemListAdapter extends BaseAdapter {
    List<Item> itemList;
    Context context;

    public ItemListAdapter(Context context, List<Item> itemList){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_details_layout, parent, false);

        TextView name = (TextView) row.findViewById(R.id.lblItemDisplayName);
        TextView category = (TextView) row.findViewById(R.id.lblItemDisplayCategory);
        TextView units = (TextView) row.findViewById(R.id.lblItemDisplayUnits);
        TextView distance = (TextView) row.findViewById(R.id.lblDistance);
        Button btnCompletePurchase = (Button) row.findViewById(R.id.btnCompletePurchase);

        final Item item = itemList.get(position);

        name.setText(item.getName());
        category.setText(item.getCategory());
        units.setText(item.getUnits());
        distance.setText(item.getShortDistance() + "");

        btnCompletePurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemsDBHandler handler = new ItemsDBHandler(context);
                handler.removeItem(item);

                ((ShopingListActivity)context).onSaveSelected();
            }
        });

        return row;
    }
}
