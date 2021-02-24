package com.example.ulanganpwpbsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 5;
    private static final String DATABASE_NAME = "db_perpustakaans";
    private static final String TABLE_NAME = "data_buku";
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA_BUKU = "nama_buku";
    private static final String KEY_PENGARANG = "pengarang";
    private static final String KEY_PENERBIT = "penerbit";

    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createDataBuku = "CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAMA_BUKU+" TEXT,"+KEY_PENGARANG+" TEXT,"+KEY_PENERBIT+" TEXT"+")";
        sqLiteDatabase.execSQL(createDataBuku);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = ("DROP TABLE IF EXISTS " +TABLE_NAME);
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }



    public ArrayList<DataBuku> listDataBuku(){
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DataBuku> storeData = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String nama_buku = cursor.getString(1);
                String penulis = cursor.getString(2);
                String penerbit = cursor.getString(3);
                storeData.add(new DataBuku(id, nama_buku, penulis, penerbit));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeData;
    }
    public void insert(DataBuku dataBuku){

        ContentValues values = new ContentValues();
        values.put(KEY_ID, dataBuku.id);
        values.put(KEY_NAMA_BUKU, dataBuku.nama_buku);
        values.put(KEY_PENGARANG, dataBuku.pengarang);
        values.put(KEY_PENERBIT, dataBuku.penerbit);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
    }

    public void delete (int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID      + "       = ?", new String[]{String.valueOf(id)});
    }

    public void update(DataBuku dataBuku){
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA_BUKU, dataBuku.getNama_buku());
        values.put(KEY_PENGARANG, dataBuku.getPengarang());
        values.put(KEY_PENERBIT, dataBuku.getPenerbit());
        SQLiteDatabase db = this.getWritableDatabase();

        db.update(TABLE_NAME,values,KEY_ID     + "       = ?",new String[] {String.valueOf(dataBuku.getId())});
    }

        public DataBuku findBuku(String name){
            String query  = "Select * FROM "	+ TABLE_NAME + " WHERE " + KEY_NAMA_BUKU + " = " + "nama_buku";
            SQLiteDatabase db = this.getWritableDatabase();
            DataBuku dataBuku = null;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()){
                int id = Integer.parseInt(cursor.getString(0));
                String nama_buku = cursor.getString(1);
                String penulis = cursor.getString(2);
                String penerbit = cursor.getString(3);
                dataBuku = new DataBuku(id, nama_buku, penulis,penerbit);
            }
            cursor.close();
            return dataBuku;
        }

}
