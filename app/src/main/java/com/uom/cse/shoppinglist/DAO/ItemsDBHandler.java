package com.uom.cse.shoppinglist.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemsDBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ItemsDetails";
    public static final String ITEMS_TABLE_NAME = "items";
    public static final String ITEMS_COLUMN_ID = "id";
    public static final String ITEMS_COLUMN_NAME = "name";
    public static final String ITEMS_COLUMN_CATEGORY = "category";
    public static final String ITEMS_COLUMN_UNITS = "units";
    public static final String ITEMS_COLUMN_DAYS = "days";
    public static final String ITEMS_COLUMN_TIMESTAMP = "timestamp";
    public static final String ITEMS_COLUMN_IS_IN_LIST = "isInList";
    private HashMap hp;

    public ItemsDBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + ITEMS_TABLE_NAME + " " +
                        "(" + ITEMS_COLUMN_ID + " integer primary key, " + ITEMS_COLUMN_NAME + " text," +
                        "" + ITEMS_COLUMN_CATEGORY + " text," + ITEMS_COLUMN_UNITS + " text, " +
                        "" + ITEMS_COLUMN_DAYS + " text," + ITEMS_COLUMN_TIMESTAMP + " integer, " +
                        "" + ITEMS_COLUMN_IS_IN_LIST + " integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertItem  (Item item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEMS_COLUMN_NAME, item.getName());
        contentValues.put(ITEMS_COLUMN_CATEGORY, item.getCategory());
        contentValues.put(ITEMS_COLUMN_UNITS, item.getUnits());
        contentValues.put(ITEMS_COLUMN_DAYS, item.getDays());
        contentValues.put(ITEMS_COLUMN_TIMESTAMP, item.getTimestamp());
        contentValues.put(ITEMS_COLUMN_IS_IN_LIST, item.getInList());
        db.insert(ITEMS_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + ITEMS_TABLE_NAME + " where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, ITEMS_TABLE_NAME);
        return numRows;
    }

    public boolean updateItem (Item item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEMS_COLUMN_NAME, item.getName());
        contentValues.put(ITEMS_COLUMN_CATEGORY, item.getCategory());
        contentValues.put(ITEMS_COLUMN_UNITS, item.getUnits());
        contentValues.put(ITEMS_COLUMN_DAYS, item.getDays());
        contentValues.put(ITEMS_COLUMN_TIMESTAMP, item.getTimestamp());
        contentValues.put(ITEMS_COLUMN_IS_IN_LIST, item.getInList());
        db.update(ITEMS_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(item.getId()) } );
        return true;
    }

    public boolean removeItem (Item item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEMS_COLUMN_NAME, item.getName());
        contentValues.put(ITEMS_COLUMN_CATEGORY, item.getCategory());
        contentValues.put(ITEMS_COLUMN_UNITS, item.getUnits());
        contentValues.put(ITEMS_COLUMN_DAYS, item.getDays());
        contentValues.put(ITEMS_COLUMN_TIMESTAMP, item.getTimestamp());
        contentValues.put(ITEMS_COLUMN_IS_IN_LIST, 0);
        db.update(ITEMS_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(item.getId()) } );
        return true;
    }

    public Integer deleteItem (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ITEMS_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Item> getAllItems()
    {
        ArrayList<Item> array_list = new ArrayList<Item>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + ITEMS_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Item item = new Item();
            item.setId(res.getInt(res.getColumnIndex(ITEMS_COLUMN_ID)));
            item.setName(res.getString(res.getColumnIndex(ITEMS_COLUMN_NAME)));
            item.setCategory(res.getString(res.getColumnIndex(ITEMS_COLUMN_CATEGORY)));
            item.setUnits(res.getString(res.getColumnIndex(ITEMS_COLUMN_UNITS)));
            item.setDays(res.getString(res.getColumnIndex(ITEMS_COLUMN_DAYS)));
            item.setTimestamp(res.getInt(res.getColumnIndex(ITEMS_COLUMN_TIMESTAMP)));
            item.setIsInList(res.getInt(res.getColumnIndex(ITEMS_COLUMN_IS_IN_LIST)));

            array_list.add(item);
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Item> getActiveItems()
    {
        ArrayList<Item> array_list = new ArrayList<Item>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + ITEMS_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Item item = new Item();
            if((res.getInt(res.getColumnIndex(ITEMS_COLUMN_IS_IN_LIST)) == 1)) {
                item.setId(res.getInt(res.getColumnIndex(ITEMS_COLUMN_ID)));
                item.setName(res.getString(res.getColumnIndex(ITEMS_COLUMN_NAME)));
                item.setCategory(res.getString(res.getColumnIndex(ITEMS_COLUMN_CATEGORY)));
                item.setUnits(res.getString(res.getColumnIndex(ITEMS_COLUMN_UNITS)));
                item.setDays(res.getString(res.getColumnIndex(ITEMS_COLUMN_DAYS)));
                item.setTimestamp(res.getInt(res.getColumnIndex(ITEMS_COLUMN_TIMESTAMP)));
                item.setIsInList(res.getInt(res.getColumnIndex(ITEMS_COLUMN_IS_IN_LIST)));

                array_list.add(item);
            }
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor getCursor(){
        ArrayList<Item> array_list = new ArrayList<Item>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + ITEMS_TABLE_NAME, null );

        return res;
    }
}
