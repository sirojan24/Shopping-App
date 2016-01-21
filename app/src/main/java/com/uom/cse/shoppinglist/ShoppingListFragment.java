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
import com.uom.cse.shoppinglist.DAO.Location;
import com.uom.cse.shoppinglist.DAO.LocationDBHandler;
import com.uom.cse.shoppinglist.adapters.ItemListAdapter;
import com.uom.cse.shoppinglist.gpsTracker.GPSTracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingListFragment extends Fragment {

    private View shoppingListView;

    private ListView ListViewItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        shoppingListView = inflater.inflate(R.layout.shoping_list_layout, container, false);

        ListViewItems = (ListView) shoppingListView.findViewById(R.id.listViewItems);

        ItemsDBHandler itemHandler = new ItemsDBHandler(getActivity());

        LocationDBHandler locationHandler = new LocationDBHandler(getActivity());

        final List<Item> itemList = itemHandler.getActiveItems();

        final List<Location> locationList = locationHandler.getAllItems();

        double longDis = 0.0;
        for(Item item : itemList){
            for (Location location : locationList){
                if(location.getItems().contains(item.getCategory())){
                    double distance = calculateDistanceFromCurrentLocation
                            (location.getLatitude(), location.getLongitude());

                    if(longDis < distance){
                        longDis = distance;
                    }
                    if(item.getLocation() != null){

                        if(item.getShortDistance() > distance){
                            item.setLocation(location);
                            item.setShortDistance(calculateDistanceFromCurrentLocation
                                    (location.getLatitude(), location.getLongitude()));
                        }

                    }else{
                        item.setLocation(location);
                        item.setShortDistance(distance);
                    }
                }
            }
        }

        Collections.sort(itemList);

        ItemListAdapter adapter = new ItemListAdapter(getActivity(), itemList);

        ListViewItems.setAdapter(adapter);

        return shoppingListView;
    }

    private double calculateDistanceFromCurrentLocation(double lat, double longi){
        // create class object
        GPSTracker gps = new GPSTracker(getActivity());

        double currLat = 0.0;
        double currLong = 0.0;

        // check if GPS enabled
        if(gps.canGetLocation()){

            currLat = gps.getLatitude();
            currLong = gps.getLongitude();

            double dis = ((currLat - lat) * (currLat - lat)) + ((currLong - longi) * (currLong - longi));
            dis = Math.sqrt(dis);

            return dis;
        }else{
            return 0.0;
        }
    }
}
