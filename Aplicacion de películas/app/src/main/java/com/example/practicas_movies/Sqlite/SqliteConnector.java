package com.example.practicas_movies.Sqlite;

import android.content.ContentResolver;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.practicas_movies.models.likeObject;

import java.util.ArrayList;

public class SqliteConnector extends SQLiteOpenHelper {

    private static final String COMMENTS_TABLE_CREATE = "CREATE TABLE favourites(id INTEGER, name TEXT, image TEXT)";
    private static final String DB_NAME = "movie.db";
    private static final int DB_VERSION = 1;


    public SqliteConnector(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format(COMMENTS_TABLE_CREATE));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertDB(String id, String name, String text){
        SQLiteDatabase db = getWritableDatabase();
        String aux = id.substring(0,id.length()-2);
        if (db != null) {
            db.execSQL("INSERT INTO favourites VALUES ('"+aux+"','"+name+"','"+text+"')");
            db.close();
        }
    }

    public void eliminateDB(String id){
        SQLiteDatabase db = getWritableDatabase();
        String aux = id.substring(0,id.length()-2);
        if (db != null) {
            db.execSQL("DELETE FROM favourites WHERE id = '" + aux + "'");
            db.close();
        }
    }

    public boolean readLikeList_byId(String id){

        boolean find_data = false;


        SQLiteDatabase db = getWritableDatabase();
        String aux = id.substring(0,id.length()-2);
        if (db != null) {
            Cursor cursor = db.rawQuery("SELECT * FROM favourites WHERE id = '" + aux + "'", null);

            if(cursor.getCount() > 0){
                find_data = true;
            }


            db.close();
        }
        return find_data;
    }

    public ArrayList<likeObject> readLikeList(){

        SQLiteDatabase db = getWritableDatabase();

        String[] projection = {
                "id",
                "name",
                "image"
        };

        Cursor cursor = db.query("favourites", projection, null, null, null, null, null);

        ArrayList<likeObject> aux_likes = new ArrayList<likeObject>();


        while(cursor.moveToNext()){

            likeObject aux_newObject = new likeObject(cursor.getString(0),cursor.getString(1), cursor.getString(2));
            aux_likes.add(aux_newObject);

        }


        db.close();

        return aux_likes;
    }

}
