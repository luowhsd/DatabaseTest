package com.example.fangl.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SQLiteOpenHelper sqLiteOpenHelper;
    private Button button;
    private Button addData;
    private Button upData;
    private Button deleteData;
    private Button queryData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteOpenHelper = new MyDatabaseHelper(this,"bookStore2.db",null,2);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteOpenHelper.getWritableDatabase();
            }
        });
        addData = (Button) findViewById(R.id.add);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
               /* ContentValues values = new ContentValues();
                values.put("name","heyhey");
                values.put("author","JKrorins");
                values.put("pages",456);
                values.put("price",16.55);
                db.insert("book",null,values);
                values.clear();
                values.put("name","wakaka");
                values.put("author","JKrorins");
                values.put("pages",666);
                values.put("price",1154);
                db.insert("book",null,values);*/

               db.execSQL("insert into book (name,author,pages,price) values (?,?,?,?)",new String[]{"hahaha","jijijojo","799","1311"});

            }
        });
        upData = (Button)findViewById(R.id.updata);
        upData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",1111115);
                db.update("book",values,"name=?",new String[]{"heyhey"});
            }
        });

        deleteData = (Button)findViewById(R.id.deleteData);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
                db.delete("book","pages>?",new String[]{"500"});

            }
        });
        queryData = (Button)findViewById(R.id.query);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
//                Cursor cursor = db.query("book",null,null,null,null,null,null);
                Cursor cursor = db.rawQuery("select * from book where name=?",new String[]{"heyhey"});
                if(cursor.moveToFirst()){
                    do{
                        String id = cursor.getString(cursor.getColumnIndex("id"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        String price = cursor.getString(cursor.getColumnIndex("price"));
                        String pages = cursor.getString(cursor.getColumnIndex("pages"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        Log.d(TAG,id +" "+ author +" "+ price +" "+  pages +" "+  name);

                    }while (cursor.moveToNext());

                }
                cursor.close();
            }
        });
    }
}
