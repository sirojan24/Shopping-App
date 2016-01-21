package com.uom.cse.shoppinglist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocationDBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "LocationsDetails";
    public static final String LOCATION_TABLE_NAME = "location";
    public static final String LOCATION_COLUMN_ID = "id";
    public static final String LOCATION_COLUMN_NAME = "name";
    public static final String LOCATION_COLUMN_ITEMS = "items";
    public static final String LOCATION_COLUMN_LONG = "longi";
    public static final String LOCATION_COLUMN_LAT = "lati";
    private HashMap hp;

    public LocationDBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + LOCATION_TABLE_NAME + " " +
                        "(" + LOCATION_COLUMN_ID + " integer primary key, " + LOCATION_COLUMN_NAME + " text," +
                        "" + LOCATION_COLUMN_ITEMS + " text," + LOCATION_COLUMN_LONG + " real," + LOCATION_COLUMN_LAT + " real)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + LOCATION_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertItem  (Location location)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOCATION_COLUMN_NAME, location.getName());
        contentValues.put(LOCATION_COLUMN_ITEMS, location.getItemsStr());
        contentValues.put(LOCATION_COLUMN_LONG, location.getLongitude());
        contentValues.put(LOCATION_COLUMN_LAT, location.getLatitude());
        db.insert(LOCATION_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + LOCATION_TABLE_NAME + " where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, LOCATION_TABLE_NAME);
        return numRows;
    }

    public boolean updateItem (Location location)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOCATION_COLUMN_NAME, location.getName());
        contentValues.put(LOCATION_COLUMN_ITEMS, location.getItemsStr());
        contentValues.put(LOCATION_COLUMN_LONG, location.getLongitude());
        contentValues.put(LOCATION_COLUMN_LAT, location.getLatitude());
        db.update(LOCATION_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(location.getId()) } );
        return true;
    }

    public boolean removeItem (Location location)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOCATION_COLUMN_NAME, location.getName());
        contentValues.put(LOCATION_COLUMN_ITEMS, location.getItemsStr());
        contentValues.put(LOCATION_COLUMN_LONG, location.getLongitude());
        contentValues.put(LOCATION_COLUMN_LAT, location.getLatitude());
        db.update(LOCATION_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(location.getId()) } );
        return true;
    }

    public Integer deleteItem (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(LOCATION_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public List<Location> getAllItems()
    {
        ArrayList<Location> array_list = new ArrayList<Location>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + LOCATION_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Location location = new Location();
            location.setId(res.getInt(res.getColumnIndex(LOCATION_COLUMN_ID)));
            location.setName(res.getString(res.getColumnIndex(LOCATION_COLUMN_NAME)));
            location.setItemsStr(res.getString(res.getColumnIndex(LOCATION_COLUMN_ITEMS)));
            location.setLatitude(res.getDouble(res.getColumnIndex(LOCATION_COLUMN_LAT)));
            location.setLongitude(res.getDouble(res.getColumnIndex(LOCATION_COLUMN_LONG)));
            array_list.add(location);
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor getCursor(){
        ArrayList<Item> array_list = new ArrayList<Item>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + LOCATION_TABLE_NAME, null );

        return res;
    }
}
