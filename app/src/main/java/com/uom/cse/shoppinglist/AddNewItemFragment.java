package com.uom.cse.shoppinglist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.uom.cse.shoppinglist.DAO.Item;
import com.uom.cse.shoppinglist.DAO.ItemsDBHandler;

import java.util.Date;

public class AddNewItemFragment extends Fragment {

    private View addCategoryView;

    private Spinner spinnerCategory;

    private Spinner spinnerCloseDays;

    private Button btnSave;

    private EditText txtItemName;

    private EditText txtUnits;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addCategoryView = inflater.inflate(R.layout.add_new_item_layout, container, false);

        spinnerCategory = (Spinner) addCategoryView.findViewById(R.id.spinnerCategory);

        spinnerCloseDays = (Spinner) addCategoryView.findViewById(R.id.spinnerCloseDays);

        btnSave = (Button) addCategoryView.findViewById(R.id.btnSave);

        txtItemName = (EditText) addCategoryView.findViewById(R.id.txtItemName);

        txtUnits = (EditText) addCategoryView.findViewById(R.id.txtUnits);

//        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.categories,
//                android.R.layout.simple_spinner_item);
        ArrayAdapter categoryAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.categories,
                R.layout.spinner_layout);

        spinnerCategory.setAdapter(categoryAdapter);

        ArrayAdapter closeDaysAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.days,
                R.layout.spinner_layout);

        spinnerCloseDays.setAdapter(closeDaysAdapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemsDBHandler handler = new ItemsDBHandler(getActivity());
                Date date = new Date();
                int timestamp = (int) date.getTime();

                if (!"".equals(txtItemName.getText().toString()) && !"".equals(txtUnits.getText())) {
                    Item item = new Item();
                    item.setName(txtItemName.getText().toString());
                    item.setCategory(spinnerCategory.getSelectedItem().toString());
                    item.setUnits(txtUnits.getText().toString());
                    item.setDays(spinnerCloseDays.getSelectedItem().toString());
                    item.setTimestamp(timestamp);
                    item.setIsInList(1);

                    handler.insertItem(item);

                    ((ShopingListActivity) getActivity()).onSaveSelected();
                } else {
                    Toast.makeText(getActivity(), "Please Enter Item Name and Units", Toast.LENGTH_LONG).show();
                }
            }
        });

        return addCategoryView;
    }
}
