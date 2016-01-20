package com.uom.cse.shoppinglist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.uom.cse.shoppinglist.DAO.Item;
import com.uom.cse.shoppinglist.DAO.ItemsDBHandler;
import com.uom.cse.shoppinglist.adapters.ItemListAdapter;

import java.util.List;

public class ShoppingListFragment extends Fragment {

    private View shoppingListView;

    private ListView ListViewItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        shoppingListView = inflater.inflate(R.layout.shoping_list_layout, container, false);

        ListViewItems = (ListView) shoppingListView.findViewById(R.id.listViewItems);

        ItemsDBHandler handler = new ItemsDBHandler(getActivity());

        final List<Item> itemList = handler.getActiveItems();

        ItemListAdapter adapter = new ItemListAdapter(getActivity(), itemList);

        ListViewItems.setAdapter(adapter);

        return shoppingListView;
    }
}
