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

public class AddNewLocationFragment extends Fragment {

    private View addLocationView;

    private Button btnSave;

    private EditText txtLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addLocationView = inflater.inflate(R.layout.add_new_location_layout, container, false);

        btnSave = (Button) addLocationView.findViewById(R.id.btnSave);

        txtLocation = (EditText) addLocationView.findViewById(R.id.txtItemName);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemsDBHandler handler = new ItemsDBHandler(getActivity());
                Date date = new Date();
                int timestamp = (int) date.getTime();

            }
        });

        return addLocationView;
    }
}
