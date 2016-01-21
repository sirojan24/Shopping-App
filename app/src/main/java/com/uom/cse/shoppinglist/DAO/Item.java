package com.uom.cse.shoppinglist.DAO;

public class Item implements Comparable<Item>{

    private Integer id;
    private String name;
    private String category;
    private String units;
    private String days;
    private int timestamp;
    private int isInList;
    private Location location;
    private double shortDistance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getInList() {
        return isInList;
    }

    public void setIsInList(int isInList) {
        this.isInList = isInList;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getShortDistance() {
        return shortDistance;
    }

    public void setShortDistance(double shortDistance) {
        this.shortDistance = shortDistance;
    }

    @Override
    public int compareTo(Item another) {
        double thisDis = 0.0;
        double anoDis = 0.0;

        if(this.shortDistance > 0){
            thisDis = 1 / this.shortDistance;
        }

        if(another.getShortDistance() > 0){
            anoDis = 1 / another.getShortDistance();
        }

        return Double.compare(anoDis, thisDis);
    }
}
