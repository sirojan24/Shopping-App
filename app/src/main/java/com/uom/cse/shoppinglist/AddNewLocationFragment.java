package com.uom.cse.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.uom.cse.shoppinglist.DAO.Item;
import com.uom.cse.shoppinglist.DAO.ItemsDBHandler;
import com.uom.cse.shoppinglist.DAO.Location;
import com.uom.cse.shoppinglist.DAO.LocationDBHandler;

import java.util.Date;
import java.util.List;

public class AddNewLocationFragment extends Fragment {

    private View addLocationView;

    private Button btnSave;

    private EditText txtLocation;

    private CheckBox chkGrocery;
    private CheckBox chkVegetable;
    private CheckBox chkFruit;
    private CheckBox chkMilkProduct;
    private CheckBox chkMeatandNonVegetable;
    private CheckBox chkBakeryProduct;
    private CheckBox chkElectricalItem;
    private CheckBox chkHardwareItem;
    private CheckBox chkHousekeepingItem;
    private CheckBox chkStationeryItem;
    private CheckBox chkMedicine;
    private CheckBox chkEntertainment;

    private static double longitude;
    private static double latitude;

    private static boolean isEditing;
    private static Location location;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addLocationView = inflater.inflate(R.layout.add_new_location_layout, container, false);

        btnSave = (Button) addLocationView.findViewById(R.id.btnSave);

        txtLocation = (EditText) addLocationView.findViewById(R.id.txtLocation);

        chkGrocery = (CheckBox) addLocationView.findViewById(R.id.chkGrocery);
        chkVegetable = (CheckBox) addLocationView.findViewById(R.id.chkVegetable);
        chkFruit = (CheckBox) addLocationView.findViewById(R.id.chkFruit);
        chkMilkProduct = (CheckBox) addLocationView.findViewById(R.id.chkMilkProduct);
        chkMeatandNonVegetable = (CheckBox) addLocationView.findViewById(R.id.chkMeatandNonVegetable);
        chkBakeryProduct = (CheckBox) addLocationView.findViewById(R.id.chkBakeryProduct);
        chkElectricalItem = (CheckBox) addLocationView.findViewById(R.id.chkElectricalItem);
        chkHardwareItem = (CheckBox) addLocationView.findViewById(R.id.chkHardwareItem);
        chkHousekeepingItem = (CheckBox) addLocationView.findViewById(R.id.chkHousekeepingItem);
        chkStationeryItem = (CheckBox) addLocationView.findViewById(R.id.chkStationeryItem);
        chkMedicine = (CheckBox) addLocationView.findViewById(R.id.chkMedicine);
        chkEntertainment = (CheckBox) addLocationView.findViewById(R.id.chkEntertainment);

        if (isEditing){
            txtLocation.setText(location.getName());

            List<String> categories = location.getItems();

            if(categories.contains("Grocery")){
                chkGrocery.setChecked(true);
            }
            if(categories.contains("Vegetable")){
                chkVegetable.setChecked(true);
            }
            if(categories.contains("Fruit")){
                chkFruit.setChecked(true);
            }
            if(categories.contains("Electrical Item")){
                chkElectricalItem.setChecked(true);
            }
            if(categories.contains("Hardware Item")){
                chkHardwareItem.setChecked(true);
            }
            if(categories.contains("Housekeeping Item")){
                chkHousekeepingItem.setChecked(true);
            }
            if(categories.contains("Milk Product")){
                chkMilkProduct.setChecked(true);
            }
            if(categories.contains("Meat and Non-vegetable")){
                chkMeatandNonVegetable.setChecked(true);
            }
            if(categories.contains("Bakery Product")){
                chkBakeryProduct.setChecked(true);
            }
            if(categories.contains("Stationery Item")){
                chkStationeryItem.setChecked(true);
            }
            if(categories.contains("Medicine")){
                chkMedicine.setChecked(true);
            }
            if(categories.contains("Entertainment")){
                chkEntertainment.setChecked(true);
            }

        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationDBHandler handler = new LocationDBHandler(getActivity());
                Location tempLocation = new Location();

                String itemsStr = "";
                if(chkGrocery.isChecked()){
                    itemsStr = chkGrocery.getText().toString() + ";";
                }
                if(chkVegetable.isChecked()){
                    itemsStr += chkVegetable.getText().toString() + ";";
                }
                if(chkFruit.isChecked()){
                    itemsStr += chkFruit.getText().toString() + ";";
                }
                if(chkMilkProduct.isChecked()){
                    itemsStr += chkMilkProduct.getText().toString() + ";";
                }
                if(chkMeatandNonVegetable.isChecked()){
                    itemsStr += chkMeatandNonVegetable.getText().toString() + ";";
                }
                if(chkBakeryProduct.isChecked()){
                    itemsStr += chkBakeryProduct.getText().toString() + ";";
                }
                if(chkElectricalItem.isChecked()){
                    itemsStr += chkElectricalItem.getText().toString() + ";";
                }
                if(chkHardwareItem.isChecked()){
                    itemsStr += chkHardwareItem.getText().toString() + ";";
                }
                if(chkHousekeepingItem.isChecked()){
                    itemsStr += chkHousekeepingItem.getText().toString() + ";";
                }
                if(chkStationeryItem.isChecked()){
                    itemsStr += chkStationeryItem.getText().toString() + ";";
                }
                if(chkMedicine.isChecked()){
                    itemsStr += chkMedicine.getText().toString() + ";";
                }
                if(chkEntertainment.isChecked()){
                    itemsStr += chkEntertainment.getText().toString() + ";";
                }

                if (itemsStr.length() > 0 && itemsStr.charAt(itemsStr.length()-1)==';') {
                    itemsStr = itemsStr.substring(0, itemsStr.length()-1);
                }

                tempLocation.setName(txtLocation.getText().toString());
                tempLocation.setItemsStr(itemsStr);
                tempLocation.setLongitude(longitude);
                tempLocation.setLatitude(latitude);

                if (isEditing) {
                    tempLocation.setId(location.getId());
                    tempLocation.setLatitude(location.getLatitude());
                    tempLocation.setLongitude(location.getLongitude());
                    handler.updateItem(tempLocation);
                }else{
                    handler.insertItem(tempLocation);
                }

                ((ShopingListActivity)getActivity()).startMapActivity();
            }
        });

        return addLocationView;
    }

    public static void setLonLat(double longi, double lati){
        longitude = longi;
        latitude = lati;
        isEditing = false;
    }

    public static void edit(Location loc){
        isEditing = true;
        location = loc;
    }
}
