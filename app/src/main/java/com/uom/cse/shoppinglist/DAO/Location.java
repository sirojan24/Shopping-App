package com.uom.cse.shoppinglist.DAO;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sirojan on 1/21/2016.
 */
public class Location {
    private int id;
    private String name;
    private List<String> items;
    private String itemsStr;
    private double longitude;
    private double latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getItems() {
        return items;
    }

    public String getItemsStr() {
        return itemsStr;
    }

    public void setItemsStr(String itemsStr) {
        String[] items = itemsStr.split(";");
        this.items = Arrays.asList(items);
        this.itemsStr = itemsStr;
    }



    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}
